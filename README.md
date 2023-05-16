Counseling Center Management System

The Counseling Center Management System is a Java-based project designed to help a counseling center manage its employees, staff members, interns, and clients more efficiently. The system includes functionality for adding, deleting, and modifying information about these individuals. It also provides basic bank functionality to help the counseling center keep track of its finances.

By using this system, the counseling center can more easily keep track of its staff and clients, assign tasks to employees, and manage its finances. It provides a streamlined approach to record-keeping and data management, which can help the counseling center operate more efficiently. Overall, the Counseling Center Management System is a valuable tool for any counseling center looking to improve its operations and better serve its clients.

Employee Commands

addEmployee: Adds an employee to the system.
deleteEmployee: Deletes an employee from the system.
payEmployee: Records and processes an employee's payment.
changeEmployeeHourlyRate: Updates an employee's hourly rate.
recordEmployeeHours: Records the hours worked by an employee.
restartEmployeeWeeklyHours: Resets an employee's weekly hours.
restartEmployeeMonthlyHours: Resets an employee's monthly hours.
printEmployeeList: Prints a list of all employees in the system.

Staff Commands

addStaff: Adds a staff member to the system.
deleteStaff: Deletes a staff member from the system.
removeClientConnectedToStaff: Removes a client assigned to a staff member.
addClientToStaff: Assigns a client to a staff member.
addInternToStaff: Assigns an intern to a staff member.
deleteStaffsIntern: Removes an intern assigned to a staff member.
changeStaffSalary: Updates a staff member's salary.
printStaffList: Prints a list of all staff members in the system.
printStaffClients: Prints a list of all clients assigned to a staff member.
printStaffInterns: Prints a list of all interns assigned to a staff member.

Intern Commands

addIntern: Adds an intern to the system.
deleteIntern: Deletes an intern from the system.
addClientToIntern: Assigns a client to an intern.
removeInternsClient: Removes a client assigned to an intern.
internChangeSupervisor: Assigns a  new supervisor to an intern.
internDeleteSupervisor: Removes a supervisor assigned to an intern.
receivePaymentFromIntern: Records payment from an intern.
voidInternMonthlyPayment: Voids an intern's monthly payment.
changeInternFee: Updates an intern's fee.
printInternList: Prints a list of all interns in the system.
printInternClients: Prints a list of all clients assigned to an intern.

Client Commands

addClientAssignedToStaff: Adds a client assigned to a staff member.
addAndAssignClientToIntern: Adds and assigns a client to an intern.
deleteClient: Deletes a client from the system.
deleteClientsCounselor: Removes a counselor assigned to a client.
clientChangeStaffCounselor: Assigns a new counselor to a client.
clientMakePayment: Records a payment made by a client.
voidClientPayment: Voids a payment made by a client.
clientChangeFee: Updates a client's fee.
printClientList: Prints a list of all clients in the system.
printClientCounselorName: Prints the name of a client's counselor.

Bank Commands

printBankTotal: Prints the total amount in the company's bank account.

Scanner Input/Output

This project includes basic scanner input/output functionality for user interaction. The commands listed above are all for the main class.
