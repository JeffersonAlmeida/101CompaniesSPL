package org.softlang.tests;

import org.softlang.company.*;
import org.softlang.company.factory.PojoFactory;
/*#if($Cut || $Total || $AccessControl)*/
import org.softlang.features.*;
/*#end*/
/*#if($AccessControl)*/
import org.softlang.proxy.*;
/*#end*/

import org.junit.*;
import static org.junit.Assert.*;

public class Proxying {
	
	/*#if($AccessControl && $TotalReducer)*/
	@Test
	public void testTotal() {
		Company sampleCompany = Basics.createSampleCompany(new PojoFactory());
		AccessControl ac = new AccessControl();
		ac.disableWriteAcccess();
		sampleCompany = ac.deploy(sampleCompany);
		TotalReducer reducer = new TotalReducer();
	    assertEquals(399747, reducer.reduce(sampleCompany), 0);
	}
	/*#end*/

	/*#if($AccessControl && $TotalReducer)*/
	@Test(expected=IllegalArgumentException.class)
	public void testTotalException() {
		Company sampleCompany = Basics.createSampleCompany(new PojoFactory());
		AccessControl ac = new AccessControl();
		ac.disableReadAcccess();
		sampleCompany = ac.deploy(sampleCompany);
		TotalReducer reducer = new TotalReducer();
	    reducer.reduce(sampleCompany);
	}
	/*#end*/
	
	/*#if($AccessControl && $TotalReducer && $SimpleCut)*/
	@Test
	public void testCut() {
		Company sampleCompany = Basics.createSampleCompany(new PojoFactory());
		AccessControl ac = new AccessControl();
		sampleCompany = ac.deploy(sampleCompany);
		TotalReducer total = new TotalReducer();
		SimpleCut cut = new SimpleCut();
		double before = total.reduce(sampleCompany);
		cut.postorder(sampleCompany);
		double after = total.reduce(sampleCompany);
		assertEquals(before / 2.0d, after, 0);
	}
	/*#end*/

	/*#if($AccessControl && $SimpleCut)*/
	@Test(expected=IllegalArgumentException.class)
	public void testCutException() {
		Company sampleCompany = Basics.createSampleCompany(new PojoFactory());
		AccessControl ac = new AccessControl();
		ac.disableWriteAcccess();
		sampleCompany = ac.deploy(sampleCompany);
		SimpleCut cut = new SimpleCut();
		cut.postorder(sampleCompany);
	}
	/*#end*/
}
