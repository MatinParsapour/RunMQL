package ir.edmp.mqlplugin.actions.services.impl.properties;

import ir.edmp.mqlplugin.actions.services.properties.PropertyService;


public abstract class PropertyServiceImpl implements PropertyService {
	protected final String property;
	protected PropertyService nextProperty = null;
	
	public PropertyServiceImpl(String property) {
		this.property = property;
	}

}
