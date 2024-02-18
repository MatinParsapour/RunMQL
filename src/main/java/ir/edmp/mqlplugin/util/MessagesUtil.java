package ir.edmp.mqlplugin.util;

import com.intellij.openapi.project.Project;

public class MessagesUtil {

    public static void errorAndMessageView(String header, String title, String message, String result, Project project) {
        MessageViewUtil.displayMessage(result, project);
        NotificationUtil.error(header, title, message);
    }
}
