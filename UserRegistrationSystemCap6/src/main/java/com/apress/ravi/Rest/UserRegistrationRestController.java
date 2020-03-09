/**
 * 
 */
package com.apress.ravi.Rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/**
 * @author urano2
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apress.ravi.Exception.CustomErrorType;
import com.apress.ravi.dto.UserDTO;
import com.apress.ravi.repository.UserJpaRepository;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {
	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);
	@Autowired
	private UserJpaRepository userJpaRepository;

//public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
//this.userJpaRepository = userJpaRepository;
//}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> listAllUsers() {
		List<UserDTO> users = userJpaRepository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody final UserDTO user) {
		logger.info("Creating User : {}", user);
		if (userJpaRepository.findByName(user.getName()) != null) {
			logger.error("Unable to create. A User with name {} already exist",
					user.getName());
			return new ResponseEntity<UserDTO>(
					new CustomErrorType(
							"Unable to create new user. A User with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userJpaRepository.save(user);
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Long id) {
		Optional<UserDTO> useropt = userJpaRepository.findById(id);
//		if (useropt.isEmpty()) {
//			return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.NO_CONTENT);
//		}
		// before was useropt.isEmpty()
		if (useropt == null) {
			return new ResponseEntity<UserDTO>(new CustomErrorType("User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(useropt.get(), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UserDTO user) {
		// fetch user based on id and set it to currentUser object of type UserDTO
		Optional<UserDTO> optuser = userJpaRepository.findById(id);
		// update currentUser object data with user object data
		// before was useropt.isEmpty()
		if (optuser == null) {
			return new ResponseEntity<UserDTO>(
					new CustomErrorType("Unable to upate. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		UserDTO usrdto = optuser.get();
		usrdto.setName(user.getName());
		usrdto.setAddress(user.getAddress());
		usrdto.setEmail(user.getEmail());
		// save currentUser object
		userJpaRepository.saveAndFlush(usrdto);
		// return ResponseEntity object
		return new ResponseEntity<UserDTO>(usrdto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long id) {
		Optional<UserDTO> useropt = userJpaRepository.findById(id);
		// before was useropt.isEmpty()
		if (useropt == null) {
			return new ResponseEntity<UserDTO>(
					new CustomErrorType("Unable to delete. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		UserDTO user = useropt.get();
		userJpaRepository.deleteById(id);
		return new ResponseEntity<UserDTO>(new CustomErrorType("Deleted User with id " + id + "."),
				HttpStatus.NO_CONTENT);
	}
}