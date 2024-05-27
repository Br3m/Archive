package com.example.Archive.service;

import com.example.Archive.dto.UserDto;
import com.example.Archive.model.User;

public interface UserService {
	
	User findByUsername(String username);
	User save (UserDto userDto);

}
