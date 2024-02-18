package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDocumentManager;
import ir.edmp.mqlplugin.services.FileService;
import ir.edmp.mqlplugin.services.MQLService;
import ir.edmp.mqlplugin.services.ProjectService;
import ir.edmp.mqlplugin.services.ProjectsMainService;
import ir.edmp.mqlplugin.services.impl.properties.PropertiesMainServiceImpl;
import ir.edmp.mqlplugin.services.properties.PropertiesMainService;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;

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

		MQLService mqlService = new MQLServiceImpl();
		String activeFileProjectName = getActiveFileProjectName();
		return mqlService.validateAndUpdateSchema(activeFileProjectName, activeFilePath);
	}

	private String getActiveFileAbsolutePath() {
		Document currentDoc = FileEditorManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getSelectedTextEditor().getDocument();
		String filePath = PsiDocumentManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getPsiFile(currentDoc).getOriginalFile().getVirtualFile().getPath();
		boolean isThereActiveEditor = filePath.isEmpty();
		if (isThereActiveEditor) {
			return null;
		}
		return filePath;
	}

	private String getActiveFileProjectName() {
		Document currentDoc = FileEditorManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getSelectedTextEditor().getDocument();
		String moduleName = ProjectRootManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getFileIndex().getModuleForFile(PsiDocumentManager.getInstance(ModuleProjectUtil.getInstance().getProject()).getPsiFile(currentDoc).getVirtualFile()).getName();
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
			password = fileService.read(PASSWORD);
			location = fileService.read(PROJECTS_LOCATION);
		} catch (IOException exception) {
			Messages.showErrorDialog(exception.getMessage(), ERROR_RUN_MQL);
		}

		PropertiesMainService dialogMainService = new PropertiesMainServiceImpl(location, username, password);
		return dialogMainService.startChecking();
	}
}
