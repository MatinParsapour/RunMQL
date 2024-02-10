package ir.edmp.mqlplugin.actions.builder;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleType;

public class ConfigurationModuleBuilder extends ModuleBuilder  {
    @Override
    public ModuleType<?> getModuleType() {
        return ModuleType.EMPTY;
    }
}
