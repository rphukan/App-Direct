package com.storelocator.model;

public class Store {

	private long id;

	private String name;

	private Address address;

	private double latitude;

	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String format() {
		Address address = this.getAddress();
		StringBuilder formattedAddress = new StringBuilder();
		if (null != this.getName()) {
			formattedAddress.append(this.getName()).append(",");
		}
		if (null != address) {
			formattedAddress.append(address.format());
		}
		return formattedAddress.toString();
	}

}