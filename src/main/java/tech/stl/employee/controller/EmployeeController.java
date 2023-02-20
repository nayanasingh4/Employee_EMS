package tech.stl.employee.controller;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import tech.stl.employee.entity.Employee;
import tech.stl.employee.entity.LeaveRequest;
import tech.stl.employee.exception.ResourceNotFoundException;
import tech.stl.employee.repository.EmployeeRepository;
import tech.stl.employee.service.EmployeeService;
import tech.stl.employee.value.Task;
import tech.stl.employee.entity.Employee;
import tech.stl.employee.entity.LeaveRequest;
import tech.stl.employee.exception.ResourceNotFoundException;
import tech.stl.employee.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	public EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/employee")
	public ResponseEntity<List<Employee>> getEmployee() {
		List<Employee> list = employeeService.getAllEmployee();
		if (list.size() <= 0) {
			throw new ResourceNotFoundException("Employee list is empty");

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	@GetMapping("/employee/managerid/{managerId}")

	public ResponseEntity<List<Employee>> getEmployeeByManagerId(@PathVariable("managerId") int managerId) {
		List<Employee> list = employeeService.getEmployeeByManagerId(managerId);
		if (list.size() <= 0) {
			throw new ResourceNotFoundException("No Employee present in the list ");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	@GetMapping("/employee/emailid/{emailId}")
	public ResponseEntity<List<Employee>> getByEmailId(@PathVariable("emailId") String emailId) {
		List<Employee> list = employeeService.getEmployeebyemailId(emailId);
		if (list.size() <= 0) {
			throw new ResourceNotFoundException("No Employee present in the list with the given department");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable("employeeId") int employeeId) {
		Optional<Employee> employee = employeeService.getEmployeeById(employeeId);
		if (employee == null) {
			throw new ResourceNotFoundException("Patient is not present with the id:" + employeeId);
		}
		return ResponseEntity.of(Optional.of(employee));
	}

	@PostMapping("/employee")
	public ResponseEntity<Employee> addPatient(@RequestBody Employee employee) {
		Employee b = null;
		try {
			b = this.employeeService.saveemployee(employee);
			System.out.println(employeeService);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
//	@CrossOrigin
//	@PutMapping("/employee/update/{employeeId}")
//	public ResponseEntity<Employee> updateEmployee(@PathVariable int employeeId,@RequestBody Employee employee) {
//        Employee updateEmployee = employeeRepository.findByemployeeId(employeeId);
//        updateEmployee.setEmployeeName(employee.getEmployeeName());
//        updateEmployee.setEmailId(employee.getEmailId());
//        updateEmployee.setPhoneNumber(employee.getPhoneNumber());
//        employeeRepository.saveAndFlush( updateEmployee);
//           return ResponseEntity.ok( updateEmployee);
//       }

	@DeleteMapping("/employee/delete/{employeeId}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") int employeeId) {
		try {
			this.employeeService.deleteEmployeeById(employeeId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException(
				"The requested employee cannot be deleted as it is not present in the list");
	}

	@PostMapping("/leave")
	public ResponseEntity<LeaveRequest> addLeave(@RequestBody LeaveRequest leave) {
		LeaveRequest b = null;
		try {
			b = this.employeeService.saveleave(leave);
			System.out.println(employeeService);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/leave/{empId}")
	public ResponseEntity<List<LeaveRequest>> getLeaveByEmpid(@PathVariable("empId") int empId) {
		List<LeaveRequest> list = employeeService.getLeaveByEmpId(empId);
		if (list.size() <= 0) {
			throw new ResourceNotFoundException("No Employee present in the list with the given department");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	// get leave by managerId
	@GetMapping("/leave/managerId/{managerId}")
	public ResponseEntity<List<LeaveRequest>> getLeaveByManagerId(@PathVariable("managerId") int managerId) {
		List<LeaveRequest> list = employeeService.getLeaveByManagerId(managerId);
		if (list.size() <= 0) {
			throw new ResourceNotFoundException("No Employee present in the list with the given department");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	// get leave by leave id
	@GetMapping("/leave/id/{leaveId}")
	public ResponseEntity<List<LeaveRequest>> getLeaveById(@PathVariable("leaveId") int leaveId) {
		List<LeaveRequest> list = employeeService.getLeaveById(leaveId);
		if (list.size() <= 0) {
			throw new ResourceNotFoundException("No Leave request in the list ");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	@PutMapping("/leave/update/{leaveId}")
	public ResponseEntity<LeaveRequest> updateLeave(@RequestBody LeaveRequest leaveRequest,
			@PathVariable("leaveId") int leaveId) {
		try {
			this.employeeService.updateLeave(leaveRequest, leaveId);
			return ResponseEntity.ok().body(leaveRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException("The requested leave cannot be updated as it is not present in the list");
	}

	@DeleteMapping("/leave/delete/{leaveId}")
	public ResponseEntity<Void> deleteLeave(@PathVariable("leaveId") int leaveId) {
		try {
			this.employeeService.deleteLeaveById(leaveId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException("The requested leave cannot be deleted as it is not present in the list");
	}

	// Get task by employeeid

	@GetMapping("/gettask/empId/{empId}")
	public ResponseEntity<Object> getTaskByEmpId(@PathVariable("empId") int empId) throws URISyntaxException {
		Task[] manager = this.employeeService.getTaskByEmpId(empId);
		if (manager == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(manager);
	}

//	@PutMapping("/gettask/update/{taskId}")
//    public ResponseEntity<Task> updateTask( @RequestBody Task task,@PathVariable("taskId") int taskId) throws URISyntaxException{
//    	return this.employeeService.updateTask(taskId);
//    }

//	@PutMapping("/gettask/update/{taskId}")
//    public void updateTask( @RequestBody Task task,@PathVariable("taskId") int taskId) throws URISyntaxException{
//    	this.employeeService.updateTask(taskId);
//    }
	@PutMapping("/gettask/update/{taskId}")
	public Task updateTaskByTaskId(@PathVariable("taskId") int taskId, @RequestBody Task task) {
		return this.employeeService.updateTask(taskId, task);
	}

}
