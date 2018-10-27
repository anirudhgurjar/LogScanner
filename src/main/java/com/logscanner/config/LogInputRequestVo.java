package com.logscanner.config;

public class LogInputRequestVo {
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	private String group;
	private String host;	
	private String path;
	private String q;
	
	
	@Override
	public String toString() {
	System.out.println(new StringBuffer().append(group).append(",")
			.append(host).append(",").append(path).append(q).toString()
			);
		return super.toString();
	}

}
