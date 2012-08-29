package org.softlang.company.impl.bean;



import org.softlang.company.Container;
import org.softlang.company.Department;
import org.softlang.company.Subunit;

/*#if($Logging || $Precedence)*/
import java.util.Observer;
/*#end*/
import org.softlang.util.ObservableSimpleList;
import org.softlang.util.SimpleLinkedList;


public abstract class ContainerImpl extends ComponentImpl implements Container {

	private ObservableSimpleList<Subunit> subunits =  new ObservableSimpleList<Subunit>(new SimpleLinkedList<Subunit>());
	
	public Iterable<? extends Subunit> subunits() {
		return subunits;
	}
	
	public boolean add(Subunit u) {
		ComponentImpl i = (ComponentImpl)u;
		if (i.getParent()!=null)
			throw new IllegalArgumentException("Attemped re-parenting.");
		i.setParent(this);
		return subunits.add(u);
	}
	
	/*#if($GUI)*/
	public void add(Department department){
	}
	/*#end*/

	public boolean remove(Subunit u) {
		ComponentImpl i = (ComponentImpl)u;
		i.setParent(null);
		return subunits.remove(u);
	}
	
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
