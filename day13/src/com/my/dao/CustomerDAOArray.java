package com.my.dao;

import com.my.exception.AddException;
import com.my.exception.DuplicateException;
import com.my.exception.FindException;
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
	public void insert(Customer c) throws AddException{
		System.out.println("CustomerDAOArray의 insert()");

		//아이디중복확인
		try {
			Customer c1 = selectById(c.getId());
			throw new DuplicateException("이미 존재하는 아이디입니다");			
		}catch(FindException fe) {
			try {
				customers[cnt] = c;
			}catch(ArrayIndexOutOfBoundsException e) {
				throw new AddException("저장소가 꽉찼습니다");
			}
			cnt++;
		}
	}
	@Override
	public Customer[] selectAll() throws FindException{
		System.out.println("CustomerDAOArray의 selectAll()");
		if(cnt == 0) {
			//return null;
			throw new FindException("고객이 한명도 없습니다");
		}
		Customer[]all = new Customer[cnt];
		System.arraycopy(customers, 0, all, 0, cnt);
		return all;
	}

	@Override
	public Customer selectById(String id) throws FindException{
		for(int i=0; i<cnt; i++) {
			Customer c = customers[i];
			String cId = c.getId();
			if(cId.equals(id)) {
				return c;
			}
		}
		//return null;
		throw new FindException("아이디에 해당하는 고객이 없습니다");
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
