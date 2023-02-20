package tech.stl.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tech.stl.employee.entity.LeaveRequest;



public interface LeaveRepository extends CrudRepository<LeaveRequest, Integer> {

	void saveAndFlush(LeaveRequest leaveRequest);



	List<LeaveRequest> findLeaveByEmpId(int empId);



	List<LeaveRequest> findLeaveByManagerId(int managerId);



	List<LeaveRequest> findLeaveByLeaveId(int leaveId);

}
