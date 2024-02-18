package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.project.Project;
import ir.edmp.mqlplugin.services.JPOService;
import ir.edmp.mqlplugin.services.PythonIntegratorService;
import static ir.edmp.mqlplugin.constants.Constant.*;

public class JPOServiceImpl extends ProjectServiceImpl implements JPOService {

    @Override
    public boolean validateAndUpdateSchema(String projectName, String jpoPath) {
        PythonIntegratorService pythonIntegratorService = new PythonIntegratorServiceImpl();
        return pythonIntegratorService.runPythonFile(jpoPath, projectName, FILE_INSERT_PROGRAM);
    }
}
