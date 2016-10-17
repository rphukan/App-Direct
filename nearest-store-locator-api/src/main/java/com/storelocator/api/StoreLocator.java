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
 * the controller for all the REST enpoints
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
	@Qualifier("memoryStore")
	private Repository<Store, LatLng> store;

	/**
	 * REST endpoint for adding a shop
	 * 
	 * @param shop
	 */
	@RequestMapping(path = "/shop", method = RequestMethod.POST)
	public void addShop(@RequestBody Store shop) {
		LatLng geocode = geocodeService.getGeocode(shop);
		if (null != geocode) {
			shop.setLatitude(geocode.lat);
			shop.setLongitude(geocode.lng);
		}
		store.add(shop);
	}

	/**
	 * REST endpoint to find the nearest shop from a location marked with
	 * latitude and longitude
	 * 
	 * @param latitude,
	 *            the latitude of the customer
	 * @param longitude,
	 *            the longitude of the customer
	 * @return the nearest shop
	 */
	@RequestMapping(path = "/shop/{latitude}/{longitude}", method = RequestMethod.GET)
	public Store getShop(@PathVariable double latitude, @PathVariable double longitude) {
		LatLng geocode = new LatLng(latitude, longitude);
		Store shop = store.get(geocode);
		return shop;
	}

	/**
	 * REST endpoint for a default request without the customer latitude and
	 * longitude
	 * 
	 * @return returns all the registered shops
	 */
	@RequestMapping(path = "/shop", method = RequestMethod.GET)
	public List<Store> getShops() {
		return store.getAll();
	}

}
