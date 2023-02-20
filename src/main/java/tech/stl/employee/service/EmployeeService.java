package tech.stl.employee.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import tech.stl.employee.entity.Employee;
import tech.stl.employee.entity.LeaveRequest;
import tech.stl.employee.repository.EmployeeRepository;
import tech.stl.employee.repository.LeaveRepository;
import tech.stl.employee.value.Task;

@Service
public class EmployeeService {

	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	public LeaveRepository leaveRepository;

	@Autowired
	private RestTemplate restTemplate;

	// add employee
	public Employee saveemployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	// get employee list
	public List<Employee> getAllEmployee() {
		return this.employeeRepository.findAll();
	}

	// get employee by id
	public Optional<Employee> getEmployeeId(int employeeId) {
		return employeeRepository.findById(employeeId);
	}

	// get employee by manager id
	public List<Employee> getEmployeeByManagerId(int managerId) {
		return this.employeeRepository.findByManagerId(managerId);

	}

	// update employee
//	public void updateEmployeeById(Employee employee, int employeeId) {
//		employee.setEmployeeId(employeeId);
//		employeeRepository.saveAndFlush(employee);
//	}

	// delete employee by id
	public void deleteEmployeeById(int employeeId) {
		employeeRepository.deleteById(employeeId);
	}

	// find employee by emailId
	public List<Employee> getEmployeebyemailId(String emailId) {
		return this.employeeRepository.findByemailId(emailId);

	}

	// get employee by id
	public Optional<Employee> getEmployeeById(int employeeId) {
		return employeeRepository.findById(employeeId);
	}

	// add Leave
	public LeaveRequest saveleave(LeaveRequest leave) {
		return leaveRepository.save(leave);
	}

	// get leave by employee id
	public List<LeaveRequest> getLeaveByEmpId(int empId) {
		return this.leaveRepository.findLeaveByEmpId(empId);

	}

	// get leave by manager id
	public List<LeaveRequest> getLeaveByManagerId(int managerId) {
		return this.leaveRepository.findLeaveByManagerId(managerId);

	}

	// get leave by leave id
	public List<LeaveRequest> getLeaveById(int leaveId) {
		return this.leaveRepository.findLeaveByLeaveId(leaveId);

	}

	// update leave
	public void updateLeave(LeaveRequest leaveRequest, int leaveId) {
		leaveRequest.setLeaveId(leaveId);
		leaveRepository.saveAndFlush(leaveRequest);
	}

	// delete leave by id
	public void deleteLeaveById(int leaveId) {
		leaveRepository.deleteById(leaveId);
	}

	// Get task by employee id
	public Task[] getTaskByEmpId(int empId) throws URISyntaxException {
		URI uri = new URI("http://localhost:4424/task/empid" + "/" + empId);
		RestTemplate restTemplate = new RestTemplate();
		Task[] task = restTemplate.getForObject(uri, Task[].class);
		return task;

	}

	public Task updateTask(int taskId, Task task) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Task> entity = new HttpEntity<Task>(task, headers);

		return restTemplate.exchange("http://localhost:4424/task/update/" + taskId, HttpMethod.PUT, entity, Task.class)
				.getBody();
	}


}
