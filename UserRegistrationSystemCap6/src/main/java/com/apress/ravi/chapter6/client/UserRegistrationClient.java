package com.apress.ravi.chapter6.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.apress.ravi.dto.UserDTO;

public class UserRegistrationClient {
	private static RestTemplate restTemplate = new RestTemplate();
	private static final String USER_REGISTRATION_BASE_URL = "http://localhost:8080/api/user/";

	public UserDTO getUserById(final Long userId) {
		return restTemplate.getForObject(USER_REGISTRATION_BASE_URL + "/{id}", UserDTO.class, userId);
	}

	// returns no content 204
	public UserDTO[] getAllUsers() {
		return restTemplate.getForObject(USER_REGISTRATION_BASE_URL, UserDTO[].class);
	}

	public UserDTO createUser(final UserDTO user) {
		return restTemplate.postForObject(USER_REGISTRATION_BASE_URL, user, UserDTO.class);
	}

	public void updateUser(final Long userId, final UserDTO user) {
		restTemplate.put(USER_REGISTRATION_BASE_URL + "/{id}", user, userId);
	}

	// returns no content 204
	public void deleteUser(final Long userId) {
		restTemplate.delete(USER_REGISTRATION_BASE_URL + "/{id}", userId);
	}

	public ResponseEntity<UserDTO> getUserByIdUsingExchangeAPI(final Long userId) {
		HttpEntity<UserDTO> httpEntity = new HttpEntity<UserDTO>(new UserDTO());
		return restTemplate.exchange(USER_REGISTRATION_BASE_URL + "/{id}", HttpMethod.GET, httpEntity, UserDTO.class,
				userId);
	}

	// to test GET
//	public static void main(String[] args) {
//		UserRegistrationClient userRegistrationClient = new UserRegistrationClient();
//		UserDTO user = userRegistrationClient.getUserById(1L);
//		System.out.println("User-ID" + user.getId() + " User-Name" + user.getName());
//	}

	// to test POST
//	public static void main(String[] args) {
//		UserRegistrationClient userRegistrationClient = new UserRegistrationClient();
//		UserDTO user = new UserDTO();
//		user.setName("Soniya Singh");
//		user.setAddress("JP Nagar; Bangalore; India");
//		user.setEmail("test@test.com");
//		UserDTO newUser = userRegistrationClient.createUser(user);
//		System.out.println(newUser.getId());
//	}

	// to test PUT
//	public static void main(String[] args) {
//		UserRegistrationClient userRegistrationClient = new UserRegistrationClient();
//		UserDTO user = userRegistrationClient.getUserById(1L);
//		System.out.println("old user name: " + user.getName());
//		user.setName("Ravi Kant Soni");
//		userRegistrationClient.updateUser(1L, user);
//		System.out.println("updated user name: " + user.getName());
//	}

	// to test DELETE
//	public static void main(String[] args) {
//		UserRegistrationClient userRegistrationClient = new UserRegistrationClient();
//		System.out.println("Old Users List: " + userRegistrationClient.getAllUsers().length);
//		userRegistrationClient.deleteUser(1L);
//		System.out.println("New Users List: " + userRegistrationClient.getAllUsers().length);
//	}

	// to test RestTemplate exchange API
	public static void main(String[] args) {
		UserRegistrationClient userRegistrationClient = new UserRegistrationClient();
		ResponseEntity<UserDTO> responseEntity = userRegistrationClient.getUserByIdUsingExchangeAPI(1L);
		UserDTO user = responseEntity.getBody();
		System.out.println(user.getName());
	}

}
