package org.softlang.company.impl.pojo;

import org.softlang.company.*;
import org.softlang.visitor.*;

public abstract class ComponentImpl implements Component {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3300801540093302854L;
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public abstract void accept(VoidVisitor v);
	public abstract <R> R accept(ReturningVisitor<R> v);
}
