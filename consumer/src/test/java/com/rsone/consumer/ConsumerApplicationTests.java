package com.rsone.consumer;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(
		ids = {"com.rsone:greeting:+:stubs"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@DirtiesContext
class ConsumerApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ConsumerController consumerController;

	@StubRunnerPort("greeting")
	int port;


	@Test
	void contextLoads() throws Exception {
		Util.greetingPort= this.port;
		mockMvc.perform(MockMvcRequestBuilders.get("/greeting"))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(Matchers.equalToIgnoringCase("Hello Tchoupi")));
	}

}
