package com.nagarro.nagp.providers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagarro.nagp.providers.dao.ProviderDao;
import com.nagarro.nagp.providers.entity.Provider;

public class ProviderDaoImpl implements ProviderDao {

	private static final Logger LOG = LoggerFactory.getLogger(ProviderDaoImpl.class);

	private static List<Provider> providers = new ArrayList<Provider>();

	@Override
	public List<Provider> getAllProviders() {
		LOG.info("Mocking the DB call. Fetching providers data from the list");
		return providers;
	}

	@Override
	public void addProvider(Provider provider) {
		LOG.info("Mocking the DB call. Adding provider data to the list");
		List<Provider> result = providers.stream().filter(c -> c.getUsername().equalsIgnoreCase(provider.getUsername()))
				.collect(Collectors.toList());
		if (result.isEmpty()) {
			providers.add(provider);
		} else {
			LOG.error("Provider already exists in the database.");
		}
	}

	@Override
	public Provider getProviderByUsername(String username) {
		LOG.info("Mocking the DB call. Getting provider from the list based on username");
		List<Provider> result = providers.stream().filter(c -> c.getUsername().equalsIgnoreCase(username))
				.collect(Collectors.toList());
		if (result.isEmpty()) {
			LOG.error("Provider with " + username + " doesn't exist.");
			return null;
		}
		return result.get(0);

	}

	@Override
	public boolean updateUser(Provider provider) {
		LOG.info("Mocking the DB call. Updating provider in the list.");
		List<Provider> result = providers.stream().filter(c -> c.getUsername().equalsIgnoreCase(provider.getUsername()))
				.collect(Collectors.toList());
		if (result.isEmpty()) {
			LOG.error("There is no such user with username " + provider.getUsername());
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

}
