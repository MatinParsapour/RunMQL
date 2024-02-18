package ir.edmp.mqlplugin.util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;

public class ActionsUtil {

    public static boolean noEditorFound(AnActionEvent event) {
        Project moduleProject = event.getProject();
        ModuleProjectUtil.getInstance().setProject(moduleProject);
        Editor editor = FileEditorManager.getInstance(moduleProject).getSelectedTextEditor();
        return editor == null;
    }

    public static String getFileExtension(AnActionEvent event) {
        Editor editor = FileEditorManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getSelectedTextEditor();
        Document currentDoc = editor.getDocument();
        return PsiDocumentManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getPsiFile(currentDoc).getOriginalFile().getVirtualFile().getExtension();
    }
}
