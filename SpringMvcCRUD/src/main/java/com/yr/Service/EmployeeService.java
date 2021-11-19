package com.yr.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.Dao.EmployeeDao;
import com.yr.entily.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	/**
	 * ²éÑ¯
	 * @return
	 */
	public Collection<Employee> getAll(){
		return employeeDao.getAll();
	}
	
	/**
	 * Ìí¼Ó
	 * @param employee
	 */
	public void save(Employee employee){
		employeeDao.save(employee);
	}
	
	/**
	 * É¾³ý
	 * @param id
	 */
	public void delete(Integer id){
		employeeDao.delete(id);
	}
	
	/**
	 * »ØÏÔ
	 * @param id
	 * @return
	 */
	public Employee get(Integer id){
		return employeeDao.get(id);
	}
	
	public void xiug(Employee employee){
		employeeDao.save(employee);
	}
}
