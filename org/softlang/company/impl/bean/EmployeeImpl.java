package org.softlang.company.impl.bean;

import org.softlang.company.*;

/*#if($Cut || $Total)*/
import org.softlang.visitor.*;
/*#end*/

public class EmployeeImpl extends ComponentImpl implements Employee {
	
	private String name;
	private String address;
	private double salary;
	private boolean manager;
	
	/*#if($Precedence)*/
	private double oldSalary;
	public double getOldSalary() {
		return oldSalary;
	}
	public void setOldSalary(double oldSalary) {
		this.oldSalary = oldSalary;
	}
	/*#end*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		/*#if($Logging || $Precedence)*/
		setChanged();
		notifyObservers("name");
		/*#end*/
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		/*#if($Logging || $Precedence)*/
		setChanged();
		notifyObservers("address");
	    /*#end*/
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		if(salary!=this.getSalary()){
			/*#if($Precedence)*/
			this.oldSalary = this.salary; 
			/*#end*/
			this.salary = salary;
			/*#if($Logging || $Precedence)*/
	 		setChanged();
	 		notifyObservers("salary");
		    /*#end*/
		}	
	}	
	
	/*#if($Precedence)*/
	public void setSalaryWithOldSalary(){
		this.salary = this.oldSalary;
	}
	/*#end*/
	
	public boolean getManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
		/*#if($Logging || $Precedence)*/
		setChanged();
		notifyObservers("manager");
		/*#end*/
	}

	/*#if($Cut || $Total)*/
	public void accept(VoidVisitor v) {
		v.visit(this);
	}
	public <R> R accept(ReturningVisitor<R> v) {
		return v.visit(this);
	}
	/*#end*/
	
	
	/*#if($GUI)*/
	/**
	 * This method returns the name for the tree-view.
	 */
	@Override
	public String toString(){
		String treeName = this.getName();
		if (manager) {
			return treeName + " (Manager)";
		}
		return treeName;
	}
	/*#end*/

}
