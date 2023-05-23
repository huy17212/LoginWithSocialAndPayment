package com.HTT.company.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HTT.company.entity.Users;
import com.HTT.company.repository.UsersRepository;
import com.HTT.company.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository usersDao;

	@Override
	public List<Users> getAll() {
		return usersDao.findAllUsers();
	}

	@Override
	public Users getById(String id) {
		return usersDao.findByUsersName(id);
	}

	@Override
	public Integer create(Users entity) {
		return usersDao.createUsers(entity);
	}

	@Override
	public Integer delete(String username) {
		return null;
	}

	@Override
	public Integer update(Users entity) {
		return null;
	}

	@Override
	public Users findByUserName(String username) {
		return usersDao.findByUsersName(username);
	}

	@Override
	public Users findByGmail(String gmail) {
		return usersDao.findByGmail(gmail);
	}

}
