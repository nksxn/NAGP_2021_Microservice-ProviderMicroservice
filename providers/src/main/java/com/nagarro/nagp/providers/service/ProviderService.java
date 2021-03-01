package com.nagarro.nagp.providers.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.nagp.providers.entity.Provider;

@Service
public interface ProviderService {

	public List<Provider> getAllProviders();

	public void addProvider(Provider provider);

	public Provider getProviderByUsername(String username);

	public boolean updateProvider(Provider provider);

	public void notifyProviders(String serviceRegion);

	public void acceptOrder(String orderCode, String username);

	public void notifyProvider(String username);

}
