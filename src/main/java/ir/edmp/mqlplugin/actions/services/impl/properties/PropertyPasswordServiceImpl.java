package ir.edmp.mqlplugin.actions.services.impl.properties;

import com.intellij.openapi.ui.Messages;
import ir.edmp.mqlplugin.actions.services.properties.PropertyPasswordService;
import ir.edmp.mqlplugin.actions.services.properties.PropertyService;

import static ir.edmp.mqlplugin.actions.constants.Constant.DEFAULT_PASSWORD;

public class PropertyPasswordServiceImpl extends PropertyServiceImpl implements PropertyPasswordService {

	public PropertyPasswordServiceImpl(String property) {
		super(property);
	}

	@Override
	public boolean checkComponent() {
		String password = this.property;
		boolean isPasswordFake = password.equals(DEFAULT_PASSWORD);
		if (isPasswordFake) {
			Messages.showErrorDialog( "Incorrect password : \nChange default password", "Error");
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
