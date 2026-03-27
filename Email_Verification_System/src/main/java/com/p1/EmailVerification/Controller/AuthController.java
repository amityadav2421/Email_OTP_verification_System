package com.p1.EmailVerification.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.p1.EmailVerification.DTO.RequestDTO;
import com.p1.EmailVerification.DTO.ResponseDTO;
import com.p1.EmailVerification.Service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	UserService userService;
	
	@PostMapping("/user-register")
	public ResponseEntity<ResponseDTO> registerUser(@RequestBody RequestDTO request){
		
		ResponseDTO response = this.userService.registerUser(request);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/user-verify")
	public ResponseEntity<?> Verifyuser(@RequestParam String email, @RequestParam String otp){
		String response = this.userService.Verifyuser(email, otp);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
