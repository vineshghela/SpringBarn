package com.qa.barn.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.barn.controller.BarnController;
import com.qa.barn.dto.BarnDto;
import com.qa.barn.persistence.domain.Barn;
import com.qa.barn.service.BarnService;

@SpringBootTest // lets spring know this is a test class
@ActiveProfiles("dev")
public class BarnControllerTest {

	@Autowired
	private BarnController controller;

	@MockBean
	private BarnService service;

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

	@Test
	void createTest() throws Exception {
		when(this.service.create(TEST_BARN_1)).thenReturn(this.mapToDTO(TEST_BARN_1));
		assertThat(new ResponseEntity<BarnDto>(this.mapToDTO(TEST_BARN_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_BARN_1));
		verify(this.service, atLeastOnce()).create(TEST_BARN_1);
	}

}
