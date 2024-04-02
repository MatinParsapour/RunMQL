package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.ui.Messages;
import ir.edmp.mqlplugin.services.*;
import ir.edmp.mqlplugin.services.impl.properties.PropertiesMainServiceImpl;
import ir.edmp.mqlplugin.services.properties.PropertiesMainService;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import ir.edmp.mqlplugin.util.ProgressIndicatorUtil;

import java.io.IOException;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class ProjectsMainServiceImpl extends ServiceImpl implements ProjectsMainService {

	@Override
	public boolean importJPO() {
		boolean isConfigurationValid = checkConfigFile();
		if (!isConfigurationValid) {
			return false;
		}

		String activeFilePath = getActiveFileAbsolutePath();
		boolean isThereFileOpen = activeFilePath != null;
		if (!isThereFileOpen) {
			return false;
		}

		ProjectService jpoImporter = new JPOServiceImpl();
		String activeFileProjectName = getActiveFileProjectName();
		return jpoImporter.validateAndUpdateSchema(activeFileProjectName, activeFilePath);

	}

	@Override
	public boolean runMQLFile() {
		boolean isConfigurationValid = checkConfigFile();
		if (!isConfigurationValid) {
			return false;
		}

		String activeFilePath = getActiveFileAbsolutePath();
		boolean isThereFileOpen = activeFilePath != null;
		if (!isThereFileOpen) {
			return false;
		}

		MQLFileService mqlFileService = new MQLFileServiceImpl();
		String activeFileProjectName = getActiveFileProjectName();
		return mqlFileService.validateAndUpdateSchema(activeFileProjectName, activeFilePath);
	}

	@Override
	public boolean runMQLScript(String script) {
		boolean isConfigurationValid = checkConfigFile();
		if (!isConfigurationValid) {
			return false;
		}

		MQLScriptService mqlScriptService = new MQLScriptServiceImpl();
		String activeFileProjectName = getActiveFileProjectName();
		return mqlScriptService.validateAndUpdateSchema(activeFileProjectName, script);
	}

	private String getActiveFileAbsolutePath() {
		String filePath = ModuleProjectUtil.getInstance().getModuleProject().getCurrentDocumentPSIFile().getOriginalFile().getVirtualFile().getPath();
		ProgressIndicatorUtil.getInstance().updateProgress(10, "Read active file path", filePath);
		boolean isThereActiveEditor = filePath.isEmpty();
		if (isThereActiveEditor) {
			return null;
		}
		return filePath;
	}

	private String getActiveFileProjectName() {
		String moduleName = ModuleProjectUtil.getInstance().getModuleProject().getModuleName();
		ProgressIndicatorUtil.getInstance().updateProgress(12, "Read module name", moduleName);
		if (moduleName.isEmpty()) {
			return null;
		}

		return moduleName;
	}

	private boolean checkConfigFile() {
		FileService fileService = FileServiceImpl.getInstance();
		String username = null;
		String password = null;
		String location = null;
		try {
			username = fileService.read(USERNAME);
			ProgressIndicatorUtil.getInstance().updateProgress(6, "Read username", username);
			password = fileService.read(PASSWORD);
			ProgressIndicatorUtil.getInstance().updateProgress(7, "Read password");
			location = fileService.read(PROJECTS_LOCATION);
			ProgressIndicatorUtil.getInstance().updateProgress(8, "Read projects path", location);
		} catch (IOException exception) {
			Messages.showErrorDialog(exception.getMessage(), ERROR_RUN_MQL);
		}

		PropertiesMainService dialogMainService = new PropertiesMainServiceImpl(location, username, password);
		return dialogMainService.startChecking();
	}
}
