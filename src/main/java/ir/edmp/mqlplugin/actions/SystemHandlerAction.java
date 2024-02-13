package ir.edmp.mqlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDocumentManager;
import ir.edmp.mqlplugin.dialog.SystemHandlerDialog;
import ir.edmp.mqlplugin.services.ScriptService;
import ir.edmp.mqlplugin.services.impl.SystemHandlerScriptServiceImpl;
import ir.edmp.mqlplugin.services.impl.TriggerScriptServiceImpl;
import org.jetbrains.annotations.NotNull;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class SystemHandlerAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project moduleProject = event.getProject();
        Editor editor = FileEditorManager.getInstance(moduleProject).getSelectedTextEditor();
        boolean isThereEditor = editor == null;
        if (isThereEditor) {
            return;
        }

        Document currentDoc = editor.getDocument();
        String fileExtension = PsiDocumentManager.getInstance(moduleProject).getPsiFile(currentDoc).getOriginalFile().getVirtualFile().getExtension();
        boolean isFileMQL = fileExtension.equals(MQL_EXTENSION);
        if (!isFileMQL) {
            Messages.showErrorDialog(ERROR_NO_MQL_FILE_FOUND, ERROR_TRIGGER);
        } else {
            SystemHandlerDialog systemHandlerDialog = new SystemHandlerDialog();
            boolean isDialogOk = systemHandlerDialog.showAndGet();
            if (isDialogOk) {
                boolean isFileDirty = currentDoc.getTextLength() > 0;
                if (isFileDirty) {
                    boolean userWantsToOverwrite = Messages.showYesNoDialog(WARNING_OVERWRITE_DOCUMENT, WARNING, null) == 0;
                    if (userWantsToOverwrite) {
                        WriteCommandAction.runWriteCommandAction(moduleProject, () -> currentDoc.setText(""));
                    }
                }
                ScriptService scriptService = new SystemHandlerScriptServiceImpl(systemHandlerDialog);
                String script = scriptService.generateScript();
                WriteCommandAction.runWriteCommandAction(moduleProject, () -> currentDoc.insertString(currentDoc.getTextLength(), "\n" + script));
            }
        }
    }
}
