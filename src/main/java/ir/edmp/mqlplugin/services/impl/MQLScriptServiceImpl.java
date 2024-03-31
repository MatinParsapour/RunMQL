package ir.edmp.mqlplugin.services.impl;

import ir.edmp.mqlplugin.services.MQLFileService;
import ir.edmp.mqlplugin.services.MQLIntegrationService;
import ir.edmp.mqlplugin.services.MQLScriptService;

import static ir.edmp.mqlplugin.constants.Constant.SCRIPT_RUN_MQL_FILE;

public class MQLScriptServiceImpl extends ProjectServiceImpl implements MQLScriptService {
    @Override
    public boolean validateAndUpdateSchema(String projectName, String scriptOrFile) {
        MQLIntegrationService MQLIntegrationService = new MQLIntegrationServiceImpl();
        return MQLIntegrationService.executeMQLForCurrentFile(projectName, scriptOrFile);
    }
}
