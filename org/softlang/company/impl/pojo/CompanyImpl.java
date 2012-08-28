package org.softlang.company.impl.pojo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import org.softlang.company.Company;
import org.softlang.company.Department;
import org.softlang.company.Subunit;
import org.softlang.visitor.ReturningVisitor;
import org.softlang.visitor.VoidVisitor;

public class CompanyImpl extends ContainerImpl implements Company{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = -200889592677165250L;
	/*#if($GUI)*/
	/*#end*/
	private List<Department> depts;
	
	public CompanyImpl() {
		depts = new LinkedList<Department>();
	}

	/**
	 * Enforce the constraint a company can only aggregate departments
	 */
	public boolean add(Subunit u) {
		if (!(u instanceof Department))
			throw new IllegalArgumentException();
		return depts.add((Department) u);
	}
	
	 /* public boolean add(Subunit u){
		 if (!(u instanceof Department)){
			throw new IllegalArgumentException();
			#if($GUI) return depts.add((Department)u);#end
			#if(!$GUI)return super.add(u); /*#end
	     }
      }*/

	/*#if($GUI)*/
	public boolean add(Department department) {
		return depts.add(department);
	}
	/*#end*/
	
	public void accept(VoidVisitor v) {
		v.visit(this);
	}

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
