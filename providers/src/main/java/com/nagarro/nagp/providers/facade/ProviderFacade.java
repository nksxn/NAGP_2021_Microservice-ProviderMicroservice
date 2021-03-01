package com.nagarro.nagp.providers.facade;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.nagarro.nagp.providers.entity.Provider;

@Component
public interface ProviderFacade {

	public Map<String, Object> getAllProviders();

	public void addProvider(Provider provider);

	public Map<String, Object> getProviderByUsername(String username);

	public boolean updateProvider(Provider provider);

	public void notifyProviders(String serviceRegion);

	public void acceptOrder(String orderCode, String username);

	public void notifyProvider(String username);

}
