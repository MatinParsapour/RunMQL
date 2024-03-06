package ir.edmp.mqlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import ir.edmp.mqlplugin.dialog.SystemHandlerDialog;
import ir.edmp.mqlplugin.services.ScriptService;
import ir.edmp.mqlplugin.services.impl.SystemHandlerScriptServiceImpl;
import ir.edmp.mqlplugin.util.ActionsUtil;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import ir.edmp.mqlplugin.util.ScriptActionUtil;
import org.jetbrains.annotations.NotNull;

public class SystemHandlerAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        ModuleProjectUtil.getInstance().addModuleProject(Thread.currentThread().getId(), ActionsUtil.setModuleData(event));
        if (ActionsUtil.noEditorFound()) {
            return;
        }

        if (ScriptActionUtil.mqlFileFound(event)) {
            SystemHandlerDialog systemHandlerDialog = new SystemHandlerDialog();
            ScriptService scriptService = new SystemHandlerScriptServiceImpl(systemHandlerDialog);
            ScriptActionUtil.displayDialog(event, systemHandlerDialog, scriptService);
        }
    }
}
