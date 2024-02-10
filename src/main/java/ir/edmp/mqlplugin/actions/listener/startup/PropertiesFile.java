package ir.edmp.mqlplugin.actions.listener.startup;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import ir.edmp.mqlplugin.actions.services.impl.FileServiceImpl;
import org.jetbrains.annotations.NotNull;

public class PropertiesFile implements StartupActivity {
    @Override
    public void runActivity(@NotNull Project project) {
        FileServiceImpl.getInstance(project);
    }
}
