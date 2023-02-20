package tech.stl.employee.value;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int taskId;
	
	private int empId;
	
	private int managerId;
	
	private LocalDate taskDate;
	
	private String assignedTask;
	
	private String taskProgress;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
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

	public LocalDate getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(LocalDate taskDate) {
		this.taskDate = taskDate;
	}

	public String getAssignedTask() {
		return assignedTask;
	}

	public void setAssignedTask(String assignedTask) {
		this.assignedTask = assignedTask;
	}

	public String getTaskProgress() {
		return taskProgress;
	}

	public void setTaskProgress(String taskProgress) {
		this.taskProgress = taskProgress;
	}

	@Override
	public String toString() {
		return "Leave [taskId=" + taskId + ", empId=" + empId + ", managerId=" + managerId + ", taskDate=" + taskDate
				+ ", assignedTask=" + assignedTask + ", taskProgress=" + taskProgress + "]";
	}

	public Task(int taskId, int empId, int managerId, LocalDate taskDate, String assignedTask, String taskProgress) {
		super();
		this.taskId = taskId;
		this.empId = empId;
		this.managerId = managerId;
		this.taskDate = taskDate;
		this.assignedTask = assignedTask;
		this.taskProgress = taskProgress;
	}

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
