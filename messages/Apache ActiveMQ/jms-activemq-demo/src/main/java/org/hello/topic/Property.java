package org.hello.topic;

import java.util.Date;

public class Property implements IProperty{

	private String name;

	private Date createTime;

	public Property(String name, Date createTime) {
		super();
		this.name = name;
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
