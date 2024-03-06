package ir.edmp.mqlplugin.builder;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import ir.edmp.mqlplugin.entity.ModuleProject;

public class ModuleProjectBuilder {

    private Project project;
    private AnActionEvent event;
    private Editor editor;
    private Document currentDocument;
    private PsiFile currentDocumentPSIFile;
    private String moduleName;


    private ModuleProjectBuilder(){}

    public static ModuleProjectBuilder getInstance() {
        return new ModuleProjectBuilder();
    }


    public ModuleProjectBuilder setEvent(AnActionEvent event) {
        this.event = event;
        return this;
    }

    public ModuleProjectBuilder setCurrentDocumentPSIFile(PsiFile currentDocumentPSIFile) {
        this.currentDocumentPSIFile = currentDocumentPSIFile;
        return this;
    }

    public ModuleProjectBuilder setEditor(Editor editor) {
        this.editor = editor;
        return this;
    }

    public ModuleProjectBuilder setCurrentDocument(Document currentDocument) {
        this.currentDocument = currentDocument;
        return this;
    }

    public ModuleProjectBuilder setProject(Project project) {
        this.project = project;
        return this;
    }

    public ModuleProjectBuilder setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public PsiFile getCurrentDocumentPSIFile() {
        return currentDocumentPSIFile;
    }

    public Editor getEditor() {
        return editor;
    }

    public Document getCurrentDocument() {
        return currentDocument;
    }

    public Project getProject() {
        return project;
    }

    public AnActionEvent getEvent() {
        return event;
    }

    public String getModuleName() {
        return moduleName;
    }

    public ModuleProject build() {
        ModuleProject moduleProject = new ModuleProject()
                .setProject(project)
                .setEvent(event)
                .setCurrentDocument(currentDocument)
                .setCurrentDocumentPSIFile(currentDocumentPSIFile)
                .setEditor(editor)
                .setModuleName(moduleName);
        return moduleProject;
    }
}
