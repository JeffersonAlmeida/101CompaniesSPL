package org.softlang.company.impl.bean;

import org.softlang.company.*;
import org.softlang.visitor.*;

public class EmployeeImpl extends ComponentImpl implements Employee {
	
	private String name;
	private String address;
	private double salary;
	private boolean manager;

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
		this.salary = salary;
		/*#if($Logging || $Precedence)*/
 		setChanged();
 		notifyObservers("salary");
	    /*#end*/
	}	
	
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

	public void accept(VoidVisitor v) {
		v.visit(this);
	}

	public <R> R accept(ReturningVisitor<R> v) {
		return v.visit(this);
	}
	
	
	
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
