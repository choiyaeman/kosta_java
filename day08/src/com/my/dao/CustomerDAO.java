package com.my.dao;

import com.my.vo.Customer;

public class CustomerDAO {
	private Customer[] customers; //고객저장소
	private int cnt=0;
	public CustomerDAO(int size){
		customers = new Customer[size];
	}
	public  Customer[] selectAll() {
		//return null;
		//return customers;
		
		//저장소의  고객들이 저장된 새로운 배열생성 
		Customer[] cArr = new Customer[cnt];
		
		//저장소의 고객들을 새로운 배열에 복붙
		//for(int i=0; i<cArr.length; i++) {
		//	cArr[i] = customers[i];
		//}
		System.arraycopy(customers, 0, cArr, 0, cnt);
		return cArr;
	}
	public void  insert(Customer c) {
		this.customers[cnt] = c;
		cnt++;
	}
	public Customer selectById(String id) {
		/*
		 * 저장소의 고객ID값과 매개변수ID값이 같은경우
		 * 저장소의 고객객체를 반환
		 */
		for(int i=0; i<cnt; i++) {
			Customer c = customers[i];
			String cId = c.getId();
			//String cId = c.id;
			if(cId.equals(id)) {
				return c;			
			}
		}
		return null;
	}
}
