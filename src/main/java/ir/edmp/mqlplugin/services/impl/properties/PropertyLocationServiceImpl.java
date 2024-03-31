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
