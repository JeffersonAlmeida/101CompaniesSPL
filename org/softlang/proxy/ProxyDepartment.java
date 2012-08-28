package org.softlang.proxy;

import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.softlang.company.*;
import org.softlang.visitor.*;

/**
 * A proxy for departments to enforce access control policy for salaries.
 */
/* package */ class ProxyDepartment implements Department {

	private AccessControl context;
	private Department subject;
	
	/*#if($GUI)*/
	private List<Department> subdepts;
	private List<Employee> employees;
	private DefaultMutableTreeNode treeNode;
	/*#end*/
	
	/*#if($GUI)*/
	public ProxyDepartment() {
		super();
		subdepts = new LinkedList<Department>();
		employees = new LinkedList<Employee>();
	}
	/*#end*/
	
	/* package */ ProxyDepartment(AccessControl context, Department subject) {
		this.context = context;
		this.subject = subject;
	}

	public String getName() {
		return subject.getName();
	}

	public void setName(String name) {
		subject.setName(name);
	}
	
	public Iterable<? extends Subunit> subunits() {
		return subject.subunits();
	}

	/*#if($GUI)*/
	public boolean add(Subunit u) {
		if(u instanceof Department){
			u = context.deploy(u);
			return this.subdepts.add((Department) u);
		}else if(u instanceof Employee){
			return this.employees.add((Employee) u);
		}
		return false;
	}
	/*#end*/
	
	/*#if(!$GUI)*/
	/*public boolean add(Subunit u) {
		u = context.deploy(u);
		return subject.add(u);
	}*/
	/*#end*/
	
	public boolean remove(Subunit u) {
		return subject.remove(u);
	}
	public Employee getManager() {
		return subject.getManager();
	}		
	// Delegation is NOT appropriate here.
	public void accept(VoidVisitor v) {
		v.visit(this);
	}
	// Delegation is NOT appropriate here.
	public <R> R accept(ReturningVisitor<R> v) {
		return v.visit(this);
	}
	
	
	

	/*#if($GUI)*/
	public void setSubdepts(List<Department> subdepts) {
		this.subdepts = subdepts;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public List<Department> getSubdepts() {
		return subdepts;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setTreeNode(DefaultMutableTreeNode treeNode) {
		this.treeNode = treeNode;
	}
	public DefaultMutableTreeNode getTreeNode() {
		return treeNode;
	}
	/**
	 * This method returns the name for the tree-view.
	 */
	@Override
	public String toString() {
		String treeName = this.getName();
		return treeName;
	}
	/*#end*/
}
