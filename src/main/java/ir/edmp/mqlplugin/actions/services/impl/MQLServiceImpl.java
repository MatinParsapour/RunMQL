package ir.edmp.mqlplugin.actions.services.impl;

import com.intellij.openapi.project.Project;
import ir.edmp.mqlplugin.actions.services.MQLService;
import ir.edmp.mqlplugin.actions.services.PythonIntegratorService;
import static ir.edmp.mqlplugin.actions.constants.Constant.*;

public class MQLServiceImpl extends ProjectServiceImpl implements MQLService {
    private Project moduleProject = null;

    public MQLServiceImpl(Project moduleProject) {
        this.moduleProject = moduleProject;
    }

    @Override
    public boolean validateAndUpdateSchema(String projectName, String mqlPath) {
        PythonIntegratorService pythonIntegratorService = new PythonIntegratorServiceImpl(moduleProject);
        return pythonIntegratorService.runPythonFile(mqlPath, projectName, FILE_RUN_MQL);
    }
}
