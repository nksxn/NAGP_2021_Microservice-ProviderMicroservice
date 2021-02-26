package com.nagarro.nagp.providers.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.nagarro.nagp.providers.dao.ProviderDao;
import com.nagarro.nagp.providers.entity.Provider;

@Repository
public class ProviderDaoImpl implements ProviderDao {

	private static final Logger LOG = LoggerFactory.getLogger(ProviderDaoImpl.class);

	private static List<Provider> providers = new ArrayList<Provider>();

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	@PostConstruct
	public void init() {
		LOG.info("Creting Dummy data for Users.");
		LocalDateTime now = LocalDateTime.now();
		Provider provider1 = new Provider();
		provider1.setActive(Boolean.TRUE);
		provider1.setFirstName("Vinay");
		provider1.setLastName("Prajapati");
		provider1.setEmail("vinay.prajapati@gmail.com");
		provider1.setPhone("9813232319");
		provider1.setUsername("vinayPrajapati");
		provider1.setPassword("qwerty@123");
		provider1.setExpertise("Carpainter");
		provider1.setCreationTime(dtf.format(now));
		provider1.setModifiedTime(dtf.format(now));
		Provider provider2 = new Provider();
		provider2.setActive(Boolean.TRUE);
		provider2.setFirstName("Gaurav");
		provider2.setLastName("Kansal");
		provider2.setEmail("gaurav.kansal@gmail.com");
		provider2.setPhone("9827632981");
		provider2.setUsername("gauravKansal");
		provider2.setPassword("qwerty@123");
		provider2.setExpertise("Painter");
		provider2.setCreationTime(dtf.format(now));
		provider2.setModifiedTime(dtf.format(now));
		providers.add(provider1);
		providers.add(provider2);
	}

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
