package ir.edmp.mqlplugin.util;

import com.intellij.openapi.progress.ProgressIndicator;
import ir.edmp.mqlplugin.entity.ModuleProject;

import java.util.HashMap;
import java.util.Map;

public class ProgressIndicatorUtil {

    private Map<Long, ProgressIndicator> moduleProjects = new HashMap<>();

    private static ProgressIndicatorUtil instance;

    private ProgressIndicatorUtil() {}

    public static ProgressIndicatorUtil getInstance() {
        if (instance == null) {
            instance = new ProgressIndicatorUtil();
        }
        return  instance;
    }

    public void addModuleProject(ProgressIndicator moduleProject) {
        moduleProjects.put(Thread.currentThread().getId(), moduleProject);
    }

    public ProgressIndicator getModuleProject() {
        return moduleProjects.get(Thread.currentThread().getId());
    }

    public void updateProgress(double fraction, String text) {
        updateProgress(fraction, text, null);
    }
    public void updateProgress(double fraction, String text, String secondText) {
        getModuleProject().setFraction(fraction);
        getModuleProject().setText(text);
        getModuleProject().setText2(secondText);
    }
}
