package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import ir.edmp.mqlplugin.services.FileService;
import ir.edmp.mqlplugin.services.MQLIntegrationService;
import ir.edmp.mqlplugin.util.MessageViewUtil;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import ir.edmp.mqlplugin.util.NotificationUtil;
import ir.edmp.mqlplugin.util.ProgressIndicatorUtil;

import java.io.*;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class MQLIntegrationServiceImpl extends ServiceImpl implements MQLIntegrationService {

    protected String projectsLocation = null;
    protected String username =  null;
    protected String password = null;

    @Override
    public boolean executeMQLForCurrentFile(String projectName, String pythonFileName) {
        return saveAndRunActiveFile(projectName, pythonFileName);
    }

    protected boolean saveAndRunActiveFile(String projectName, String script) {
        ProgressIndicatorUtil.getInstance().updateProgress(15, "Saving active file...");
        Document currentDoc = ModuleProjectUtil.getInstance().getModuleProject(Thread.currentThread().getId()).getCurrentDocument();
        ApplicationManager.getApplication().invokeLater(() -> FileDocumentManager.getInstance().saveDocument(currentDoc));
        ProgressIndicatorUtil.getInstance().updateProgress(20, "Active file saved");
        readProperties();
        return executeMQL(projectName, script);
    }

    protected boolean executeMQL(String projectName, String script){
        try {
            // Execute mql file in project path and run script
            ProgressIndicatorUtil.getInstance().updateProgress(25, "Prepare to execute mql");
            ProcessBuilder pythonProcess = new ProcessBuilder("cmd", "start", "/c", "mql.exe.lnk", "-c", "set context user " + this.username + " password " + this.password + "; " + script);
            pythonProcess.directory(new File(this.projectsLocation + "\\" + projectName));
            pythonProcess.redirectErrorStream(true);
            Process process = pythonProcess.start();
            ProgressIndicatorUtil.getInstance().updateProgress(50, "Execution finished");

            // Reading result of execution
            ProgressIndicatorUtil.getInstance().updateProgress(55, "Reading execution result...");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();

            boolean isThereError = process.exitValue() == 1;
            ProgressIndicatorUtil.getInstance().updateProgress(65, "Check if there's error running python file...", isThereError ? "Yes" : "No");
            if (isThereError) {
                displayErrorMessage();
            }

            ProgressIndicatorUtil.getInstance().updateProgress(75, "Packing result...");
            MessageViewUtil.displayMessage(result);
            if (!isThereError) {
                NotificationUtil.info(MQL_RAN_SUCCESSFULLY, "MQL Ran successfully", "");
            }
            return true;
        } catch (Exception exception) {
            NotificationUtil.error(ERROR_UNSUCCESSFUL_MQL_RUNNING, ERROR_RUN_MQL, exception.getMessage());
            return false;
        }
    }

    protected void displayErrorMessage() {
        NotificationUtil.error(ERROR_RUN_MQL, ERROR_RUN_MQL, ERROR_UNSUCCESSFUL_MQL_RUNNING);
    }

    protected void readProperties() {
        FileService fileService = FileServiceImpl.getInstance();
        try {
            username = fileService.read(USERNAME);
            password = fileService.read(PASSWORD);
            projectsLocation = fileService.read(PROJECTS_LOCATION);
        } catch (IOException e) {
            NotificationUtil.error(ERROR_RUN_MQL, ERROR_RUN_MQL ,e.getMessage());
        }
    }
}
