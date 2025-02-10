package com.fly.javadesktop.test1;

import java.awt.*;

public class TrayApp {
    public static void main(String[] args) throws AWTException {
        if(SystemTray.isSupported()) {
            PopupMenu popup = new PopupMenu();
            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(e->System.exit(0));

            TrayIcon icon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("icon.png"), "桌面应用", popup);

            SystemTray.getSystemTray().add(icon);
            icon.displayMessage("提示", "应用已启动", TrayIcon.MessageType.INFO);
        }
    }
}
