package com.logscanner.config;

public class ClusterObject {
	
	public ClusterObject() {
		// TODO Auto-generated constructor stub
	}
	
	private String name;
	public com.logscanner.config.Server[] getData() {
		return data;
	}

	public void setData(com.logscanner.config.Server[] data) {
		this.data = data;
	}

	private com.logscanner.config.Server data[];

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

	

