package com.cagemini.lifescience;

import com.cagemini.lifescience.dao.AdminRepository;
import com.cagemini.lifescience.entity.Admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootApplication

public class LifescienceApplication {

	public static void main(String[] args) throws ParseException {

		ApplicationContext ctx = SpringApplication.run(LifescienceApplication.class, args);

//
//		AdminRepository adminRepository = ctx.getBean(AdminRepository.class);
//
//		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
//		adminRepository.save(new Admin("aziz", "sanae", df.parse("01/01/2001"), "06675677", "femme", "true", "sanae@gmail.com", "1234", true, "sanae.jpg"));
//


	}
}
