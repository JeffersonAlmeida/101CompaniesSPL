package org.softlang.company;

import java.util.List;

/**
 * A company has a name and consists of (possibly nested) departments.
 * This is sufficiently covered by the Container interface.
 * Hence, the present interface is essentially a marker.
 */


public interface Company extends Container {
	
	
		/*#if($GUI)*/
		/*#end*/
	
		/**
		 * Read (say, deserialize) a company
		 */	
		public Company readObject(String filename);
		
		/**
		 * Write (say, serialize) an object.
		 */
		public boolean writeObject(String filename);
		
		public List<Department> getDepts();
	
}
