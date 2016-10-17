package com.storelocator;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.google.maps.model.LatLng;
import com.storelocator.data.MemoryRepository;
import com.storelocator.data.Repository;
import com.storelocator.geocode.GeocodeService;
import com.storelocator.model.Store;

@SpringBootApplication
public class StoreLocatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreLocatorApplication.class, args);
	}

	@Bean(name = "geocodeService")
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@ConfigurationProperties(prefix = "config")
	public GeocodeService getGeocodeService() {
		GeocodeService service = new GeocodeService();
		return service;
	}

	@Bean(name = "memoryStore")
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public Repository<Store, LatLng> getMemoryStore() {
		return new MemoryRepository();
	}
}
