package org.softlang.proxy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.softlang.company.*;
import org.softlang.visitor.*;

/**
 * A proxy for companies to enforce access control policy for salaries.
 */
/* package */ class ProxyCompany implements Company, Serializable {

	/*#if($GUI)*/
	/*#end*/
	private static final long serialVersionUID = -200889592677165250L;
	
	private AccessControl context;
	private Company subject;
	
	/*#if($GUI)*/
	/*#end*/
	private List<Department> depts;
	
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
	/*#end*/
	
	@Override
	public Company readObject(String filename) {
		Object o = null;

		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fis);
			o = in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return (Company) o;
	}

	@Override
	public boolean writeObject(String filename) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;

		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public List<Department> getDepts() {
		return depts;
	}

	public void setDepts(List<Department> depts) {
		this.depts = depts;
	}	
	
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
