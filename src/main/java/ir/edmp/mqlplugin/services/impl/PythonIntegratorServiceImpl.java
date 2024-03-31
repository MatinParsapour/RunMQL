package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import ir.edmp.mqlplugin.services.FileService;
import ir.edmp.mqlplugin.services.PythonIntegratorService;
import ir.edmp.mqlplugin.util.MessageViewUtil;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import ir.edmp.mqlplugin.util.NotificationUtil;
import ir.edmp.mqlplugin.util.ProgressIndicatorUtil;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Scanner;
import java.util.StringJoiner;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class PythonIntegratorServiceImpl extends ServiceImpl implements PythonIntegratorService {

    protected String projectsLocation = null;
    protected String username =  null;
    protected String password = null;

    @Override
    public boolean runPythonFile(String projectName, String pythonFileName) {
        return saveAndRunActiveFile(projectName, pythonFileName);
    }

    protected boolean saveAndRunActiveFile(String projectName, String script) {
        ProgressIndicatorUtil.getInstance().updateProgress(15, "Saving active file...");
        Document currentDoc = ModuleProjectUtil.getInstance().getModuleProject(Thread.currentThread().getId()).getCurrentDocument();
        ApplicationManager.getApplication().invokeLater(() -> FileDocumentManager.getInstance().saveDocument(currentDoc));
        ProgressIndicatorUtil.getInstance().updateProgress(20, "Active file saved");
        readProperties();
        return runActiveFile(projectName, script);
    }

    protected boolean runActiveFile(String projectName, String script){
        try {
            ProgressIndicatorUtil.getInstance().updateProgress(25, "Prepare to run python file", pythonFileName);
            ProcessBuilder pythonProcess = new ProcessBuilder("python", pythonFileName, "'" + filePath + "',"  + projectName + "," + this.password + "," + this.username);
            pythonProcess.directory(new File(this.projectsLocation));
            ProgressIndicatorUtil.getInstance().updateProgress(30, "Call python file...", pythonFileName);
            Process processResult = pythonProcess.start();
            processResult.waitFor();
            ProgressIndicatorUtil.getInstance().updateProgress(50, "Call finished");

            BufferedReader processResultReader = new BufferedReader(new InputStreamReader(processResult.getErrorStream()));
            StringBuilder runMQLErrorMessage = null;
            while (processResultReader.readLine() != null) {
                runMQLErrorMessage = new StringBuilder();
                runMQLErrorMessage.append(processResultReader.readLine());
                runMQLErrorMessage.append(System.getProperty("line.separator"));
            }

            boolean isThereError = runMQLErrorMessage != null;
            ProgressIndicatorUtil.getInstance().updateProgress(55, "Check if there's error running python file...", isThereError ? "Yes" : "No");
            if (isThereError) {
                NotificationUtil.error(ERROR_UNSUCCESSFUL_MQL_RUNNING, ERROR_RUN_MQL, runMQLErrorMessage.toString());
                return false;
            }

            boolean isProcessFinishedSuccessfully = processResult.exitValue() == 0;
            if (!isProcessFinishedSuccessfully) {
                displayErrorMessage();

            }

            File file = new File(filePath);
            String fileName = FilenameUtils.removeExtension(file.getName());
            File resultFile = new File(projectsLocation + DIRECTORY_LOGS + fileName + ".txt");
            ProgressIndicatorUtil.getInstance().updateProgress(60, "Reading result file...", fileName);
            if (resultFile.exists()) {
                FileReader resultFileReader = new FileReader(projectsLocation + DIRECTORY_LOGS + fileName + ".txt");
                Scanner myReader = new Scanner(resultFileReader);
                StringJoiner result = new StringJoiner("\n");
                ProgressIndicatorUtil.getInstance().updateProgress(70, "Extracting data");
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    result.add(data);
                }
                resultFileReader.close();

                MessageViewUtil.displayMessage(result.toString());
                if (isProcessFinishedSuccessfully) {
                    NotificationUtil.info(MQL_RAN_SUCCESSFULLY, "MQL Ran successfully", "");
                }
            }
            return true;
        } catch (IOException | InterruptedException exception) {
            NotificationUtil.error(ERROR_UNSUCCESSFUL_MQL_RUNNING, ERROR_RUN_MQL, exception.getMessage());
            return false;
        }
    }

    protected void displayErrorMessage() throws IOException {
        NotificationUtil.error(ERROR_RUN_MQL, ERROR_RUN_MQL, ERROR_UNSUCCESSFUL_MQL_RUNNING + ERROR_SEE_FULL_LOG.replace("${PATH}",DIRECTORY_LOGS + FILE_INSERT_PROGRAM));
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
