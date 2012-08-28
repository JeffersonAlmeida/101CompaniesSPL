package org.softlang.company.impl.bean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import org.softlang.company.Company;
import org.softlang.company.Department;
import org.softlang.visitor.ReturningVisitor;
import org.softlang.visitor.VoidVisitor;

public class CompanyImpl extends ContainerImpl implements Company {

	/*#if($GUI)*/
	private static final long serialVersionUID = 5691954270577060794L;
	private List<Department> depts;
	/*#end*/
	
	public CompanyImpl(){
		setDepts(new LinkedList<Department>());
	}	
			
	public void setDepts(LinkedList<Department> departments) {
			this.depts = departments;
	}

	public void accept(VoidVisitor v) {
		v.visit(this);
	}

	public <R> R accept(ReturningVisitor<R> v) {
		return v.visit(this);
	}

	
	@Override
	public Company readObject(String filename) {
		Object o = null;

		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fis);
			o = in.readObject();
			in.close();
		} catch (IOException e) {
			System.out.println("IOException:: " + e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:: " + e.getMessage());
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
			System.out.println("IOException - writeObject ");
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public void add(Department department) {
		depts.add(department);
	}

	@Override
	public List<Department> getDepts() {
		return depts;
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
