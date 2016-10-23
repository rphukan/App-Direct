package com.storelocator.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.maps.model.LatLng;
import com.storelocator.data.Repository;
import com.storelocator.geocode.GeocodeService;
import com.storelocator.model.Store;

/**
 * controller for the store REST endpoints
 * 
 * @author ranjan
 *
 */
@RestController
public class StoreLocator {

	/**
	 * the google geocoding service
	 */
	@Autowired
	@Qualifier("geocodeService")
	private GeocodeService geocodeService;

	/**
	 * the data store
	 */
	@Autowired
	@Qualifier("memoryRepo")
	private Repository<Store, LatLng> repo;

	/**
	 * REST endpoint for adding a store
	 * 
	 * @param store
	 */
	@RequestMapping(path = "/store", method = RequestMethod.POST)
	public void addStore(@RequestBody Store store) {
		LatLng geocode = geocodeService.getGeocode(store);
		if (null != geocode) {
			store.setLatitude(geocode.lat);
			store.setLongitude(geocode.lng);
		}
		repo.add(store);
	}

	/**
	 * REST endpoint to find the nearest store from a location marked with
	 * latitude and longitude
	 * 
	 * @param latitude,
	 *            the latitude of the customer
	 * @param longitude,
	 *            the longitude of the customer
	 * @return the nearest store
	 */
	@RequestMapping(path = "/store/{latitude}/{longitude}", method = RequestMethod.GET)
	public Store getStore(@PathVariable double latitude, @PathVariable double longitude) {
		LatLng geocode = new LatLng(latitude, longitude);
		Store store = repo.get(geocode);
		return store;
	}

	/**
	 * REST endpoint for a default request without the customer latitude and
	 * longitude
	 * 
	 * @return returns all the registered stores
	 */
	@RequestMapping(path = "/store", method = RequestMethod.GET)
	public List<Store> getStores() {
		return repo.getAll();
	}

}
