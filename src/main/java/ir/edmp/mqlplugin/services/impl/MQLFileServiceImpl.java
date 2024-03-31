package ir.edmp.mqlplugin.services.impl;

import ir.edmp.mqlplugin.services.MQLFileService;
import ir.edmp.mqlplugin.services.MQLIntegrationService;
import static ir.edmp.mqlplugin.constants.Constant.*;

public class MQLFileServiceImpl extends ProjectServiceImpl implements MQLFileService {

    @Override
    public boolean validateAndUpdateSchema(String projectName, String scriptOrFile) {
        MQLIntegrationService MQLIntegrationService = new MQLIntegrationServiceImpl();
        String script = SCRIPT_RUN_MQL_FILE.replace("${MQL_FILE}", scriptOrFile);
        return MQLIntegrationService.executeMQLForCurrentFile(projectName, script);
    }
}
