package ir.edmp.mqlplugin.actions.services.impl;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.ContentFactory;
import ir.edmp.mqlplugin.actions.services.FileService;
import ir.edmp.mqlplugin.actions.services.PythonIntegratorService;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.StringJoiner;

import static ir.edmp.mqlplugin.actions.constants.Constant.*;

public class PythonIntegratorServiceImpl implements PythonIntegratorService {

    protected String projectsLocation = null;
    protected String username =  null;
    protected String password = null;
    private Project moduleProject = null;

    public PythonIntegratorServiceImpl(Project moduleProject) {
        this.moduleProject = moduleProject;
    }

    @Override
    public boolean runPythonFile(String filePath, String projectName, String pythonFileName) {
        return saveAndRunActiveFile(filePath, projectName, pythonFileName);
    }

    protected boolean saveAndRunActiveFile(String jpoName, String projectName, String pythonFileName) {
        Document currentDoc = FileEditorManager.getInstance(moduleProject).getSelectedTextEditor().getDocument();
        FileDocumentManager.getInstance().saveDocument(currentDoc);
        readProperties();
        return runActiveFile(jpoName, projectName, pythonFileName);
    }

    protected boolean runActiveFile(String filePath, String projectName, String pythonFileName){
        try {
            ProcessBuilder runPython = new ProcessBuilder("python", pythonFileName, "'" + filePath + "',"  + projectName + "," + this.password + "," + this.username);
            runPython.directory(new File(this.projectsLocation));
            Process processResult = runPython.start();
            processResult.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(processResult.getErrorStream()));
            StringBuilder builder = null;
            while (reader.readLine() != null) {
                builder = new StringBuilder();
                builder.append(reader.readLine());
                builder.append(System.getProperty("line.separator"));
            }

            boolean isThereError = builder != null;
            if (isThereError) {
                Messages.showErrorDialog("Error, running mql was not successful : " + builder, "Error");
                return false;
            }

            boolean isProcessFinishedSuccessfully = processResult.exitValue() == 0;
            if (!isProcessFinishedSuccessfully) {
                displayErrorMessage();
            }

            Document currentDoc = FileEditorManager.getInstance(moduleProject).getSelectedTextEditor().getDocument();
            String fileExtension = PsiDocumentManager.getInstance(moduleProject).getPsiFile(currentDoc).getOriginalFile().getVirtualFile().getExtension();
            boolean isFileJava = fileExtension.equals(JAVA_EXTENSION);
            if (isFileJava) {
                return true;
            }

            File resultFile = new File(projectsLocation + "\\logs\\result.txt");
            if (resultFile.exists()) {
                FileReader resultFileReader = new FileReader(projectsLocation + "\\logs\\result.txt");
                Scanner myReader = new Scanner(resultFileReader);
                StringJoiner result = new StringJoiner("\n");
                while(myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    result.add(data);
                }
                resultFileReader.close();

                ToolWindow toolWindow = ToolWindowManager.getInstance(moduleProject).getToolWindow("MQL");
                JTextArea resultTextArea = new JTextArea(result.toString());
                resultTextArea.setLineWrap(true);
                resultTextArea.setWrapStyleWord(true);
                JBScrollPane scrollPane = new JBScrollPane(resultTextArea);
                ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
                toolWindow.getContentManager().removeAllContents(true);
                toolWindow.getContentManager().addContent(contentFactory.createContent(scrollPane, "Result", false));
                toolWindow.activate(null);
            }
            return true;
        } catch (IOException | InterruptedException exception) {
            Messages.showErrorDialog("Error, Insertion was not successful : \n" + exception, "Error");
            return false;
        }
    }

    protected void displayErrorMessage() throws IOException {
        Messages.showErrorDialog("Error, Insertion was not successful : \nYou can see full log in " + projectsLocation + "\\logs\\insert_program.log", "Error");
    }

    protected void readProperties() {
        FileService fileService = FileServiceImpl.getInstance(moduleProject);
        username = fileService.read(USERNAME);
        password = fileService.read(PASSWORD);
        projectsLocation = fileService.read(PROJECTS_LOCATION);
    }
}
