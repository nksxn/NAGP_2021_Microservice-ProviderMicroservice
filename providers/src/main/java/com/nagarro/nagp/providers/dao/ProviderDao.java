package com.nagarro.nagp.providers.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nagarro.nagp.providers.entity.Provider;

@Repository
public interface ProviderDao {
	public List<Provider> getAllProviders();

	public void addProvider(Provider provider);

	public Provider getProviderByUsername(String username);

	public boolean updateUser(Provider provider);

}
