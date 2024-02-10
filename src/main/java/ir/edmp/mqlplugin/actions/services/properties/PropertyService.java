package ir.edmp.mqlplugin.actions.services.properties;

public interface PropertyService {
	
	boolean checkComponent();

	PropertyService getNextComponent();

	void setNextComponent(PropertyService nextProperty);
}
