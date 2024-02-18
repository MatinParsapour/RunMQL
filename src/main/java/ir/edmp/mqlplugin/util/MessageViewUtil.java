package ir.edmp.mqlplugin.util;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiDocumentManager;
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

        Document currentDoc = FileEditorManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getSelectedTextEditor().getDocument();
        String fileName = PsiDocumentManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getPsiFile(currentDoc).getOriginalFile().getVirtualFile().getName();
        ModuleProjectUtil.getInstance().getProject().getService(MessageView.class);
        ToolWindow toolWindow = ToolWindowManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getToolWindow(ToolWindowId.MESSAGES_WINDOW);
        Content content =  toolWindow.getContentManager().findContent(fileName);
        boolean fileNameContentDoesNotExists = content == null;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        if (!fileNameContentDoesNotExists) {
            content.getManager().removeContent(content, true);
            toolWindow.getContentManager().addContent(contentFactory.createContent(scrollPane, fileName, false));
        } else {
            toolWindow.getContentManager().addContent(contentFactory.createContent(scrollPane, fileName, false));
        }
        toolWindow.activate(null);
    }
}
