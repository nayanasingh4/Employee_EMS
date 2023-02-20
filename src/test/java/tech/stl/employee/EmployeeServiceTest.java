package tech.stl.employee;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.Task;
import org.springframework.web.client.RestTemplate;

import tech.stl.employee.entity.Employee;
import tech.stl.employee.entity.LeaveRequest;
import tech.stl.employee.repository.EmployeeRepository;
import tech.stl.employee.repository.LeaveRepository;
import tech.stl.employee.service.EmployeeService;

@SpringBootTest(classes = { ServiceMockitoTest.class })
class EmployeeServiceTest {
	@Mock
	EmployeeRepository employeeRepository;
	@Mock
	LeaveRepository leaveRepository;

	@InjectMocks
	EmployeeService employeeService;

	public List<Employee> employees;

	@Test
	void testSaveemployee() {
		Employee emp = new Employee(1, "Ved", "2345678001", 2, "ved@gmail.com", "12345");
		when(employeeRepository.save(emp)).thenReturn(emp);
		assertEquals(emp, employeeService.saveemployee(emp));
	}

	@Test
	void testGetAllEmployee() {
		List<Employee> employees = new ArrayList<>();
		Employee ved = new Employee(1, "Ved", "2345678001", 2, "ved@gmail.com", "12345");
		Employee anshu = new Employee(2, "Anshu", "2345678001", 2, "anshu@gmail.com", "12345");
		Employee babli = new Employee(3, "Babli", "2345678001", 2, "babli@gmail.com", "12345");
		Employee max = new Employee(4, "Max", "2345678001", 2, "max@gmail.com", "12345");
		employees.add(ved);
		employees.add(anshu);
		employees.add(babli);
		employees.add(max);

		when(employeeRepository.findAll()).thenReturn(employees);
		assertEquals(employees.size(), employeeService.getAllEmployee().size());
	}

	@Test
	void testGetEmployeeId() {
		Optional<Employee> max = Optional.of(new Employee(4, "Max", "2345678001", 2, "max@gmail.com", "12345"));
		when(employeeRepository.findById(4)).thenReturn(max);
		assertEquals(max, employeeService.getEmployeeById(4));
	}

	@Test
	void testGetEmployeeByManagerId() {
		List<Employee> employees = new ArrayList<>();
		Employee ved = new Employee(1, "Ved", "2345678001", 2, "ved@gmail.com", "12345");
		Employee anshu = new Employee(2, "Anshu", "2345678001", 2, "anshu@gmail.com", "12345");
		Employee babli = new Employee(3, "Babli", "2345678001", 2, "babli@gmail.com", "12345");
		Employee max = new Employee(4, "Max", "2345678001", 2, "max@gmail.com", "12345");
		employees.add(ved);
		employees.add(anshu);
		employees.add(babli);
		employees.add(max);
		when(employeeRepository.findByManagerId(2)).thenReturn(employees);
		assertEquals(employees, employeeService.getEmployeeByManagerId(2));
	}

	@Test
	void testDeleteEmployeeById() {
		employeeRepository.deleteById(4);
		assertEquals(Optional.empty(), employeeService.getEmployeeById(4));
	}

	@Test
	void testGetEmployeebyemailId() {
		List<Employee> employees = new ArrayList<>();
		Employee ved = new Employee(1, "Ved", "2345678001", 2, "ved@gmail.com", "12345");
		Employee anshu = new Employee(2, "Anshu", "2345678001", 2, "anshu@gmail.com", "12345");
		employees.add(ved);
		employees.add(anshu);
		String email = "ved@gmail.com";
		when(employeeRepository.findByemailId("ved@gmail.com")).thenReturn(employees);
		assertEquals(ved, employeeService.getEmployeebyemailId(email).get(0));
		assertNotEquals(anshu, employeeService.getEmployeebyemailId(email).get(0));
	}

	@Test
	void testGetEmployeeById() {
		Optional<Employee> ved = Optional.of(new Employee(1, "Ved", "2345678001", 2, "ved@gmail.com", "12345"));
		when(employeeRepository.findById(1)).thenReturn(ved);
		assertEquals(ved, employeeService.getEmployeeById(1));
	}

	@Test
	void testSaveleave() {
		LocalDate start = LocalDate.of(2023, 3, 2);
		LocalDate end = LocalDate.of(2023, 3, 3);
		LeaveRequest leave = new LeaveRequest(1, 2, 3, start, end, "Reason", "status");
		when(leaveRepository.save(leave)).thenReturn(leave);
		assertEquals(leave, employeeService.saveleave(leave));
	}

	@Test
	void testGetLeaveByEmpId() {
		LocalDate start = LocalDate.of(2023, 3, 2);
		LocalDate end = LocalDate.of(2023, 3, 3);
		LeaveRequest leave = new LeaveRequest(1, 2, 3, start, end, "Reason", "status");
		LeaveRequest leave1 = new LeaveRequest(2, 2, 3, start, end, "Reason1", "status1");
		when(leaveRepository.save(leave)).thenReturn(leave);
		when(leaveRepository.save(leave)).thenReturn(leave1);
		List<LeaveRequest> leaves = new ArrayList<>();
		leaves.add(leave);
		leaves.add(leave1);
		when(leaveRepository.findLeaveByEmpId(2)).thenReturn(leaves);
		assertEquals(leaves, employeeService.getLeaveByEmpId(2));
	}

	@Test
	void testGetLeaveByManagerId() {
		LocalDate start = LocalDate.of(2023, 3, 2);
		LocalDate end = LocalDate.of(2023, 3, 3);
		LeaveRequest leave = new LeaveRequest(1, 2, 3, start, end, "Reason", "status");
		LeaveRequest leave1 = new LeaveRequest(2, 2, 3, start, end, "Reason1", "status1");
		when(leaveRepository.save(leave)).thenReturn(leave);
		when(leaveRepository.save(leave)).thenReturn(leave1);
		List<LeaveRequest> leaves = new ArrayList<>();
		leaves.add(leave);
		leaves.add(leave1);
		when(leaveRepository.findLeaveByManagerId(3)).thenReturn(leaves);
		assertEquals(leaves, employeeService.getLeaveByManagerId(3));
	}

	@Test
	void testGetLeaveById() {
		LocalDate start = LocalDate.of(2023, 3, 2);
		LocalDate end = LocalDate.of(2023, 3, 3);
		LeaveRequest leave = new LeaveRequest(1, 2, 3, start, end, "Reason", "status");
		LeaveRequest leave1 = new LeaveRequest(2, 2, 3, start, end, "Reason1", "status1");
		when(leaveRepository.save(leave)).thenReturn(leave);
		when(leaveRepository.save(leave1)).thenReturn(leave1);
		List<LeaveRequest> leaves = new ArrayList<>();
		leaves.add(leave);
		leaves.add(leave1);
		when(leaveRepository.findLeaveByLeaveId(1)).thenReturn(leaves);
		assertEquals(leaves, employeeService.getLeaveById(1));
	}

	@Test
	void testUpdateLeave() {
		LocalDate start = LocalDate.of(2023, 3, 2);
		LocalDate end = LocalDate.of(2023, 3, 3);
		LeaveRequest leave = new LeaveRequest(1, 2, 3, start, end, "Reason", "status");
		when(leaveRepository.save(leave)).thenReturn(leave);
		employeeService.updateLeave(leave, 1);
		assertNotEquals(leave, employeeService.getLeaveById(1));
	}

	@Test
	void testDeleteLeaveById() {
		leaveRepository.deleteById(1);
		employeeService.deleteLeaveById(1);
		assert (employeeService.getLeaveById(1).isEmpty());
	}
	
	/*
	 * @Test void testGetTaskByEmpId() { }
	 * 
	 * @Test void testUpdateTask() { fail("Not yet implemented"); }
	 */
}
