package ir.edmp.mqlplugin.services;

public interface ProjectService extends Service {

    boolean validateAndUpdateSchema(String projectName, String scriptOrFile);
}
