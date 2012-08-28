package org.softlang.proxy;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.softlang.company.Company;
import org.softlang.company.Department;
import org.softlang.company.Subunit;
import org.softlang.visitor.ReturningVisitor;
import org.softlang.visitor.VoidVisitor;

/**
 * A proxy for companies to enforce access control policy for salaries.
 */
/* package */ class ProxyCompany implements Company {

	private AccessControl context;
	private Company subject;
	
	/*#if($GUI)*/
	private List<Department> depts;
	/*#end*/
	
	public ProxyCompany() {
		setDepts(new LinkedList<Department>());
	}
	
	/* package */ ProxyCompany(AccessControl context, Company subject) {
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

	public boolean add(Subunit u) {
		u = context.deploy(u);
		return subject.add(u);
	}

	public boolean remove(Subunit u) {
		return subject.remove(u);
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
	public List<Department> getDepts() {
		return depts;
	}
	/*#end*/
	/*#if($GUI)*/
	public void setDepts(List<Department> depts) {
		this.depts = depts;
	}	
	/*#end*/
	
	/*#if($GUI)*/
	/**
	 * This method returns the name for the tree-view.
	 */
	@Override
	public String toString(){
		return this.getName();
	}
	/*#end*/
}
