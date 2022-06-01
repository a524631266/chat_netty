package org.example.config;


import javax.annotation.Nullable;

/**
 * 配置枚举类型
 */
public enum ConfigKeyEnum {
    HELP("help", "h" ,null,false, "查看命令", ChatConfiguration.empty),
    MODE("mode", "m", "mode", true, "模式client 或者 server", ChatConfiguration.runMode),
    SC_IP("sc.ip", "i" ,"ip",true, "客户端和服务端需要绑定的地址信息", ChatConfiguration.serverIp),
    SC_PORT("sc.port", "p", "port",true,"客户端和服务端需要绑定的端口号", ChatConfiguration.serverPort),
//    CREATE_GROUP("uniGroup", "关联组"),
    ;
    // key为null表示不是内部的key
    String key;
    String cliKey;
    String longCliKey;
    Boolean hasArg;
    String description;
    ChatOption<?> option;

    ConfigKeyEnum(String key, String cliKey, String longCliKey, Boolean hasArg, String description, ChatOption<?> option ) {
        this.key = key;
        this.cliKey = cliKey;
        this.longCliKey = longCliKey;
        this.hasArg = hasArg;
        this.description = description;
        this.option = option;
    }

    @Nullable
    public static ConfigKeyEnum codeOf(String key) {
        for (ConfigKeyEnum configKey : ConfigKeyEnum.values()) {
            if(configKey.key.equals(key)){
                return configKey;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getCliKey() {
        return cliKey;
    }

    public String getLongCliKey() {
        return longCliKey;
    }

    public Boolean getHasArg() {
        return hasArg;
    }

    public String getDescription() {
        return description;
    }

    public ChatOption<?> getOption() {
        return option;
    }
}
