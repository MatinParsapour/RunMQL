package ir.edmp.mqlplugin.services.impl;

import ir.edmp.mqlplugin.services.JPOService;
import ir.edmp.mqlplugin.services.MQLIntegrationService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class JPOServiceImpl extends ProjectServiceImpl implements JPOService {

    @Override
    public boolean validateAndUpdateSchema(String projectName, String scriptOrFile) {
        MQLIntegrationService MQLIntegrationService = new MQLIntegrationServiceImpl();
        Path path = Paths.get(scriptOrFile);
        String jpoName = path.getFileName().toString().replace("_mxJPO.java","");
        String script = SCRIPT_INSERT_PROGRAM.replace("${JPO_PATH}", scriptOrFile).replace("${JPO_NAME}", jpoName);
        return MQLIntegrationService.executeMQLForCurrentFile(projectName, script);
    }
}
