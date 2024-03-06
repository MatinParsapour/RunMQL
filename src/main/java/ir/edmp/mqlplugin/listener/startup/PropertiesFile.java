package ir.edmp.mqlplugin.listener.startup;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import ir.edmp.mqlplugin.entity.ModuleProject;
import ir.edmp.mqlplugin.services.impl.FileServiceImpl;
import ir.edmp.mqlplugin.builder.ModuleProjectBuilder;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import org.jetbrains.annotations.NotNull;

public class PropertiesFile implements StartupActivity {
    @Override
    public void runActivity(@NotNull Project project) {
        ModuleProject moduleProject = ModuleProjectBuilder.getInstance().setProject(project).build();
        ModuleProjectUtil.getInstance().addModuleProject(Thread.currentThread().getId(), moduleProject);
        FileServiceImpl.getInstance();
    }
}
