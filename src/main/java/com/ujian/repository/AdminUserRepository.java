package com.ujian.repository;

import org.springframework.data.repository.CrudRepository;

import com.ujian.entity.AdminUser;



public interface AdminUserRepository extends CrudRepository<AdminUser, Long> {
	public AdminUser findByUsername(String username);
}
