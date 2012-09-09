package org.softlang.company.impl.bean;



import org.softlang.company.Container;
import org.softlang.company.Department;
import org.softlang.company.Subunit;

/*#if($Logging || $Precedence)*/
import java.util.LinkedList;
import java.util.List;
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
		/*#if($GUI)*/
		if(u instanceof Department){
			this.depts.add((Department) u);
		}
		/*#end*/
		return subunits.add(u);
	}
	
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
	
	/*#if($GUI)*/
	private List<Department> depts;	
	public ContainerImpl(){
		this.depts = new LinkedList<Department>();
	}
	public void setDepts(LinkedList<Department> departments) {
			this.depts = departments;
	}
	public void add(Department department) {
		depts.add(department);
	}
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
