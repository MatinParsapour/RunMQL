package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.ui.Messages;
import ir.edmp.mqlplugin.services.FileService;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class FileServiceImpl extends ServiceImpl implements FileService {

    private static FileService instance = null;
    private static Project moduleProject = null;
    private static File propertiesFile = null;

    private FileServiceImpl() {
        try {
            createConfigFile();
        } catch (IOException exception) {
            Messages.showErrorDialog(ERROR_UNABLE_TO_CREATE_PROPERTIES_FILE + exception, ERROR_RUN_MQL);
        }
    }

    /**
     * In module "Configuration" creates new properties file and put three properties
     * @throws IOException
     */
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

    /**
     * Singleton design pattern to return only on instance of file service
     * @param moduleProject
     * @return
     */
    public static FileService getInstance(Project moduleProject) {
        FileServiceImpl.moduleProject = moduleProject;
        boolean isInstanceExists = instance != null;
        if (!isInstanceExists) {
            instance = new FileServiceImpl();
        }
        return instance;
    }

    private static boolean checkConfigurationFile() throws IOException {
        File configurationModulePath = new File(new File(moduleProject.getBasePath()).getParentFile() + "\\" + CONFIGURATION_MODULE);
        Module configurationModule = ModuleManager.getInstance(moduleProject).findModuleByName(CONFIGURATION_MODULE);
        if (!configurationModulePath.exists()) {
            Messages.showErrorDialog(ERROR_CONFIGURATION_MODULE_NOT_FOUND, ERROR_RUN_MQL);
            return false;
        }

        String workspacePath = ModuleRootManager.getInstance(configurationModule).getSourceRoots()[0].getPath();
        propertiesFile = new File(workspacePath + FILE_CONFIG);
        return propertiesFile.createNewFile();
    }
    @Override
    public String read(String key) throws IOException{
        createConfigFile();
        String propertyValue = null;
        try {
            Properties properties = new Properties();
            FileReader reader = new FileReader(propertiesFile);
            properties.load(reader);
            propertyValue = properties.getProperty(key);
            boolean isPropertyExists = propertyValue != null;
            if (!isPropertyExists) {
                throw new IOException(ERROR_KEY_NOT_FOUND.replace("${KEY}",key));
            }
        } catch (IOException exception) {
            throw new IOException(ERROR_UNABLE_TO_READ_PROPERTIES_FILE + exception);
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
            Messages.showErrorDialog( ERROR_UNABLE_TO_WRITE_PROPERTIES_FILE + exception, ERROR_RUN_MQL);
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
            Messages.showErrorDialog( ERROR_UNABLE_TO_WRITE_PROPERTIES_FILE + exception, ERROR_RUN_MQL);
        }

    }
}
