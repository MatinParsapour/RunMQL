package ir.edmp.mqlplugin.actions.services.impl;

import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.ui.Messages;
import ir.edmp.mqlplugin.actions.services.FileService;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static ir.edmp.mqlplugin.actions.constants.Constant.*;

public class FileServiceImpl implements FileService {

    private static FileService instance = null;
    private static Project moduleProject = null;
    private static File propertiesFile = null;

    private FileServiceImpl() {
        try {
            createConfigFile();
        } catch (IOException exception) {
            Messages.showErrorDialog("Error, Unable to create properties file : \n" + exception, "Error");
        }
    }

    private void createConfigFile() throws IOException {
        boolean isNewFileCreated = checkConfigurationFile();
        if (!isNewFileCreated) {
            return;
        }
        Map<String, String> properties = new HashMap<>();
        properties.put(PROJECTS_LOCATION, DEFAULT_PROJECTS_DIRECTORY);
        properties.put(USERNAME, DEFAULT_USERNAME);
        properties.put(PASSWORD, DEFAULT_PASSWORD);
        write(properties);
    }

    public static FileService getInstance(Project moduleProject) {
        FileServiceImpl.moduleProject = moduleProject;
        System.out.println(moduleProject);
        boolean isInstanceExists = instance != null;
        if (!isInstanceExists) {
            instance = new FileServiceImpl();
        }
        return instance;
    }

    private static boolean checkConfigurationFile() throws IOException {
        String workspacePath = ModuleRootManager.getInstance(ModuleManager.getInstance(moduleProject).findModuleByName(CONFIGURATION_MODULE)).getSourceRoots()[0].getPath();
        propertiesFile = new File(workspacePath + "\\Config.properties");
        return propertiesFile.createNewFile();
    }
    @Override
    public String read(String key) {
        String propertyValue = null;
        try {
            Properties properties = new Properties();
            FileReader reader = new FileReader(propertiesFile);
            properties.load(reader);
            propertyValue = properties.getProperty(key);
            boolean isPropertyExists = propertyValue != null;
            if (!isPropertyExists) {
                throw new IOException("No property " + key + " found in properties file");
            }
        } catch (IOException exception) {
            Messages.showErrorDialog("Error, Unable to read properties file : \n" + exception, "Error");
        }
        return propertyValue;
    }

    private void write(String key, String value, Properties properties) {
        FileReader reader;
        try {
            reader = new FileReader(propertiesFile);
            properties.load(reader);
            properties.setProperty(key, value);
        } catch (IOException exception) {
            Messages.showErrorDialog( "Unable to write properties file : \n" + exception, "Error");
        }
    }

    @Override
    public void write(Map<String, String> properties) {
        try {
            Properties configFile = new Properties();
            FileWriter writer = new FileWriter(propertiesFile);
            for (Map.Entry<String, String> entry: properties.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                write(key, value, configFile);
            }
            configFile.store(writer, "Last Update : " + new Date());
        } catch (IOException exception) {
            Messages.showErrorDialog( "Unable to write properties file : \n" + exception, "Error");
        }

    }
}
