package com.alibaba.cloud.youxia;

public class SpringCloudAlibabaMessage {

	private int id;

	private String bar;

	public SpringCloudAlibabaMessage() {
	}

	public SpringCloudAlibabaMessage(int id, String bar) {
		this.id = id;
		this.bar = bar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	@Override
	public String toString() {
		return "Foo{" + "id=" + id + ", bar='" + bar + '\'' + '}';
	}

}
