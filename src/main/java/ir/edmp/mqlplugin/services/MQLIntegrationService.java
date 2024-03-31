package ir.edmp.mqlplugin.services;

public interface MQLIntegrationService extends Service {

    boolean executeMQLForCurrentFile(String projectName, String pythonFileName);
}
