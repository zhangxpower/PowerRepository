package com.fly.javalogging.test1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.*;

public class LoggerExample {
    public static void main(String[] args) throws IOException {
        // 自定义日志配置
        Logger logger = Logger.getLogger("com.fly.javalogging.test1.LoggerExample");
        logger.setUseParentHandlers(false);

        FileHandler fileHandler = new FileHandler("app-%g.log", 1024 * 1024, 5, true);
        fileHandler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                return String.format("[%1$tF %1$tT][%2$-7s] %3$s %n",
                        new Date(record.getMillis()),
                        record.getLevel(),
                        record.getMessage());
            }
        });

        logger.addHandler(fileHandler);
        logger.log(Level.SEVERE, "数据库连接失败", new SQLException("Connection timeout"));
    }
}
