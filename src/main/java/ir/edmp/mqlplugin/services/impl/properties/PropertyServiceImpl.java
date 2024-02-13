package ir.edmp.mqlplugin.services.impl.properties;

import ir.edmp.mqlplugin.services.properties.PropertyService;


public abstract class PropertyServiceImpl implements PropertyService {
	protected final String property;
	protected PropertyService nextProperty = null;
	
	public PropertyServiceImpl(String property) {
		this.property = property;
	}

}
