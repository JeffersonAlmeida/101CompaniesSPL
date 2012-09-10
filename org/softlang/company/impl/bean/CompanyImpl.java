package org.softlang.company.impl.bean;

import org.softlang.company.Company;
import org.softlang.visitor.ReturningVisitor;

/*#if($Cut || $Total)*/
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
	public CompanyImpl(){
		super();
	}
	/*#end*/
}
