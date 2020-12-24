package com.my.dao;

import com.my.vo.Customer;

public class CustomerDAOArray implements CustomerDAO {
	private Customer[] customers;
	private int cnt;
	public CustomerDAOArray() {
		customers = new Customer[10];
	}
	public CustomerDAOArray(int size) {
		customers = new Customer[size];
	}
	@Override
	public void insert(Customer c) {
		System.out.println("CustomerDAOArray의 insert()");
//GoodCode		
//		customers[cnt] = c;
//		cnt++;
		customers[cnt++] = c; 
	}

	@Override
	public Customer[] selectAll() {
		System.out.println("CustomerDAOArray의 selectAll()");
		if(cnt == 0) {
			return null;
		}
		Customer[]all = new Customer[cnt];
		System.arraycopy(customers, 0, all, 0, cnt);
		return all;
	}

	@Override
	public Customer selectById(String id) {
		for(int i=0; i<cnt; i++) {
			Customer c = customers[i];
			String cId = c.getId();
			if(cId.equals(id)) {
				return c;
			}
		}
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
