package ir.edmp.mqlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import ir.edmp.mqlplugin.services.ProjectsMainService;
import ir.edmp.mqlplugin.services.impl.ProjectsMainServiceImpl;
import ir.edmp.mqlplugin.util.ActionsUtil;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import org.jetbrains.annotations.NotNull;

import static ir.edmp.mqlplugin.constants.Constant.JAVA_EXTENSION;
import static ir.edmp.mqlplugin.constants.Constant.MQL_EXTENSION;

public class MQLAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        if (ActionsUtil.noEditorFound(event)) {
            return;
        }

        String fileExtension = ActionsUtil.getFileExtension(event);

        // Check if file is java then insert it if its mql then run it
        boolean isFileJava = fileExtension.equals(JAVA_EXTENSION);
        boolean isFileMQL = fileExtension.equals(MQL_EXTENSION);
        ProjectsMainService mainService = new ProjectsMainServiceImpl();
        if (isFileJava) {
            mainService.importJPO();
        } else if (isFileMQL) {
            mainService.runMQL();
        }
    }
}
