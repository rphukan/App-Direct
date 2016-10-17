package com.storelocator.model;

public class Address {

	private String number;

	private String line1;

	private String line2;

	private String postcode;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String format() {
		StringBuilder formattedAddress = new StringBuilder();
		if (null != this.getNumber()) {
			formattedAddress.append(this.getNumber()).append(",");
		}
		if (null != this.getLine1()) {
			formattedAddress.append(this.getLine1()).append(",");
		}
		if (null != this.getLine2()) {
			formattedAddress.append(this.getLine2()).append(",");
		}
		if (null != this.getPostcode()) {
			formattedAddress.append(this.getPostcode());
		}
		return formattedAddress.toString();
	}

}