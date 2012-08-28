package org.softlang.company;

/**
 * This class provides methods for the identification of company-objects in
 * order to prevent "instanceof".
 * 
 * @author Tobias
 */
public interface Basic {

	/**
	 * @return true, if the object is a company.
	 */
	public boolean isCompany();

	/**
	 * @return true, if the object is a department.
	 */
	public boolean isDepartment();

	/**
	 * @return true, if the object is an employee.
	 */
	public boolean isEmployee();
	
	public void setName(String newName);
}
