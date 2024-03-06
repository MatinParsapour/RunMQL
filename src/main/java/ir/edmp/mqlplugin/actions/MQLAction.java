package ir.edmp.mqlplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressManager;
import ir.edmp.mqlplugin.entity.ModuleProject;
import ir.edmp.mqlplugin.thread.RunMqlThread;
import ir.edmp.mqlplugin.util.ActionsUtil;
import org.jetbrains.annotations.NotNull;

public class MQLAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        ModuleProject moduleProject = ActionsUtil.setModuleData(event);
        RunMqlThread runnable = new RunMqlThread(moduleProject);
        ProgressManager.getInstance().run(runnable);
    }


}
