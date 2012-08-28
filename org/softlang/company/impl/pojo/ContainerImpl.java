package org.softlang.company.impl.pojo;

import org.softlang.company.*;
import java.util.LinkedList;
import java.util.List;

public abstract class ContainerImpl extends ComponentImpl implements Container {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6168428287475207472L;
	
	private List<Subunit> subunits = new LinkedList<Subunit>();
	
	public Iterable<? extends Subunit> subunits() {
		return subunits;
	}

	public boolean add(Subunit u) {
		return subunits.add(u);
	}

	public boolean remove(Subunit u) {
		return subunits.remove(u);
	}
}
