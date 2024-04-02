package ir.edmp.mqlplugin.util;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.MessageView;

import javax.swing.*;

public class MessageViewUtil {

    public static void displayMessage(String result) {
        JTextArea resultTextArea = new JTextArea(result);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        JBScrollPane scrollPane = new JBScrollPane(resultTextArea);
        ProgressIndicatorUtil.getInstance().updateProgress(80, "Packing data...");

        String fileName = ModuleProjectUtil.getInstance().getModuleProject().getCurrentDocumentPSIFile().getOriginalFile().getVirtualFile().getName();

        ProgressIndicatorUtil.getInstance().updateProgress(90, "Prepare to display data...");
        ApplicationManager.getApplication().invokeLaterOnWriteThread(() -> {
            ModuleProjectUtil.getInstance().getModuleProject().getProject().getService(MessageView.class);
            ToolWindow toolWindow = ToolWindowManager.getInstance(ModuleProjectUtil.getInstance().getModuleProject().getProject()).getToolWindow(ToolWindowId.MESSAGES_WINDOW);
            Content content =  toolWindow.getContentManager().findContent(fileName);
            boolean fileNameContentDoesNotExists = content == null;
            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            Content newContent = contentFactory.createContent(scrollPane, fileName, false);
            toolWindow.getContentManager().addContent(newContent, 0);
            toolWindow.getContentManager().setSelectedContent(newContent);
            if (!fileNameContentDoesNotExists) {
                content.getManager().removeContent(content, true);
            }
            toolWindow.activate(null);
        });
        ProgressIndicatorUtil.getInstance().updateProgress(95, "Process finished");
    }
}
