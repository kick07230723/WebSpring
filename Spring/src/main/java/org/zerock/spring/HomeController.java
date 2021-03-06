package org.zerock.spring;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.service.Hello;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource(name="english")
//	@Inject
	private Hello service;
	
//	@Autowired
	@Inject
	private DataSource ds; 
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		String greeting = service.sayHello();
		
		try {
			Connection con=ds.getConnection();
			System.out.println("========");
			System.out.println(con);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		model.addAttribute("serverTime", formattedDate +" : "+greeting );
		
		return "home";
	}
	
}
