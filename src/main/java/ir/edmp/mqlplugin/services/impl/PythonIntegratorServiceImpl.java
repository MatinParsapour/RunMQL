package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import com.intellij.ui.content.MessageView;
import ir.edmp.mqlplugin.services.FileService;
import ir.edmp.mqlplugin.services.PythonIntegratorService;
import ir.edmp.mqlplugin.util.MessageViewUtil;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import ir.edmp.mqlplugin.util.NotificationUtil;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.StringJoiner;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class PythonIntegratorServiceImpl extends ServiceImpl implements PythonIntegratorService {

    protected String projectsLocation = null;
    protected String username =  null;
    protected String password = null;

    @Override
    public boolean runPythonFile(String filePath, String projectName, String pythonFileName) {
        return saveAndRunActiveFile(filePath, projectName, pythonFileName);
    }

    protected boolean saveAndRunActiveFile(String jpoName, String projectName, String pythonFileName) {
        Document currentDoc = FileEditorManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getSelectedTextEditor().getDocument();
        FileDocumentManager.getInstance().saveDocument(currentDoc);
        readProperties();
        return runActiveFile(jpoName, projectName, pythonFileName);
    }

    protected boolean runActiveFile(String filePath, String projectName, String pythonFileName){
        try {
            ProcessBuilder pythonProcess = new ProcessBuilder("python", pythonFileName, "'" + filePath + "',"  + projectName + "," + this.password + "," + this.username);
            pythonProcess.directory(new File(this.projectsLocation));
            Process processResult = pythonProcess.start();
            processResult.waitFor();

            BufferedReader processResultReader = new BufferedReader(new InputStreamReader(processResult.getErrorStream()));
            StringBuilder runMQLErrorMessage = null;
            while (processResultReader.readLine() != null) {
                runMQLErrorMessage = new StringBuilder();
                runMQLErrorMessage.append(processResultReader.readLine());
                runMQLErrorMessage.append(System.getProperty("line.separator"));
            }

            boolean isThereError = runMQLErrorMessage != null;
            if (isThereError) {
                NotificationUtil.error(ERROR_UNSUCCESSFUL_MQL_RUNNING, ERROR_RUN_MQL, runMQLErrorMessage.toString());
                return false;
            }

            boolean isProcessFinishedSuccessfully = processResult.exitValue() == 0;
            if (!isProcessFinishedSuccessfully) {
                displayErrorMessage();

            }

            File resultFile = new File(projectsLocation + DIRECTORY_LOGS + FILE_RESULT);
            if (resultFile.exists()) {
                FileReader resultFileReader = new FileReader(projectsLocation + DIRECTORY_LOGS + FILE_RESULT);
                Scanner myReader = new Scanner(resultFileReader);
                StringJoiner result = new StringJoiner("\n");
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
