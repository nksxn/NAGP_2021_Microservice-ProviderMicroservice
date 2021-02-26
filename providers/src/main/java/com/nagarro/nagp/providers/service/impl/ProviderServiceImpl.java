package com.nagarro.nagp.providers.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.nagp.providers.dao.ProviderDao;
import com.nagarro.nagp.providers.entity.Provider;
import com.nagarro.nagp.providers.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	ProviderDao providerDao;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	@Override
	public List<Provider> getAllProviders() {
		return providerDao.getAllProviders();
	}

	@Override
	public void addProvider(Provider provider) {
		LocalDateTime now = LocalDateTime.now();
		provider.setCreationTime(dtf.format(now));
		provider.setModifiedTime(dtf.format(now));
		providerDao.addProvider(provider);
	}

	@Override
	public Provider getProviderByUsername(String username) {
		return providerDao.getProviderByUsername(username);
	}

	@Override
	public boolean updateProvider(Provider provider) {
		LocalDateTime now = LocalDateTime.now();
		provider.setModifiedTime(dtf.format(now));
		return providerDao.updateUser(provider);
	}

}
