package com.nagarro.nagp.providers.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.nagp.providers.dao.ProviderDao;
import com.nagarro.nagp.providers.entity.Provider;
import com.nagarro.nagp.providers.service.ProviderService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private EurekaClient eurekaClient;

	@Resource(name = "restTemp")
	private RestTemplate restTemplate;

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

	@Override
	public void notifyProviders(String serviceRegion) {
		List<Provider> notifyProviders = providerDao.getAllProviders().stream()
				.filter(c -> c.getServiceArea().equalsIgnoreCase(serviceRegion)).collect(Collectors.toList());
		notify(notifyProviders);
	}

	@Override
	public void acceptOrder(String orderCode, String username) {
		String url = "/order/updateProvider/{" + orderCode + "}";
		HttpEntity<String> request = new HttpEntity<>(username);
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("orders", false);
		restTemplate.postForObject(instance.getHomePageUrl() + url, request, null);
	}

	@Override
	public void notifyProvider(String username) {
		Provider provider = getProviderByUsername(username);
		notify(provider);
	}

	private void notify(Provider provider) {
		System.out.println("Handle Notifications for Provider");
	}

	private void notify(List<Provider> notifyProviders) {
		System.out.println("Handle Notifications for all Providers based on Service Area");
	}

	public EurekaClient getEurekaClient() {
		return eurekaClient;
	}

	public void setEurekaClient(EurekaClient eurekaClient) {
		this.eurekaClient = eurekaClient;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
