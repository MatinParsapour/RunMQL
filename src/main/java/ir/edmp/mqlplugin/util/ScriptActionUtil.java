package ir.edmp.mqlplugin.util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDocumentManager;
import ir.edmp.mqlplugin.services.ScriptService;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class ScriptActionUtil {

    public static boolean mqlFileFound(AnActionEvent event) {
        String fileExtension = ActionsUtil.getFileExtension(event);
        boolean isFileMQL = fileExtension.equals(MQL_EXTENSION);
        if (!isFileMQL) {
            Messages.showErrorDialog(ERROR_NO_MQL_FILE_FOUND, ERROR_SYSTEM_HANDLER);
        }
        return isFileMQL;
    }

    public static void displayDialog(AnActionEvent event, DialogWrapper dialog, ScriptService scriptService) {
        Project moduleProject = event.getProject();
        Editor editor = FileEditorManager.getInstance(moduleProject).getSelectedTextEditor();
        Document currentDoc = editor.getDocument();
        boolean isDialogOk = dialog.showAndGet();
        if (isDialogOk) {
            boolean isFileDirty = currentDoc.getTextLength() > 0;
            if (isFileDirty) {
                boolean userWantsToOverwrite = Messages.showYesNoDialog(WARNING_OVERWRITE_DOCUMENT, WARNING, null) == 0;
                if (userWantsToOverwrite) {
                    WriteCommandAction.runWriteCommandAction(moduleProject, () -> currentDoc.setText(""));
                }
            }
            scriptService.init(dialog);
            String script = scriptService.generateScript();
            WriteCommandAction.runWriteCommandAction(moduleProject, () -> currentDoc.insertString(currentDoc.getTextLength(), "\n" + script));
    }
    }
}
