package ir.edmp.mqlplugin.services.properties;

import ir.edmp.mqlplugin.services.Service;

public interface PropertyService extends Service {
	
	boolean checkComponent();

	PropertyService getNextComponent();

	void setNextComponent(PropertyService nextProperty);
}
