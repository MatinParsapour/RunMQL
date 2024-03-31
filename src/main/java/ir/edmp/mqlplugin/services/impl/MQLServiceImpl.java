package ir.edmp.mqlplugin.services.impl;

import ir.edmp.mqlplugin.services.MQLService;
import ir.edmp.mqlplugin.services.MQLIntegrationService;
import static ir.edmp.mqlplugin.constants.Constant.*;

public class MQLServiceImpl extends ProjectServiceImpl implements MQLService {

    @Override
    public boolean validateAndUpdateSchema(String projectName, String mqlPath) {
        MQLIntegrationService MQLIntegrationService = new MQLIntegrationServiceImpl();
        String script = SCRIPT_RUN_MQL_FILE.replace("${MQL_FILE}", mqlPath);
        return MQLIntegrationService.executeMQLForCurrentFile(projectName, script);
    }
}
