package ir.edmp.mqlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import ir.edmp.mqlplugin.actions.services.ProjectsMainService;
import ir.edmp.mqlplugin.actions.services.impl.ProjectsMainServiceImpl;
import org.jetbrains.annotations.NotNull;

import static ir.edmp.mqlplugin.actions.constants.Constant.JAVA_EXTENSION;
import static ir.edmp.mqlplugin.actions.constants.Constant.MQL_EXTENSION;

public class MQLAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project moduleProject = event.getProject();
        Document currentDoc = FileEditorManager.getInstance(moduleProject).getSelectedTextEditor().getDocument();
        String fileExtension = PsiDocumentManager.getInstance(moduleProject).getPsiFile(currentDoc).getOriginalFile().getVirtualFile().getExtension();

        // Check if file is java then insert it if its mql then run it
        boolean isFileJava = fileExtension.equals(JAVA_EXTENSION);
        boolean isFileMQL = fileExtension.equals(MQL_EXTENSION);
        ProjectsMainService mainService = new ProjectsMainServiceImpl(event.getProject());
        if (isFileJava) {
            mainService.importJPO();
        } else if (isFileMQL) {
            mainService.runMQL();
        }
    }
}
