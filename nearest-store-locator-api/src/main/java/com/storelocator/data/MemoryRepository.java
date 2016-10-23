package com.storelocator.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.maps.model.LatLng;
import com.storelocator.model.Store;

/**
 * An in memory repository which stores all the data in an array list. Provide a
 * different implementation if you want data persistence.
 * 
 * @author ranjan
 *
 */
public class MemoryRepository implements Repository<Store, LatLng> {

	/**
	 * logger
	 */
	private static final Logger LOG = Logger.getLogger(MemoryRepository.class.getName());

	/**
	 * the list for storing data
	 */
	private final List<Store> data = new ArrayList<>();

	/**
	 * get the store nearest to a geocode
	 */
	@Override
	public Store get(LatLng geocode) {
		Store nearestStore = findNearest(geocode);
		return nearestStore;
	}

	/**
	 * get all the registered stores
	 */
	@Override
	public List<Store> getAll() {
		return data;
	}

	/**
	 * register a c
	 */
	@Override
	public Store add(Store item) {
		data.add(item);
		return item;
	}

	/**
	 * Find the store nearest to this geocode
	 * 
	 * @param geocode
	 * @return
	 */
	public Store findNearest(LatLng geocode) {
		// customer latitude and longitude
		double lat1 = geocode.lat;
		double lon1 = geocode.lng;
		// hold the nearest distance found till now
		double nearestDist = -1;
		// hold the reference to the nearest store found till now
		Store nearestStore = null;
		for (Store store : data) {
			// latitude and longitude of the store to compare
			double lat2 = store.getLatitude();
			double lon2 = store.getLongitude();
			// distance to the store in comparison
			double dist = Util.haversine(lat1, lon1, lat2, lon2);
			// if the store in comparison is nearer than the previous store or if
			// it is the first store
			if (dist < nearestDist || nearestDist == -1) {
				nearestStore = store;
				nearestDist = dist;
				LOG.log(Level.INFO, " store " + nearestStore.getName() + " found at " + nearestDist + " KM");
			}
		}
		return nearestStore;
	}

}
