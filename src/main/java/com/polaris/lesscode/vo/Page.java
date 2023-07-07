package com.polaris.lesscode.vo;

import java.util.List;

public class Page<T> {

    private Long total;
    
    private List<T> list;
    
	public Page(Long total, List<T> list) {
		this.total = total;
		this.list = list;
	}

	public Page() {
		super();
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
    
}
