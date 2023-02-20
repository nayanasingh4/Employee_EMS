package tech.stl.employee.jwt;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.stl.employee.entity.Employee;
import tech.stl.employee.repository.EmployeeRepository;



@RestController
public class UserApi {
	
@Autowired private EmployeeRepository employeeRepository;
    
    @PutMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody  Employee employee) {
    	Employee createdUser = employeeRepository.save(employee);
        URI uri = URI.create("/users/" + createdUser.getEmployeeId());
        
        UserDto userDto = new UserDto (createdUser.getEmployeeId(), createdUser.getEmailId());
        
        return ResponseEntity.created(uri).body(userDto);
    }

}