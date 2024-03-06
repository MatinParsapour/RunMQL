package ir.edmp.mqlplugin.entity;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

public class ModuleProject {

    private Project project;
    private AnActionEvent event;
    private Editor editor;
    private Document currentDocument;
    private PsiFile currentDocumentPSIFile;
    private String moduleName;

    public Project getProject() {
        return project;
    }

    public ModuleProject setProject(Project project) {
        this.project = project;
        return this;
    }

    public AnActionEvent getEvent() {
        return event;
    }

    public ModuleProject setEvent(AnActionEvent event) {
        this.event = event;
        return this;
    }

    public Editor getEditor() {
        return editor;
    }

    public ModuleProject setEditor(Editor editor) {
        this.editor = editor;
        return this;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public ModuleProject setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
        return this;
    }

    public PsiFile getCurrentDocumentPSIFile() {
        return currentDocumentPSIFile;
    }

    public ModuleProject setCurrentDocumentPSIFile(PsiFile currentDocumentPSIFile) {
        this.currentDocumentPSIFile = currentDocumentPSIFile;
        return this;
    }

    public String getModuleName() {
        return moduleName;
    }

    public ModuleProject setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }
}
