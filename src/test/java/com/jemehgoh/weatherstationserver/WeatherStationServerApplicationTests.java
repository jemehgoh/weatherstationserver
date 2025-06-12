package com.jemehgoh.weatherstationserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jemehgoh.weatherstationserver.readings.Reading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class WeatherStationServerApplicationTests {

	MockMvc mockMvc;
	ObjectMapper objectMapper;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new WeatherStationController())
				.build();
		this.objectMapper = new ObjectMapper();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void post_validReadings_success() throws Exception {
		Reading reading = new Reading("Sat Jun  7 08:58:22 2025", 23.42,
				7059.28, 84.71, 16382, 0);

		this.mockMvc.perform(post("/post").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reading))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}


}
