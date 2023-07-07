package com.polaris.lesscode.entity;

import java.util.Date;

import lombok.Data;

@Data
public class BaseEntity {

	protected Long creator;
	
	protected Date createTime;
	
	protected Long updator;
	
	protected Date updateTime;
	
	protected Long version;
	
	protected Integer delFlag;
}
