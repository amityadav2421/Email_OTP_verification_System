package com.p1.EmailVerification.DTO;

import lombok.Data;

@Data
public class ResponseDTO {

	private Long userId;
	
	private String username;
	
	private String email;
	
	private boolean verified;
	
	private String message;
}
