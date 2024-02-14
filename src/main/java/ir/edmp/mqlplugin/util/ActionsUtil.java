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
        Editor editor = FileEditorManager.getInstance(moduleProject).getSelectedTextEditor();
        return editor == null;
    }

    public static String getFileExtension(AnActionEvent event) {
        Project moduleProject = event.getProject();
        Editor editor = FileEditorManager.getInstance(moduleProject).getSelectedTextEditor();
        Document currentDoc = editor.getDocument();
        return PsiDocumentManager.getInstance(moduleProject).getPsiFile(currentDoc).getOriginalFile().getVirtualFile().getExtension();
    }
}
