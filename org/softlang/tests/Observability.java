package org.softlang.tests;

import org.softlang.company.impl.bean.CompanyImpl;
import org.softlang.company.factory.BeanFactory;
/*#if($Logging || $Cut || $Total || $Precedence)*/
import org.softlang.features.*;
/*#end*/
import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * Test cases related to observability
 */
public class Observability {

	private CompanyImpl sampleCompany;

	@Before
	public void createSampleCompany() {
		sampleCompany = (CompanyImpl)Basics.createSampleCompany(new BeanFactory());
	}

	/*#if($Logging && $CutWhatever)*/
	/**
	 * Test Logging feature
	 */
	@Test
	public void testLogging() {
		Logging log = new Logging();
		((CompanyImpl)sampleCompany).addObserver(log);
		SimpleCut cut = new SimpleCut();
		cut.postorder(sampleCompany);
		assertEquals(7, log.getSize());		
	}
	/*#end*/
	
	
	/*#if($Precedence && $CutNoManager)*/
	/**
	 * Test Precedence feature
	 */
	@Test
	public void testPrecedence() {
		Precedence prec = new Precedence();
		((CompanyImpl)sampleCompany).addObserver(prec);
		OrderedCut cut = new OrderedCut();
		cut.postorder(sampleCompany);
	}	
	 /*#end*/
	
	/**
	 * If all class remains empty because variability. 
	 * We have at least one test in order to compile the class.
	 */
	 public void test() {
	 }
}
