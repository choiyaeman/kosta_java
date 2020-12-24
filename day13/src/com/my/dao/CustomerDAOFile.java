package com.my.dao;

import com.my.vo.Customer;

public class CustomerDAOFile implements CustomerDAO {

	@Override
	public void insert(Customer c) {
		System.out.println("CustomerDAOFile의 insert()");

	}

	@Override
	public Customer[] selectAll() {
		System.out.println("CustomerDAOFile의 selectAll()");
		return null;
	}

	@Override
	public Customer selectById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer update(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
