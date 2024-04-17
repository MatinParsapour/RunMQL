package ir.edmp.mqlplugin.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class RunMQLConfigurable implements Configurable {

    private RunMQLSettingComponent settingComponent;


    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Run MQL Settings";
    }

    @Override
    public @Nullable JComponent createComponent() {
        RunMQLSettings settings = RunMQLSettings.getInstance();
        settingComponent = new RunMQLSettingComponent(settings.getMqlLocation(), settings.getUsername(), settings.getPassword(), settings.isPrintProgramImmediately(), settings.isInsertSchemaImmediately());
        return settingComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        RunMQLSettings settings = RunMQLSettings.getInstance();
        boolean modified = !settingComponent.getMqlLocation().equals(settings.getMqlLocation());
        modified |= !settingComponent.getUsername().equals(settings.getUsername());
        modified |= !settingComponent.getPassword().equals(settings.getPassword());
        modified |= settingComponent.getPrintProgramImmediately() != settings.isPrintProgramImmediately();
        modified |= settingComponent.getInsertSchemaImmediately() != settings.isInsertSchemaImmediately();
        return modified;
    }

    @Override
    public void apply() throws ConfigurationException {
        RunMQLSettings settings = RunMQLSettings.getInstance();
        settings.setMqlLocation(settingComponent.getMqlLocation());
        settings.setUsername(settingComponent.getUsername());
        settings.setPassword(settingComponent.getPassword());
        settings.setPrintProgramImmediately(settingComponent.getPrintProgramImmediately());
        settings.setInsertSchemaImmediately(settingComponent.getInsertSchemaImmediately());
    }
}
