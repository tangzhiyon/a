package com.yr.Serlvet;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;

import com.yr.entily.Department;
import com.yr.entily.Employee;

@Controller
public class Employeeconvers implements Converter<String, Employee>{

	@Override
	public Employee convert(String arg0) {
		if(arg0 !=null) {
			String [] vals =arg0.split("-");
			if(vals !=null && vals.length==4) {
				String lastName =vals[0];
				String email =vals[1];
				Integer gender=Integer.parseInt(vals[2]);
				Department department =new Department();
				department.setId(Integer.parseInt(vals[3]));
				
				String head =vals[4];
				Employee employee =new Employee(null,lastName,email,gender,department,head);
				System.out.println("×Ô¶¨Òåemployee:"+employee);
				return employee;
				
			}
		}
		return null;
	}
	
}
