package com.apress.ravi.chapter6.UserRegistrationSystem;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.apress.ravi.UserRegistrationSystemApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserRegistrationSystemApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserRegistrationSystemApplicationTest {
	
	//logger
	
	@Before
	public void setup() {
	}

	@Test
	public void testFunction() {
		//("testFunction - START")
		assertTrue(true);
		//("testFunction - END")
	}

	@After
	public void teardown() {
	}
}
