package ir.edmp.mqlplugin.actions.services.impl.properties;

import ir.edmp.mqlplugin.actions.services.properties.PropertiesService;
import ir.edmp.mqlplugin.actions.services.properties.PropertyService;

public class PropertiesServiceImpl implements PropertiesService {

	@Override
	public PropertyService linkComponent(PropertyService... edmpPropertyServices) {
		int propertyIndex = 0;
		int propertiesLength = edmpPropertyServices.length - 1;
		for (int index = 0; index < propertiesLength; index++) {
			propertyIndex = index;
			edmpPropertyServices[propertyIndex].setNextComponent(edmpPropertyServices[++propertyIndex]);
			boolean isThisIndexLastIndex = propertyIndex == propertiesLength;
			if (isThisIndexLastIndex) {
				break;
			}
		}
		return edmpPropertyServices[0];	
	}

}
