package tech.stl.employee.entity;

public class JwtResponse {

	private Employee employee;
	private String jwtToken;

	public Employee getUser() {
		return employee;
	}

	public void setUser(Employee employee) {
		this.employee = employee;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public JwtResponse(Employee employee, String jwtToken) {
		super();
		this.employee = employee;
		this.jwtToken = jwtToken;
	}

}
