package tech.stl.employee;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import tech.stl.employee.entity.Employee;
import tech.stl.employee.repository.EmployeeRepository;
import tech.stl.employee.service.EmployeeService;

@SpringBootTest(classes = { ServiceMockitoTest.class })
public class ServiceMockitoTest {

	@Mock
	EmployeeRepository employeeRepository;

	@InjectMocks
	EmployeeService employeeService;

	public List<Employee> employees;
	

}
