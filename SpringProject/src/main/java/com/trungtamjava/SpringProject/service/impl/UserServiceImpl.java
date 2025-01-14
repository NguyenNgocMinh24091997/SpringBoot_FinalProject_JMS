package com.trungtamjava.SpringProject.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.SpringProject.dao.UserDao;
import com.trungtamjava.SpringProject.entity.User;
import com.trungtamjava.SpringProject.model.UserDTO;
import com.trungtamjava.SpringProject.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public void add(UserDTO userDTO) {
		userDao.add(convertToEntity(userDTO));
	}

	@Override
	public void update(UserDTO userDTO) {
		if (userDTO != null) {
			userDao.update(convertToEntity(userDTO));
		}
	}

	@Override
	public void delete(int id) {
		User user = userDao.getById(id);
		if (user != null) {
			userDao.delete(user);
		}
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userDao.getAllUsers();
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTOs.add(convertToDTO(user));
		}
		return userDTOs;
	}

	@Override
	public UserDTO getById(int id) {
		return convertToDTO(userDao.getById(id));
	}

	@Override
	public UserDTO getByUserName(String userName) {
		return convertToDTO(userDao.getByUserName(userName));
	}

	@Override
	public List<UserDTO> getUserByName(String name) {
		List<User> users = userDao.getUserByName(name);
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTOs.add(convertToDTO(user));
		}
		return userDTOs;
	}

	@Override
	public List<UserDTO> search(String keyword, int maxPerPage, int offset) {
		List<User> users = userDao.search(keyword, maxPerPage, offset);
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();

		for (User user : users) {
			userDTOs.add(convertToDTO(user));
		}
		return userDTOs;
	}

	@Override
	public int count(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	public User convertToEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setAge(userDTO.getAge());
		user.setAddress(userDTO.getAddress());
		user.setEmail(userDTO.getEmail());
		user.setGender(userDTO.getGender());
		user.setPassword(userDTO.getPassword());
		user.setUserName(userDTO.getUserName());
		user.setRole(userDTO.getRole());
		user.setPhoneNum(userDTO.getPhoneNum());
		user.setImageAvatar(userDTO.getImageAvatar());
		return user;
	}

	public UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setAge(user.getAge());
		userDTO.setAddress(user.getAddress());
		userDTO.setEmail(user.getEmail());
		userDTO.setGender(user.getGender());
		userDTO.setPassword(user.getPassword());
		userDTO.setUserName(user.getUserName());
		userDTO.setRole(user.getRole());
		userDTO.setPhoneNum(user.getPhoneNum());
		userDTO.setImageAvatar(user.getImageAvatar());
		return userDTO;
	}

}
