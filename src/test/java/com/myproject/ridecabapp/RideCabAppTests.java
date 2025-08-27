package com.myproject.ridecabapp;

import com.myproject.ridecabapp.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RideCabAppTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		emailSenderService.sendEmail("wibika2939@namestal.com",
				"Testing email",
				"This is a cab booking application");
	}
	@Test
	void sendMultipleEmail(){
		String emails[]={
				"aakkashsingh01@gmail.com",
				"aakkashsingh02@gmail.com"
		};
		emailSenderService.sendEmail(emails,"Testing","Uber RIde Booking App");
	}

}
