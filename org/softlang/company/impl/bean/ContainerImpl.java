package org.softlang.company.impl.bean;

import java.util.Observer;

import org.softlang.company.Container;
import org.softlang.company.Department;
import org.softlang.company.Subunit;
import org.softlang.util.ObservableSimpleList;
import org.softlang.util.SimpleLinkedList;

public abstract class ContainerImpl extends ComponentImpl implements Container {

	/*#if($Logging || $Precedence)*/
	private ObservableSimpleList<Subunit> subunits =  new ObservableSimpleList<Subunit>(new SimpleLinkedList<Subunit>());
	/*#end*/
	
	/*#if($Logging || $Precedence)*/	
	public Iterable<? extends Subunit> subunits() {
		return subunits;
	}
	/*#end*/
	
	/*#if($Logging || $Precedence)*/
	public boolean add(Subunit u) {
		ComponentImpl i = (ComponentImpl)u;
		if (i.getParent()!=null)
			throw new IllegalArgumentException("Attemped re-parenting.");
		i.setParent(this);
		return subunits.add(u);
	}
	/*#end*/
	
	/*#if($GUI)*/
	public void add(Department department){
	}
	/*#end*/

	/*#if($Logging || $Precedence)*/
	public boolean remove(Subunit u) {
		ComponentImpl i = (ComponentImpl)u;
		i.setParent(null);
		return subunits.remove(u);
	}
	/*#end*/
	
	
	/*#if($Logging || $Precedence)*/
	public void addObserver(Observer o) {
		super.addObserver(o);
		subunits.addObserver(o);
	}
	/*#end*/
	
	/*#if($Logging || $Precedence)*/
	public void deleteObserver(Observer o) {
		super.deleteObserver(o);
		subunits.deleteObserver(o);		
	}
	/*#end*/
	
	/*#if($Logging || $Precedence)*/
	public void deleteObservers() {
		super.deleteObservers();
		subunits.deleteObservers();
	}	
	/*#end*/
}
