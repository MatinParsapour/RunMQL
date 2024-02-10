package ir.edmp.mqlplugin.actions.services.impl;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDocumentManager;
import ir.edmp.mqlplugin.actions.services.FileService;
import ir.edmp.mqlplugin.actions.services.MQLService;
import ir.edmp.mqlplugin.actions.services.ProjectService;
import ir.edmp.mqlplugin.actions.services.ProjectsMainService;
import ir.edmp.mqlplugin.actions.services.impl.properties.PropertiesMainServiceImpl;
import ir.edmp.mqlplugin.actions.services.properties.PropertiesMainService;

import java.io.IOException;

import static ir.edmp.mqlplugin.actions.constants.Constant.*;

public class ProjectsMainServiceImpl implements ProjectsMainService {

	private final Project moduleProject;

	public ProjectsMainServiceImpl(Project moduleProject) {
		this.moduleProject = moduleProject;
	}

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

		ProjectService jpoImporter = new JPOServiceImpl(moduleProject);
		String activeFileProjectName = getActiveFileProjectName();
		return jpoImporter.validateAndUpdateSchema(activeFileProjectName, activeFilePath);

	}

	@Override
	public boolean runMQL() {
		boolean isConfigurationValid = checkConfigFile();
		if (!isConfigurationValid) {
			return false;
		}

		String activeFilePath = getActiveFileAbsolutePath();
		boolean isThereFileOpen = activeFilePath != null;
		if (!isThereFileOpen) {
			return false;
		}

		MQLService mqlService = new MQLServiceImpl(moduleProject);
		String activeFileProjectName = getActiveFileProjectName();
		return mqlService.validateAndUpdateSchema(activeFileProjectName, activeFilePath);
	}

	private String getActiveFileAbsolutePath() {
		Document currentDoc = FileEditorManager.getInstance(moduleProject).getSelectedTextEditor().getDocument();
		String filePath = PsiDocumentManager.getInstance(moduleProject).getPsiFile(currentDoc).getOriginalFile().getVirtualFile().getPath();
		boolean isThereActiveEditor = filePath.isEmpty();
		if (isThereActiveEditor) {
			return null;
		}
		return filePath;
	}

	private String getActiveFileProjectName() {
		Document currentDoc = FileEditorManager.getInstance(moduleProject).getSelectedTextEditor().getDocument();
		String moduleName = ProjectRootManager.getInstance(moduleProject).getFileIndex().getModuleForFile(PsiDocumentManager.getInstance(moduleProject).getPsiFile(currentDoc).getVirtualFile()).getName();
		if (moduleName.isEmpty()) {
			return null;
		}

		return moduleName;
	}

	private boolean checkConfigFile() {
		FileService fileService = FileServiceImpl.getInstance(moduleProject);
		String username = null;
		String password = null;
		String location = null;
		try {
			username = fileService.read(USERNAME);
			password = fileService.read(PASSWORD);
			location = fileService.read(PROJECTS_LOCATION);
		} catch (IOException exception) {
			Messages.showErrorDialog(exception.getMessage(), "Error");
		}

		PropertiesMainService dialogMainService = new PropertiesMainServiceImpl(location, username, password);
		return dialogMainService.startChecking();
	}
}
