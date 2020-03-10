package com.apress.ravi.chapter6.UserRegistrationSystem;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.Charset;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.apress.ravi.UserRegistrationSystemApplication;
import com.apress.ravi.Rest.UserRegistrationRestController;
import com.apress.ravi.dto.UserDTO;
import com.apress.ravi.repository.UserJpaRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserRegistrationRestController.class)
@ContextConfiguration(classes = UserRegistrationSystemApplication.class)
public class UserRegistrationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	//@Spy
	//private UserRegistrationRestController userRegistrationRestController;
	@MockBean
	private UserJpaRepository userJpaRepositoryMock;
	private MediaType contentType;
	private UserDTO user;
	private Optional<UserDTO> optuser;

	@Before
	public void setup() {
		//userRegistrationRestController = new UserRegistrationRestController();
		//ReflectionTestUtils.setField(userRegistrationRestController, "userJpaRepository", userJpaRepositoryMock);
		contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
		user = new UserDTO();
		user.setName("Ravi Kant Soni");
		user.setAddress("JP Nagar; Bangalore; India");
		user.setEmail("ravikantsoni.author@gmail.com");
		optuser = Optional.of(user);
	}

	@Test
	public void shouldReturnSuccessMessage() throws Exception {
		when(this.userJpaRepositoryMock.findById(1L)).thenReturn(optuser);
//		System.out.println(this.mockMvc.perform(get("/api/user/1")));
//		MvcResult mvcResult = mockMvc.perform(get("/api/user/1")).andExpect(status().isOk()).andReturn();
//		MvcResult mvcResult = mockMvc.perform(get("/api/user/1")).andReturn();
	    //Assert.assertEquals("foo and bar", mvcResult.getResponse().getContentAsString());
//		System.out.println("AAAAAA"+mvcResult.getResponse().getContentAsString());
		this.mockMvc.perform(get("/api/user/1"))
		        .andExpect(status().isOk())
				.andExpect(content().contentType(contentType))
//				.andExpect()
				.andExpect(jsonPath("$.name", is("Ravi Kant Soni")))
				.andExpect(jsonPath("$.address", is("JP Nagar; Bangalore; India")))
				.andExpect(jsonPath("$.email", is("ravikantsoni.author@gmail.com")))
				.andDo(print());
	}
}
