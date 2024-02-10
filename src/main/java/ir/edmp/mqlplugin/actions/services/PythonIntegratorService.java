package ir.edmp.mqlplugin.actions.services;

public interface PythonIntegratorService {

    boolean runPythonFile(String filePath, String projectName, String pythonFileName);
}
