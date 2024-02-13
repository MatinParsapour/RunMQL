package ir.edmp.mqlplugin.services.properties;

import ir.edmp.mqlplugin.services.Service;

public interface PropertiesService extends Service {

	PropertyService linkComponent(PropertyService... propertyServices);
}
