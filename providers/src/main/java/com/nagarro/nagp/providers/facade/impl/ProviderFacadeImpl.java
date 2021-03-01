package com.nagarro.nagp.providers.facade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.nagp.providers.entity.Provider;
import com.nagarro.nagp.providers.facade.ProviderFacade;
import com.nagarro.nagp.providers.service.ProviderService;

@Component
public class ProviderFacadeImpl implements ProviderFacade {

	@Autowired
	ProviderService providerService;

	@Override
	public Map<String, Object> getAllProviders() {
		List<Provider> result = providerService.getAllProviders();
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("providers", result);
		return responseData;
	}

	@Override
	public void addProvider(Provider provider) {
		providerService.addProvider(provider);

	}

	@Override
	public Map<String, Object> getProviderByUsername(String username) {
		Provider provider = providerService.getProviderByUsername(username);
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("provider", provider);
		return responseData;
	}

	@Override
	public boolean updateProvider(Provider provider) {
		return providerService.updateProvider(provider);
	}

	@Override
	public void notifyProviders(String serviceRegion) {
		providerService.notifyProviders(serviceRegion);
	}

	@Override
	public void acceptOrder(String orderCode, String username) {
		providerService.acceptOrder(orderCode, username);
	}

	@Override
	public void notifyProvider(String username) {
		providerService.notifyProvider(username);
	}

}
