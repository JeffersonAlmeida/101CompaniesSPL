package org.softlang.tests;

import org.softlang.company.Company;
import org.softlang.company.factory.*;

/*#if($TotalReducer)*/
import org.softlang.features.TotalReducer;
/*#end*/

/*#if($CommandCut)*/
import org.softlang.command.CutCompany;
/*#end*/

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class Undo {

	/*#if($TotalReducer && $CommandCut)*/
	private void testUndo(Factory f) {
		Company sampleCompany = Basics.createSampleCompany(f);
		TotalReducer total = new TotalReducer();
		double before = total.reduce(sampleCompany);
	    assertEquals(399747, before, 0);
	    CutCompany cut = new CutCompany(sampleCompany);
	    assertEquals(before, total.reduce(sampleCompany), 0);
	    cut.execute();
	    assertEquals(before / 2.0, total.reduce(sampleCompany), 0);
	    cut.undo();
	    assertEquals(before, total.reduce(sampleCompany), 0);
	}
	/*#end*/
	
	/*#if($TotalReducer && $CommandCut)*/
	@Test
	public void testUndoPojo() {
		testUndo(new PojoFactory());
	}
	/*#end*/
	
	/*#if($TotalReducer && $CommandCut)*/
	@Test
	public void testUndoBean() {
		testUndo(new BeanFactory());
	}	
	/*#end*/
}
