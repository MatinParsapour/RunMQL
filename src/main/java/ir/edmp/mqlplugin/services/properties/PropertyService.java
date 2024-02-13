package ir.edmp.mqlplugin.services.properties;

public interface PropertyService {
	
	boolean checkComponent();

	PropertyService getNextComponent();

	void setNextComponent(PropertyService nextProperty);
}
