package tech.stl.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.stl.employee.entity.Employee;
import tech.stl.employee.entity.LeaveRequest;



public interface EmployeeRepository extends JpaRepository<Employee, Integer>  {

	List<Employee> findByemailId(String emailId);
	
	public Employee findByEmailId(String emailId);

	List<Employee> findByManagerId(int managerId);

	Employee findByemployeeId(int employeeId);

	

	
	



}