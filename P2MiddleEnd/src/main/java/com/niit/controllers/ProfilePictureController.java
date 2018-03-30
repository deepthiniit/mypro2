package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.ProfilePictureDao;
import com.niit.model.ProfilePicture;

@RestController
public class ProfilePictureController {
	
	@Autowired
	private ProfilePictureDao profilepictureDao;
	@Autowired
	private SessionFactory sessionFactory;
	public ProfilePictureController(){
		System.out.println("ProfilePictureController bean is created");
	}
@RequestMapping(value="/getimage/{email:.+}",method=RequestMethod.GET)
public @ResponseBody byte[] getImage(@PathVariable String email,HttpSession session){
	System.out.println(email);
	String auth=(String) session.getAttribute("loginId");
	if(auth==null){
		return null;
	}
	ProfilePicture profilePicture=profilepictureDao.getImage(email);
	if(profilePicture==null){
		return null;
		
	}else
		return profilePicture.getImage();
}
}
