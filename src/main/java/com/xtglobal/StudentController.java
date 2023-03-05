package com.xtglobal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/student")
public class StudentController 
{
	
	@GetMapping("/{studentId}")
	public ResponseEntity<ResponseData> getStudentData(@PathVariable("studentId") Long studentId)
	{
		
		ResponseData response = new ResponseData();
		
		Student s1 = new Student();
		s1.setStudentId(1l);
		s1.setStudentName("ashok");
		s1.setAddress("vizag");
		s1.setCollegeId(1l); 
		
		response.setStudent(s1);
		
		Long collegeId = s1.getCollegeId();
		RestTemplate restTemplate = new RestTemplate();
		String endPoint ="http://localhost:9012/college/{collegeId}";
		
		ResponseEntity<College> data =restTemplate.getForEntity(endPoint,College.class,collegeId);
		
		if(data.getStatusCodeValue()==200)
		{
			College c1 = data.getBody();
			response.setCollege(c1);
		}
		
		return new ResponseEntity<ResponseData>(response,HttpStatus.OK);
		
	}

}
