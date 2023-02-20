package tech.stl.employee.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LeaveRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveId;

	private int empId;
	
	private int managerId;
	
	private LocalDate leaveStartDate;
	
	private LocalDate leaveEndDate;
	
	private String leaveReason;
	
	private String status="pending";

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public LocalDate getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(LocalDate leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public LocalDate getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(LocalDate leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LeaveRequest(int leaveId, int empId, int managerId, LocalDate leaveStartDate, LocalDate leaveEndDate,
			String leaveReason, String status) {
		super();
		this.leaveId = leaveId;
		this.empId = empId;
		this.managerId = managerId;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.leaveReason = leaveReason;
		this.status = status;
	}

	@Override
	public String toString() {
		return "LeaveRequest [leaveId=" + leaveId + ", empId=" + empId + ", managerId=" + managerId
				+ ", leaveStartDate=" + leaveStartDate + ", leaveEndDate=" + leaveEndDate + ", leaveReason="
				+ leaveReason + ", status=" + status + "]";
	}

	public LeaveRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	

}
