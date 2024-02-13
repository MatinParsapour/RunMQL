package ir.edmp.mqlplugin.services.impl.properties;

import com.intellij.openapi.ui.Messages;
import ir.edmp.mqlplugin.services.properties.PropertyLocationService;
import ir.edmp.mqlplugin.services.properties.PropertyService;

import java.io.File;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class PropertyLocationServiceImpl extends PropertyServiceImpl implements PropertyLocationService {

	public PropertyLocationServiceImpl(String property) {
		super(property);
	}

	@Override
	public boolean checkComponent() {
		String location = this.property;
		boolean isLocationEmpty = location.isEmpty();
		if (isLocationEmpty) {
			Messages.showErrorDialog( ERROR_EMPTY_DIRECTORY, ERROR_RUN_MQL);
			return false;
		}

		File insertProgramFile = new File(location + "\\" + FILE_INSERT_PROGRAM);
		boolean isDirectoryContainsInsertProgramFile = insertProgramFile.exists();
		if (!isDirectoryContainsInsertProgramFile) {
			Messages.showErrorDialog( ERROR_INCORRECT_DIRECTORY.replace("${FILE_NAME}",FILE_INSERT_PROGRAM), ERROR_RUN_MQL);
			return false;
		}

		File runMQLFile = new File(location + "\\" + FILE_RUN_MQL);
		boolean isDirectoryContainsRunMQLFile = runMQLFile.exists();
		if (!isDirectoryContainsRunMQLFile) {
			Messages.showErrorDialog( ERROR_INCORRECT_DIRECTORY.replace("${FILE_NAME}",FILE_RUN_MQL), ERROR_RUN_MQL);
			return false;
		}
		
		boolean isNextComponentExists = this.nextProperty != null;
		if (!isNextComponentExists) {
			return true;
		}
		
		return this.nextProperty.checkComponent();
	}

	@Override
	public PropertyService getNextComponent() {
		return this.nextProperty;
	}

	@Override
	public void setNextComponent(PropertyService nextProperty) {
		this.nextProperty = nextProperty;
		
	}
	
	
	

}
