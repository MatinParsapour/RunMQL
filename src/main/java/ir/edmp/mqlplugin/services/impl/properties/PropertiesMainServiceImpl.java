package ir.edmp.mqlplugin.services.impl.properties;

import ir.edmp.mqlplugin.services.impl.ServiceImpl;
import ir.edmp.mqlplugin.services.properties.PropertiesMainService;
import ir.edmp.mqlplugin.services.properties.PropertiesService;
import ir.edmp.mqlplugin.services.properties.PropertyService;

public class PropertiesMainServiceImpl extends ServiceImpl implements PropertiesMainService {
	private final String locationProperty;
	private final String usernameProperty;
	private final String passwordProperty;

	
	
	public PropertiesMainServiceImpl(String locationProperty, String usernameProperty, String passwordProperty) {
		super();
		this.locationProperty = locationProperty;
		this.usernameProperty = usernameProperty;
		this.passwordProperty = passwordProperty;
	}



	@Override
	public boolean startChecking() {
		PropertiesService componentsService = new PropertiesServiceImpl();
		PropertyService component = componentsService.linkComponent(new PropertyLocationServiceImpl(locationProperty), new PropertyUsernameServiceImpl(usernameProperty), new PropertyPasswordServiceImpl(passwordProperty));
		return component.checkComponent();
	}

	

}
