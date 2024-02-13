package ir.edmp.mqlplugin.services.impl.properties;

import com.intellij.openapi.ui.Messages;
import ir.edmp.mqlplugin.services.properties.PropertyService;
import ir.edmp.mqlplugin.services.properties.PropertyUsernameService;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class PropertyUsernameServiceImpl extends PropertyServiceImpl implements PropertyUsernameService {

	public PropertyUsernameServiceImpl(String property) {
		super(property);	
	}

	@Override
	public boolean checkComponent() {
		String username = this.property;
		boolean isUsernameEmpty = username.isEmpty();
		if (isUsernameEmpty) {
			Messages.showErrorDialog(ERROR_EMPTY_USERNAME, ERROR_RUN_MQL);
			return false;
		}
		
		boolean isUsernameFake = username.equals(DEFAULT_USERNAME);
		if (isUsernameFake) {
			Messages.showErrorDialog( ERROR_DEFAULT_USERNAME, ERROR_RUN_MQL);
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
