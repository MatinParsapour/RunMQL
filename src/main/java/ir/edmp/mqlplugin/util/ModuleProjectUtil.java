package ir.edmp.mqlplugin.util;

import com.intellij.openapi.project.Project;

public class ModuleProjectUtil {

    private Project project;
    private static ModuleProjectUtil instance = null;

    private ModuleProjectUtil(){}

    public static ModuleProjectUtil getInstance() {
        if (instance == null) {
            instance = new ModuleProjectUtil();
        }
        return instance;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
