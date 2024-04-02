package ir.edmp.mqlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import ir.edmp.mqlplugin.dialog.TriggerDialog;
import ir.edmp.mqlplugin.services.ScriptService;
import ir.edmp.mqlplugin.services.impl.TriggerScriptServiceImpl;
import ir.edmp.mqlplugin.util.ActionsUtil;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import ir.edmp.mqlplugin.util.ScriptActionUtil;
import org.jetbrains.annotations.NotNull;

public class TriggerAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        ModuleProjectUtil.getInstance().addModuleProject(ActionsUtil.setModuleData(event));
        if (ActionsUtil.noEditorFound()) {
            return;
        }

        if (ScriptActionUtil.mqlFileFound(event)) {
            TriggerDialog triggerDialog = new TriggerDialog();
            ScriptService scriptService = new TriggerScriptServiceImpl(triggerDialog);
            ScriptActionUtil.displayDialog(event, triggerDialog, scriptService);
        }
    }
}
