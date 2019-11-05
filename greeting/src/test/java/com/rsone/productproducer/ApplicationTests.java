package com.rsone.productproducer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Before
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(new GreetingController());
	}

}
