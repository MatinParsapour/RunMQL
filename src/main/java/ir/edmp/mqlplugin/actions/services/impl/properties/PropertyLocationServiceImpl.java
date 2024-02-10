package ir.edmp.mqlplugin.actions.services.impl.properties;

import com.intellij.openapi.ui.Messages;
import ir.edmp.mqlplugin.actions.services.properties.PropertyLocationService;
import ir.edmp.mqlplugin.actions.services.properties.PropertyService;

import java.io.File;

public class PropertyLocationServiceImpl extends PropertyServiceImpl implements PropertyLocationService {

	public PropertyLocationServiceImpl(String property) {
		super(property);
	}

	@Override
	public boolean checkComponent() {
		String location = this.property;
		boolean isLocationEmpty = location.isEmpty();
		if (isLocationEmpty) {
			Messages.showErrorDialog( "Incorrect Directory : \nDirectory of projects can not be empty", "Error");
			return false;
		}
		
		File file = new File(location + "\\insert_program.py");
		boolean isDirectoryContainsPythonFile = file.exists();
		if (!isDirectoryContainsPythonFile) {
			Messages.showErrorDialog( "Incorrect Directory : \nThe directory must contains insert_program.py file", "Error");
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
