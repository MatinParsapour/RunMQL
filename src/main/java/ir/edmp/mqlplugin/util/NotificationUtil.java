package ir.edmp.mqlplugin.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

public class NotificationUtil {

    public static void info(String header, String title, String message) {
        final Notification notification = new Notification(header, title, message, NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }

    public static void error(String header, String title, String message) {
        final Notification notification = new Notification(header, title, message, NotificationType.ERROR);
        Notifications.Bus.notify(notification);
    }
}
