package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.project.Project;
import ir.edmp.mqlplugin.services.MQLService;
import ir.edmp.mqlplugin.services.PythonIntegratorService;
import static ir.edmp.mqlplugin.constants.Constant.*;

public class MQLServiceImpl extends ProjectServiceImpl implements MQLService {

    @Override
    public boolean validateAndUpdateSchema(String projectName, String mqlPath) {
        PythonIntegratorService pythonIntegratorService = new PythonIntegratorServiceImpl();
        return pythonIntegratorService.runPythonFile(mqlPath, projectName, FILE_RUN_MQL);
    }
}
