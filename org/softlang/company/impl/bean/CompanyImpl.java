package org.softlang.company.impl.bean;

import java.util.LinkedList;
import java.util.List;

import org.softlang.company.Company;
import org.softlang.company.Department;
/*#if($Cut || $Total)*/
import org.softlang.visitor.ReturningVisitor;
import org.softlang.visitor.VoidVisitor;
/*#end*/

public class CompanyImpl extends ContainerImpl implements Company {
	
	/*#if($Cut || $Total)*/
	public void accept(VoidVisitor v) {
		v.visit(this);
	}
	public <R> R accept(ReturningVisitor<R> v) {
		return v.visit(this);
	}
	/*#end*/
	
	
	
	/*#if($GUI)*/
	private List<Department> depts;	
	
	public CompanyImpl(){
		setDepts(new LinkedList<Department>());
	}	
	public void setDepts(LinkedList<Department> departments) {
			this.depts = departments;
	}
	@Override
	public void add(Department department) {
		depts.add(department);
	}
	@Override
	public List<Department> getDepts() {
		return depts;
	}
	/**
	 * This method returns the name for the tree-view.
	 */
	@Override
	public String toString(){
		return this.getName();
	}
	/*#end*/
}
