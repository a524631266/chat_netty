package org.example.config;

import org.apache.commons.cli.CommandLine;

import java.util.Objects;

public class ChatCliConfiguration extends ChatConfiguration {

    CommandLine cli ;

    public ChatCliConfiguration(CommandLine cli) {
        this.cli = cli;
    }

    @Override
    public ChatCliConfiguration init() {
        //
        for (ConfigKeyEnum configKey : ConfigKeyEnum.values()) {
            String optionValue = cli.getOptionValue(configKey.getCliKey());
            ChatOption<?> option = configKey.getOption();
            Object value = convertValue(optionValue, option.aClass());
            this.confData.put(option.key(), Objects.isNull(value) ? option.defaultValue() : value);
        }
        return this;
    }

    public Object getValueBy(ConfigKeyEnum key) {
        return this.confData.get(key.getOption().key());
    }
}
