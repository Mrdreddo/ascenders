package com.ascenders.base.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ascenders.base.dao.AdminDao;
import com.ascenders.base.entity.Admin;

@Service
public class AdminServiceImpl implements UserDetailsService {
	@Autowired
	private AdminDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = dao.findByAdminEmail(username);

		if (admin != null) {
			return new User(admin.getAdminEmail(), admin.getAdminPassword(), new ArrayList<>());
		}
		throw new UsernameNotFoundException("Invalid credentials");
	}

}
