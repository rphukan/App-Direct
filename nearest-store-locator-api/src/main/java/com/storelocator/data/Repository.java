package com.storelocator.data;

import java.util.List;

public interface Repository<T, M> {

	/**
	 * retrieve an item
	 * 
	 * @return
	 */
	public T get(M searchCriteria);

	/**
	 * retrieve all the items
	 * 
	 * @return
	 */
	default public List<T> getAll() {
		return null;
	}

	/**
	 * add an item
	 * 
	 * @param item
	 */
	public T add(T item);
}
