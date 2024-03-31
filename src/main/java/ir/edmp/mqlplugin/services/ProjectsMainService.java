package ir.edmp.mqlplugin.services;

public interface ProjectsMainService extends Service {

	boolean importJPO();

	boolean runMQLFile();

	boolean runMQLScript(String script);
}
