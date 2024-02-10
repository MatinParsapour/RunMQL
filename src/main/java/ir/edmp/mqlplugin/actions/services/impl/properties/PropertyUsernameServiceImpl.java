package ir.edmp.mqlplugin.actions.services.impl.properties;

import com.intellij.openapi.ui.Messages;
import ir.edmp.mqlplugin.actions.services.properties.PropertyService;
import ir.edmp.mqlplugin.actions.services.properties.PropertyUsernameService;

import static ir.edmp.mqlplugin.actions.constants.Constant.DEFAULT_USERNAME;

public class PropertyUsernameServiceImpl extends PropertyServiceImpl implements PropertyUsernameService {

	public PropertyUsernameServiceImpl(String property) {
		super(property);	
	}

	@Override
	public boolean checkComponent() {
		String username = this.property;
		boolean isUsernameEmpty = username.isEmpty();
		if (isUsernameEmpty) {
			Messages.showErrorDialog("Incorrect username : \nUsername can not be empty", "Error");
			return false;
		}
		
		boolean isUsernameFake = username.equals(DEFAULT_USERNAME);
		if (isUsernameFake) {
			Messages.showErrorDialog( "Incorrect username : \nChange default username", "Error");
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
