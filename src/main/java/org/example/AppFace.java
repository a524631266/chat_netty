package org.example;

import com.google.common.base.Objects;
import org.apache.commons.cli.*;
import org.example.client.ChatClient;
import org.example.config.ChatCliConfiguration;
import org.example.config.ConfigKeyEnum;
import org.example.server.ChatServer;

public class AppFace {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Options options = new Options();
        ConfigKeyEnum[] values = ConfigKeyEnum.values();
        for (ConfigKeyEnum value : values) {
            options.addOption(value.getCliKey(), value.getLongCliKey(), value.getHasArg(), value.getDescription());
        }
//        options.addOption("h", false, "查看命令");
//        options.addOption("p", "port", true, "监听的端口号");
//        options.addOption("i", "ip", true, "需要监听的ip地址");
//        options.addOption("m", "mode", true, "当前模式");

        GnuParser parser = new GnuParser();
        try {
            CommandLine cli = parser.parse(options, args);
            if (cli.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("选项", options);
            } else {

                ChatCliConfiguration config = new ChatCliConfiguration(cli).init();

                if(isServer(config)) { // 默认null 或者 server是开启server
                    new ChatServer().start(config);
                } else {
                    new ChatClient().start(config);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static boolean isServer(ChatCliConfiguration config) {
        return Objects.equal(config.getValueBy(ConfigKeyEnum.MODE), "server") ||
                Objects.equal(config.getValueBy(ConfigKeyEnum.MODE), null);
    }
}
