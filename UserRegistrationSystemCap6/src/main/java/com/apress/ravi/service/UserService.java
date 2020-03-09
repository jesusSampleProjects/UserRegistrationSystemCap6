/**
 * 
 */
package com.apress.ravi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.apress.ravi.dto.UserDTO;
import com.apress.ravi.repository.UserJpaRepository;

/**
 * @author urano2
 *
 */

public class UserService {
	@Autowired
	private UserJpaRepository userRepository;
	public List<UserDTO> findAll(){
		List <UserDTO> users = new ArrayList<UserDTO>();
		users = userRepository.findAll();
		return users;
	}
	
	public Page<UserDTO> findAllPaginated(Pageable pageable){
		Page<UserDTO> users = userRepository.findAll(pageable);
		return users;
	}
}
