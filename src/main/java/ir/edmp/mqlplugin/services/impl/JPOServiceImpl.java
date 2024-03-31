package ir.edmp.mqlplugin.services.impl;

import ir.edmp.mqlplugin.services.JPOService;
import ir.edmp.mqlplugin.services.MQLIntegrationService;
import static ir.edmp.mqlplugin.constants.Constant.*;

public class JPOServiceImpl extends ProjectServiceImpl implements JPOService {

    @Override
    public boolean validateAndUpdateSchema(String projectName, String jpoPath) {
        MQLIntegrationService MQLIntegrationService = new MQLIntegrationServiceImpl();
        String script = SCRIPT_INSERT_PROGRAM.replace("${JPO_NAME}", jpoPath);
        return MQLIntegrationService.executeMQLForCurrentFile(projectName, script);
    }
}
