package ir.edmp.mqlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import ir.edmp.mqlplugin.dialog.ConfigurationDialog;
import ir.edmp.mqlplugin.services.FileService;
import ir.edmp.mqlplugin.services.impl.FileServiceImpl;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class ConfigurationAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        try {
            ModuleProjectUtil.getInstance().setProject(event.getProject());
            FileService fileService = FileServiceImpl.getInstance();
            String username = fileService.read(USERNAME);
            String password = fileService.read(PASSWORD);
            String location = fileService.read(PROJECTS_LOCATION);
            ConfigurationDialog dialog = new ConfigurationDialog(username, password, location);
            boolean saveConfiguration = dialog.showAndGet();
            if (saveConfiguration) {
                Map<String, String> properties = new HashMap<>();
                properties.put(USERNAME, dialog.getUsername().getText());
                properties.put(PASSWORD, new String(dialog.getPassword().getPassword()));
                properties.put(PROJECTS_LOCATION, dialog.getMqlLocation().getText());
                fileService.write(properties);
            }
        } catch (IOException e) {
            Messages.showErrorDialog(e.getMessage(), ERROR_CONFIGURATION);
            throw new RuntimeException(e);
        }
    }
}
