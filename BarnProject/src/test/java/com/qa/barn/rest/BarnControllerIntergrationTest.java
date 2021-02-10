package com.qa.barn.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.barn.dto.BarnDto;
import com.qa.barn.persistence.domain.Barn;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = { "classpath:Barn-schema.sql",
		"classpath:barn-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class BarnControllerIntergrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonifier;

	@Autowired
	private ModelMapper mapper;

	private BarnDto mapToDTO(Barn barn) {
		return this.mapper.map(barn, BarnDto.class);
	}

	// during out tests here we can create some dummy object that we can use later
	private final Barn TEST_BARN_1 = new Barn(1L, "Red", 100, "London");
	private final Barn TEST_BARN_2 = new Barn(2L, "Green", 100, "Wales");
	private final Barn TEST_BARN_3 = new Barn(3L, "Blue", 100, "Manchester");
	private final Barn TEST_BARN_4 = new Barn(4L, "Yellow", 100, "Scotland");

	private final List<Barn> LISTOFBARNS = List.of(TEST_BARN_1, TEST_BARN_2, TEST_BARN_3, TEST_BARN_4);

	private final String URI = "/barn";

	@Test
	void createTest() throws Exception {
		BarnDto testDto = mapToDTO(new Barn("Red", 100, "London"));
		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDto);

//		RequestBuilder request = MockMvcRequestBuilders.post(URI + "/create").content(testDTOAsJSON)
//				.contentType(MediaType.APPLICATION_JSON);

		RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);

		ResultMatcher checkStatus = status().isCreated();

		BarnDto testSavedDto = mapToDTO(new Barn("Red", 100, "London"));

		testSavedDto.setId(5L);
		String TestSavedDtoAsJson = this.jsonifier.writeValueAsString(testSavedDto);

		ResultMatcher checkBody = content().json(TestSavedDtoAsJson);

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);

	}

}
