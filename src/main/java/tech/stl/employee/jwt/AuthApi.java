package tech.stl.employee.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.stl.employee.entity.Employee;
import tech.stl.employee.repository.EmployeeRepository;
import tech.stl.employee.util.JwtTokenUtil;


@CrossOrigin
@RestController
public class AuthApi {
	
	@Autowired AuthenticationManager authManager;
    @Autowired JwtTokenUtil jwtUtil;
    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
            
            Employee user = (Employee) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmailId(), accessToken);
            
            return ResponseEntity.ok().body(response);
            
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    
    @Autowired EmployeeRepository employeeRepository;
    @Autowired PasswordEncoder passwordEncoder;
    
    @PostMapping("/auth/signup/employee")
    public Employee signin(@RequestBody Employee employee) {
        //patient.addRole(new Role(1,"Patient"));
        String rawPassword = employee.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        employee.setPassword(encodedPassword);
        employeeRepository.save(employee);
        return employee;
    }
    
    @CrossOrigin
    @PutMapping("/employee/update/{employeeId}")
	public void updateEmployee(@PathVariable int employeeId,@RequestBody Employee employee) {
        Employee updateEmployee = employeeRepository.findByemployeeId(employeeId);
        updateEmployee.setEmployeeName(employee.getEmployeeName());
        updateEmployee.setEmailId(employee.getEmailId());
        updateEmployee.setPhoneNumber(employee.getPhoneNumber());
        employeeRepository.saveAndFlush( updateEmployee);
       }

}
