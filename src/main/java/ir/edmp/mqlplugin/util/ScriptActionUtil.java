package ir.edmp.mqlplugin.util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import ir.edmp.mqlplugin.entity.ModuleProject;
import ir.edmp.mqlplugin.services.MQLScriptService;
import ir.edmp.mqlplugin.services.ScriptService;
import ir.edmp.mqlplugin.services.impl.MQLScriptServiceImpl;
import ir.edmp.mqlplugin.settings.RunMQLSettings;
import ir.edmp.mqlplugin.thread.RunMqlThread;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class ScriptActionUtil {

    public static boolean mqlFileFound(AnActionEvent event) {
        String fileExtension = ActionsUtil.getFileExtension();
        boolean isFileMQL = fileExtension.equals(MQL_EXTENSION);
        if (!isFileMQL) {
            ApplicationManager.getApplication().runReadAction(() -> {
                Messages.showErrorDialog(ERROR_NO_MQL_FILE_FOUND, ERROR_SCRIPT);
            });
        }
        return isFileMQL;
    }

    public static void insertOrDisplayScript(AnActionEvent event, DialogWrapper dialog, ScriptService scriptService) {
        ModuleProject moduleProject = ModuleProjectUtil.getInstance().getModuleProject();
        Editor editor = FileEditorManager.getInstance(moduleProject.getProject()).getSelectedTextEditor();
        Document currentDoc = editor.getDocument();
        boolean isDialogOk = dialog.showAndGet();
        if (isDialogOk) {
            boolean isFileDirty = currentDoc.getTextLength() > 0;
            if (isFileDirty) {
                boolean userWantsToOverwrite = Messages.showYesNoDialog(WARNING_OVERWRITE_DOCUMENT, WARNING, null) == 0;
                if (userWantsToOverwrite) {
                    WriteCommandAction.runWriteCommandAction(ModuleProjectUtil.getInstance().getModuleProject().getProject(), () -> currentDoc.setText(""));
                }
            }
            String script = scriptService.generateRawScript();
            int startOffset = currentDoc.getTextLength() + 1;
            WriteCommandAction.runWriteCommandAction(ModuleProjectUtil.getInstance().getModuleProject().getProject(), () -> currentDoc.insertString(currentDoc.getTextLength(), "\n" + script));
            RunMQLSettings settings = RunMQLSettings.getInstance();
            if (settings.isInsertSchemaImmediately()) {
                int endOffset = currentDoc.getTextLength();
                editor.getSelectionModel().setSelection(startOffset, endOffset);
                RunMqlThread runnable = new RunMqlThread(moduleProject);
                ProgressManager.getInstance().run(runnable);
            }
        }
    }
}
