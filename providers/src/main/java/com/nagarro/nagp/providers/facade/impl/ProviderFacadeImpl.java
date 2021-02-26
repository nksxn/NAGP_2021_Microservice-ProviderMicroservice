package com.nagarro.nagp.providers.facade.impl;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.nagarro.nagp.providers.entity.Provider;
import com.nagarro.nagp.providers.facade.ProviderFacade;

@Component
public class ProviderFacadeImpl implements ProviderFacade {

	@Override
	public Map<String, Object> getAllProviders() {
		return null;
	}

	@Override
	public void addProvider(Provider provider) {

	}

	@Override
	public Map<String, Object> getProviderByUsername(String username) {
		return null;
	}

	@Override
	public boolean updateProvider(Provider provider) {
		return false;
	}

}
