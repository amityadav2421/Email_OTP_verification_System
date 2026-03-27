package com.p1.EmailVerification.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.p1.EmailVerification.DTO.RequestDTO;
import com.p1.EmailVerification.DTO.ResponseDTO;
import com.p1.EmailVerification.Entity.User;
import com.p1.EmailVerification.Repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailService emailService;

	public ResponseDTO registerUser(RequestDTO request) {

		ResponseDTO response = new ResponseDTO();
		User existingUser = this.userRepo.findByEmail(request.getEmail());
		if (existingUser != null) {
			response.setMessage("User already Registered");
		} else {
			Random r = new Random();
			String otp = String.format("%06d", r.nextInt(1000000));
			User newUser = new User();
			newUser.setUsername(request.getUsername());
			newUser.setEmail(request.getEmail());
			newUser.setOtp(otp);
			newUser.setVerified(false);
			
			User Saveduser = this.userRepo.save(newUser);
			String subject = "Email Verification";
			String body = "Your Verification OTP is:"+Saveduser.getOtp();
			
			this.emailService.senderemail(Saveduser.getEmail(), subject, body);
			response.setUserId(Saveduser.getUserId());
			response.setUsername(Saveduser.getUsername());
			response.setEmail(Saveduser.getEmail());
			response.setMessage("OTP sent successfully");

		}
		return response;
	}
	public String Verifyuser(String email,String otp) {
		String response="";
		User user = this.userRepo.findByEmail(email);
		if(user!=null && user.isVerified()) {
			response = "User is already verified";
		}else if(user!=null && otp.equals(user.getOtp())) {
			user.setVerified(true);
			this.userRepo.save(user);
			response = "user verified succesfully";
			
		}else {
			response = "User is not Verified";
		}
		return response;
	}
}
