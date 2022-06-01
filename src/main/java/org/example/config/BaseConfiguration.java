package org.example.config;


import org.example.config.type.NullType;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseConfiguration {

    public final HashMap<String, Object> confData;

    public BaseConfiguration(HashMap<String, Object> confData) {
        this.confData = confData;
    }

    /**
     * 获取值
     * @param option
     * @return
     */
    public <T> T getRawValueFromOption(ChatOption<T> option){
        String key = option.key();
        Object o = confData.get(key);
        if(!Objects.isNull(o)){
            return (T) confData.get(key);
        } else {
            return option.defaultValue();
        }
    }

    protected  <T> T convertValue(Object rawValue, Class<?> clazz) {
        if(Objects.isNull(rawValue)){
            return null;
        }
        if (Integer.class.equals(clazz)) {
            return (T) convertToInt(rawValue);
        } else if (Long.class.equals(clazz)) {
            return (T) convertToLong(rawValue);
        } else if (Boolean.class.equals(clazz)) {
            return (T) convertToBoolean(rawValue);
        } else if (Float.class.equals(clazz)) {
            return (T) convertToFloat(rawValue);
        } else if (Double.class.equals(clazz)) {
            return (T) convertToDouble(rawValue);
        } else if (String.class.equals(clazz)) {
            return (T) convertToString(rawValue);
        } else if (clazz.isEnum()) {
            return (T) convertToEnum(rawValue, (Class<? extends Enum<?>>) clazz);
        } else if (NullType.class.equals(clazz)){
            return null;
        }
        throw new IllegalArgumentException("Unsupported type: " + clazz);
    }

    private Integer convertToInt(Object o) {
        if (o.getClass() == Integer.class) {
            return (Integer) o;
        } else if (o.getClass() == Long.class) {
            long value = (Long) o;
            if (value <= Integer.MAX_VALUE && value >= Integer.MIN_VALUE) {
                return (int) value;
            } else {
                throw new IllegalArgumentException(String.format(
                        "Configuration value %s overflows/underflows the integer type.",
                        value));
            }
        }

        return Integer.parseInt(o.toString());
    }

    private Long convertToLong(Object o) {
        if (o.getClass() == Long.class) {
            return (Long) o;
        } else if (o.getClass() == Integer.class) {
            return ((Integer) o).longValue();
        }

        return Long.parseLong(o.toString());
    }

    private Boolean convertToBoolean(Object o) {
        if (o.getClass() == Boolean.class) {
            return (Boolean) o;
        }

        switch (o.toString().toUpperCase()) {
            case "TRUE":
                return true;
            case "FALSE":
                return false;
            default:
                throw new IllegalArgumentException(String.format(
                        "Unrecognized option for boolean: %s. Expected either true or false(case insensitive)",
                        o));
        }
    }

    private Float convertToFloat(Object o) {
        if (o.getClass() == Float.class) {
            return (Float) o;
        } else if (o.getClass() == Double.class) {
            double value = ((Double) o);
            if (value == 0.0
                    || (value >= Float.MIN_VALUE && value <= Float.MAX_VALUE)
                    || (value >= -Float.MAX_VALUE && value <= -Float.MIN_VALUE)) {
                return (float) value;
            } else {
                throw new IllegalArgumentException(String.format(
                        "Configuration value %s overflows/underflows the float type.",
                        value));
            }
        }

        return Float.parseFloat(o.toString());
    }

    private Double convertToDouble(Object o) {
        if (o.getClass() == Double.class) {
            return (Double) o;
        } else if (o.getClass() == Float.class) {
            return ((Float) o).doubleValue();
        }

        return Double.parseDouble(o.toString());
    }

    private String convertToString(Object o) {
        if (o.getClass() == String.class) {
            return (String) o;
        } else if (o.getClass() == Duration.class) {
            Duration duration = (Duration) o;
            return String.format("%d ns", duration.toNanos());
        } else if (o instanceof List) {
            return ((List<?>) o).stream()
                    .map(e -> escapeWithSingleQuote(convertToString(e), ";"))
                    .collect(Collectors.joining(";"));
        } else if (o instanceof Map) {
            return ((Map<?, ?>) o).entrySet().stream()
                    .map(e -> {
                        String escapedKey = escapeWithSingleQuote(e.getKey().toString(), ":");
                        String escapedValue = escapeWithSingleQuote(e.getValue().toString(), ":");

                        return escapeWithSingleQuote(escapedKey + ":" + escapedValue, ",");
                    })
                    .collect(Collectors.joining(","));
        }

        return o.toString();
    }
    static String escapeWithSingleQuote(String string, String... charsToEscape) {
        boolean escape = Arrays.stream(charsToEscape).anyMatch(string::contains)
                || string.contains("\"") || string.contains("'");

        if (escape) {
            return "'" + string.replaceAll("'", "''") + "'";
        }

        return string;
    }
    private <E extends Enum<?>> E convertToEnum(Object o, Class<E> clazz) {
        if (o.getClass().equals(clazz)) {
            return (E) o;
        }

        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> e.toString().toUpperCase(Locale.ROOT).equals(o.toString().toUpperCase(Locale.ROOT)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Could not parse value for enum %s. Expected one of: [%s]", clazz,
                                Arrays.toString(clazz.getEnumConstants()))));
    }
}
