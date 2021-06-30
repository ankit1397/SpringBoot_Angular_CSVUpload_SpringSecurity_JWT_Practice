package com.bezkoder.springjwt.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bezkoder.springjwt.helper.CSVHelper;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.services.StockService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TestController {

	@Autowired
	private StockService stockService;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@PostMapping("/csv/upload")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<MessageResponse> upload(@RequestBody MultipartFile file) {
		String message = "";
		if (Objects.isNull(file)) {
			message = "Please upload a csv file!"; // If no file chosen but upload button clicked. Handling NullPointerException exception.
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(message));
		} else if (!CSVHelper.hasCSVFormat(file)) {
			message = "File type must be CSV!"; // If file chosen is of type other than CSV
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(message));
		} else {
			try {
				stockService.save(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
			}
		}
	}
}
