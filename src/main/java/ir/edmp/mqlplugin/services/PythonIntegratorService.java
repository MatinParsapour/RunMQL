package ir.edmp.mqlplugin.services;

public interface PythonIntegratorService extends Service {

    boolean runPythonFile(String filePath, String projectName, String pythonFileName);
}
