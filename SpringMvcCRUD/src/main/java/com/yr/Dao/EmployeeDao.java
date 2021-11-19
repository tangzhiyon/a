package com.yr.Dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yr.entily.Department;
import com.yr.entily.Employee;

@Repository
public class EmployeeDao {

	private static Map<Integer, Employee> employees = null;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	static{
		employees = new HashMap<Integer, Employee>();

		employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101, "D-AA"),"C:\\Users\\86166\\Desktop\\ÎÄ¼þ¼Ð\\Í¼Æ¬\\qqÍ·Ïñ\\QQÍ¼Æ¬20210318203842.jpg"));
		employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1, new Department(102, "D-BB"),"C:\\Users\\86166\\Desktop\\ÎÄ¼þ¼Ð\\Í¼Æ¬\\qqÍ·Ïñ\\QQÍ¼Æ¬20210318204209.jpg"));
		employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0, new Department(103, "D-CC"),"C:\\Users\\86166\\Desktop\\ÎÄ¼þ¼Ð\\Í¼Æ¬\\qqÍ·Ïñ\\QQÍ¼Æ¬20210408103123.jpg"));
		employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0, new Department(104, "D-DD"),"C:\\Users\\86166\\Desktop\\ÎÄ¼þ¼Ð\\Í¼Æ¬\\qqÍ·Ïñ\\QQÍ¼Æ¬20210719090432.jpg"));
		employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1, new Department(105, "D-EE"),"C:\\Users\\86166\\Desktop\\ÎÄ¼þ¼Ð\\Í¼Æ¬\\qqÍ·Ïñ\\QQÍ¼Æ¬20210812102439.jpg"));
	}
	
	private static Integer initId = 1006;
	
	/**
	 * Ìí¼Ó
	 * @param employee
	 */
	public void save(Employee employee){
		if(employee.getId() == null){
			employee.setId(initId++);
		}
		
		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
	}
	
	/**
	 * ²éÑ¯
	 * @return
	 */
	public Collection<Employee> getAll(){
		return employees.values();
	}
	/**
	 * »ØÏÔ
	 * @param id
	 * @return
	 */
	public Employee get(Integer id){
		return employees.get(id);
	}
	
	/**
	 * É¾³ý
	 * @param id
	 */
	public void delete(Integer id){
		employees.remove(id);
	}
}
