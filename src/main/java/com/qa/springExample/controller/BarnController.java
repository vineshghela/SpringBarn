package com.qa.springExample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barn")
public class BarnController {

	@GetMapping("/str")
	public String HeyFriends() {
		return "Hello from barn";
	}
}
