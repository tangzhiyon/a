package com.yr.Serlvet;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yr.Service.DepartmentService;
import com.yr.Service.EmployeeService;
import com.yr.entily.Employee;

@Controller
public class Conversion {
	
	@Autowired
	private EmployeeService employeeService;
	@Autowired 
	private DepartmentService departmentService; 
	
	@RequestMapping("/conversiontext")
	public String conversiontext(Employee employee,Map<String, Object> map) {
		System.out.println("--"+employee);
		return "list";
	}
}
