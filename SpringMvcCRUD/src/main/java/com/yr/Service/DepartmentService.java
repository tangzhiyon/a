package com.yr.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.Dao.DepartmentDao;
import com.yr.entily.Department;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;
	
	public Collection<Department> getDepartments(){
		return departmentDao.getDepartments();
	}
}
