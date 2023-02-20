CREATE TABLE `Adminn` (
  `adminId` int PRIMARY KEY AUTO_INCREMENT,
  `adminName` varchar(255),
  `emailId` varchar(255),
  `password` varchar(255)
);

CREATE TABLE `Employee` (
  `employeeId` int PRIMARY KEY AUTO_INCREMENT,
  `employeeName` varchar(255),
  `phoneNumber` varchar(255),
  `managerId` int,
  `emailId` varchar(255),
  `password` varchar(255)
);

CREATE TABLE `Manager` (
  `managerId` int PRIMARY KEY AUTO_INCREMENT,
  `managerName` varchar(255),
  `phoneNumber` varchar(255),
  `emailId` varchar(255),
  `password` varchar(255)
);

CREATE TABLE `LeaveRequest` (
  `leaveId` int PRIMARY KEY AUTO_INCREMENT,
  `managerId` int,
  `empId` int,
  `leaveStartDate` varchar(255),
  `leaveEndDate` varchar(255),
  `leaveReason` varchar(255),
  `Status` varchar(255)
);

CREATE TABLE `Task` (
  `taskId` int PRIMARY KEY AUTO_INCREMENT,
  `empId` int,
  `managerId` int,
  `taskDate` varchar(255),
  `assignedTask` varchar(255),
  `taskProgress` varchar(255)
);

ALTER TABLE `Employee` ADD FOREIGN KEY (`managerId`) REFERENCES `Manager` (`managerId`);

ALTER TABLE `LeaveRequest` ADD FOREIGN KEY (`managerId`) REFERENCES `Manager` (`managerId`);

ALTER TABLE `LeaveRequest` ADD FOREIGN KEY (`empId`) REFERENCES `Employee` (`employeeId`);

ALTER TABLE `Task` ADD FOREIGN KEY (`empId`) REFERENCES `Employee` (`employeeId`);

ALTER TABLE `Task` ADD FOREIGN KEY (`managerId`) REFERENCES `Manager` (`managerId`);
