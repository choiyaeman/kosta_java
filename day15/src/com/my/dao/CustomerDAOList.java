package com.my.dao;
import java.util.ArrayList;
import java.util.List;

import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;
import com.my.vo.Customer;

public class CustomerDAOList implements CustomerDAO {
	private List<Customer> customers; //고객저장소
	public CustomerDAOList() {
		customers = new ArrayList<Customer>(); //
	}
	public CustomerDAOList(int size) {
		customers = new ArrayList<Customer>(size);
	}
	
	@Override
	public void insert(Customer c) throws AddException {
		try {
			selectById(c.getId());
			throw new AddException("이미 존재하는 아이디입니다");
		} catch (FindException e) {
			//저장소에 추가하기
			customers.add(c);			
		}
	}

	@Override
	public List<Customer> selectAll() throws FindException {
		if(customers.size() == 0) {
			throw new FindException("고객이 한명도 없습니다");
		}
		return customers;		
	}
	@Override
	public Customer selectById(String id) throws FindException {
		for(int i=0; i<customers.size(); i++) {
			Customer c = customers.get(i);
			if(c.getId().equals(id)) {
				return c;
			}
		}
		throw new FindException("아이디에 해당하는 고객이 없습니다");
	}

	@Override
	public Customer update(Customer c) throws ModifyException {
		//1.c의 id와 같은 값을 갖는 저장소에 저장된 고객을 찾는다
		//2.1의 경우가 성공되면 1의 고객정보를 c로 바꾼다
		//  고객정보를 반환한다
		//3.1의 경우가 실패되면  ModifyException을 강제발생시킨다
		
		String cId = c.getId();
		try {
			Customer c1 = selectById(cId); //저장소 고객찾기
			//c1 = c;
			if(!"".equals(c.getName())){//if(c.getName()!=null){
				c1.setName(c.getName());
			}
			
			if(!"".equals(c.getPwd())) {
				c1.setPwd(c.getPwd());
			}
			return c1;
		}catch(FindException e) {
			throw new ModifyException(e.getMessage());
		}
	}

	@Override
	public Customer delete(String id) throws RemoveException{
		int i=0;
		for(; i<customers.size(); i++) {
			Customer c1 = customers.get(i);
			if(c1.getId().equals(id)) { //id에 해당고객을 저장소에서 찾으면
				customers.remove(i);  //저장소의 index를 제거
				return c1;
			}
		}
		throw new RemoveException("삭제 실패!");
		
		
		
//		try {
//			Customer c = selectById(id);
//			int i=0;
//			for(; i<customers.size(); i++) {
//				Customer c1 = customers.get(i);
//				if(c == c1) { //저장소의 위치찾기
//					customers.remove(i);
//					break;
//				}
//			}
//			if(i==customers.size()) {
//				
//			}else {
//				return c;
//			}
//		} catch (FindException e) {
//			throw new RemoveException(e.getMessage());
//		}
		
	}

}
