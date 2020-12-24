package com.my.dao;

import com.my.vo.Customer;

public interface CustomerDAO {
	//모든 메서드는 public abstract이 생략됨
	/**
	 * 저장소에 고객정보를 저장한다
	 * @param c 고객객체
	 */
	void insert(Customer c);
	
	/**
	 * 저장소의 모든고객을 반환한다
	 * @return 저장소의 모든고객. 고객이 한명이 없으면 null을 반환한다
	 */
	abstract Customer[] selectAll();
	
	/**
	 * 저장소의 아이디에 해당고객을 반환한다
	 * @param id 아이디
	 * @return 고객객체. 아이디에 해당고객이 없으면 null을 반환한다
	 */
	public abstract Customer selectById(String id);
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	Customer update(Customer c);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Customer delete(String id);
}
