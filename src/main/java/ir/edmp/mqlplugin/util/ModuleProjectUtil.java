package ir.edmp.mqlplugin.util;
import ir.edmp.mqlplugin.entity.ModuleProject;

import java.util.HashMap;
import java.util.Map;

public class ModuleProjectUtil {

    private Map<Long, ModuleProject> moduleProjects = new HashMap<>();

    private static ModuleProjectUtil instance;

    private ModuleProjectUtil() {}

    public static ModuleProjectUtil getInstance() {
        if (instance == null) {
            instance = new ModuleProjectUtil();
        }
        return  instance;
    }

    public void addModuleProject(Long name, ModuleProject moduleProject) {
        moduleProjects.put(name, moduleProject);
    }

    public ModuleProject getModuleProject(long name) {
        return moduleProjects.get(name);
    }
}
