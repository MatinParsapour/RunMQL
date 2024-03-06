package ir.edmp.mqlplugin.util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import ir.edmp.mqlplugin.builder.ModuleProjectBuilder;
import ir.edmp.mqlplugin.entity.ModuleProject;

public class ActionsUtil {

    public static boolean noEditorFound() {
        Editor editor = ModuleProjectUtil.getInstance().getModuleProject(Thread.currentThread().getId()).getEditor();
        return editor == null;
    }

    public static String getFileExtension() {
        return ModuleProjectUtil.getInstance().getModuleProject(Thread.currentThread().getId()).getCurrentDocumentPSIFile().getOriginalFile().getVirtualFile().getExtension();
    }

    public static ModuleProject setModuleData(AnActionEvent event) {
        Project project = event.getProject();
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        Document currentDoc = editor.getDocument();
        PsiFile currentDocPsiFile = PsiDocumentManager.getInstance(project).getPsiFile(currentDoc);
        ModuleProject moduleProject = ModuleProjectBuilder.getInstance()
                .setProject(project)
                .setEvent(event)
                .setCurrentDocument(currentDoc)
                .setCurrentDocumentPSIFile(currentDocPsiFile)
                .setEditor(editor)
                .setModuleName(ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(PsiDocumentManager.getInstance(project).getPsiFile(currentDoc).getVirtualFile()).getName())
                .build();
        return moduleProject;
    }
}
