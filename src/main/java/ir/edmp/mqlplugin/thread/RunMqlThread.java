package ir.edmp.mqlplugin.thread;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import ir.edmp.mqlplugin.entity.ModuleProject;
import ir.edmp.mqlplugin.services.ProjectsMainService;
import ir.edmp.mqlplugin.services.impl.ProjectsMainServiceImpl;
import ir.edmp.mqlplugin.util.ActionsUtil;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import ir.edmp.mqlplugin.util.ProgressIndicatorUtil;
import org.jetbrains.annotations.NotNull;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class RunMqlThread extends Task.Backgroundable{

    private ModuleProject moduleProject;

    public RunMqlThread(ModuleProject moduleProject) {
        super(moduleProject.getProject(), "Run MQL");
        this.moduleProject = moduleProject;
    }


    @Override
    public void run(@NotNull ProgressIndicator indicator) {
        ModuleProjectUtil.getInstance().addModuleProject(Thread.currentThread().getId(), moduleProject);
        ProgressIndicatorUtil.getInstance().addModuleProject(indicator);
        if (ActionsUtil.noEditorFound()) {
            return;
        }

        String fileExtension = ActionsUtil.getFileExtension();

        // Check if file is java then insert it if its mql then run it
        boolean isFileJava = fileExtension.equals(JAVA_EXTENSION);
        boolean isFileMQL = fileExtension.equals(MQL_EXTENSION);
        ProgressIndicatorUtil.getInstance().updateProgress(3, "Start command");
        ProjectsMainService mainService = new ProjectsMainServiceImpl();
        if (isFileJava) {
            ProgressIndicatorUtil.getInstance().updateProgress(5, "Import JPO");
            mainService.importJPO();
        } else if (isFileMQL) {
            ProgressIndicatorUtil.getInstance().updateProgress(5, "Run MQL Command");
            mainService.runMQL();
        }
    }
}
