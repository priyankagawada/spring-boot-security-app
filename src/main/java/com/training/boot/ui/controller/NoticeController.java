package com.training.boot.ui.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

	
	@GetMapping("/notice")
	public String getNotice() {
		return "Notices broadcasted for patients due to covid";
	}
}
