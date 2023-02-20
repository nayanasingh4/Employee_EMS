package tech.stl.employee.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.stl.employee.entity.Employee;
import tech.stl.employee.repository.EmployeeRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 

	public Employee save(Employee employee) {
	        String rawPassword = employee.getPassword();
	        String encodedPassword = passwordEncoder.encode(rawPassword);
	        employee.setPassword(encodedPassword);
	        
	        return employeeRepository.save(employee);
	    }
}

