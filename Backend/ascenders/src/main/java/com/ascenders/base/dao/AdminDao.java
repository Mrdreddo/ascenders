package com.ascenders.base.dao;

import org.springframework.data.repository.CrudRepository;

import com.ascenders.base.entity.Admin;

public interface AdminDao extends CrudRepository<Admin, Integer> {
	
	public Admin findByAdminEmail(String adminEmail); 

}
