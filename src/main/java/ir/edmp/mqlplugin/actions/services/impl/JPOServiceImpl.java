package ir.edmp.mqlplugin.actions.services.impl;

import com.intellij.openapi.project.Project;
import ir.edmp.mqlplugin.actions.services.JPOService;
import ir.edmp.mqlplugin.actions.services.PythonIntegratorService;
import static ir.edmp.mqlplugin.actions.constants.Constant.*;

public class JPOServiceImpl extends ProjectServiceImpl implements JPOService {

    private Project moduleProject = null;
    public JPOServiceImpl(Project moduleProject) {
        this.moduleProject = moduleProject;
    }

    @Override
    public boolean validateAndUpdateSchema(String projectName, String jpoPath) {
        PythonIntegratorService pythonIntegratorService = new PythonIntegratorServiceImpl(moduleProject);
        return pythonIntegratorService.runPythonFile(jpoPath, projectName, FILE_INSERT_PROGRAM);
    }
}
