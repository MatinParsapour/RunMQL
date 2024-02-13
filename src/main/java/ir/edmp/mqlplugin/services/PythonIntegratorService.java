package ir.edmp.mqlplugin.services;

public interface PythonIntegratorService {

    boolean runPythonFile(String filePath, String projectName, String pythonFileName);
}
