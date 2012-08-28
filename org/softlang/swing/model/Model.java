package org.softlang.swing.model;

import org.softlang.company.Company;
import org.softlang.company.Department;
import org.softlang.company.Employee;
import org.softlang.company.factory.Factory;
import org.softlang.company.factory.PojoFactory;
import org.softlang.operations.Cut;
import org.softlang.operations.Total;

/**
 * The data model.
 * 
 * @author Tobias Zimmer
 */
public class Model {

	private Company company;
	private org.softlang.company.Component currentValue;

	/**
	 * Constructor
	 */
	public Model() {
		
		// The serialized object has been copied over from elsewhere	
		company = (org.softlang.company.impl.pojo.CompanyImpl)createSampleCompany(new PojoFactory());
		
		System.out.println("qto dpts: " + company.getDepts().size());
		//company.writeObject("sampleCompany.ser");
		//((CompanyImpl) company).readObject("sampleCompany.ser");
		//company = new CompanyImpl().readObject("sampleCompany.ser");
	}

	/**
	 * This method returns the total value for the current company, department
	 * or employee.
	 * 
	 * @return current total value
	 */
	/*public String getTotal() {
		if (currentValue != null) {
			if (currentValue.isCompany()) {
				return Double.toString(Total.total((Company) currentValue));
			} else if (currentValue.isDepartment()) {
				return Double.toString(Total.total((Department) currentValue));
			} else if (currentValue.isEmployee()) {
				return Double.toString(Total.total((Employee) currentValue));
			} else {
				return "0";
			}
		} else {
			return "0";
		}
	}*/
	
	public String getTotal() {
		if (currentValue != null) {
			if (currentValue instanceof Company) {
				return Double.toString(Total.total((Company) currentValue));
			} else if (currentValue instanceof Department) {
				return Double.toString(Total.total((Department) currentValue));
			} else if (currentValue instanceof Employee) {
				return Double.toString(Total.total((Employee) currentValue));
			} else {
				return "0";
			}
		} else {
			return "0";
		}
	}

	/*public String getTotal() {
		TotalReducer total = new TotalReducer();
		if (currentValue != null) {
			return Double.toString(total.reduce (currentValue));
		}
		else {
			return "0";
		}
	}*/
	
	
	/**
	 * This method cuts the current company, department or employee.
	 */
	public void cut() {
		if (currentValue != null) {
			if (currentValue instanceof Company) {
				Cut.cut((Company) currentValue);
			} else if (currentValue instanceof Department) {
				Cut.cut((Department) currentValue);
			} else if (currentValue instanceof Employee) {
				Cut.cut((Employee) currentValue);
			}
		}
	}

	// ----------------------------------- GETTER & SETTER
	public void setName(String newValue) {
		currentValue.setName(newValue);
	}

	public void setAddress(String newValue) {
		((Employee) currentValue).setAddress(newValue);
	}

	public void setSalary(String newValue) {
		((Employee) currentValue).setSalary(Double.parseDouble(newValue));
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public org.softlang.company.Component getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(org.softlang.company.Component currentValue) {
		this.currentValue = currentValue;
	}
	
static Company createSampleCompany(Factory f) {
		
		// Create company
		Company sampleCompany = f.mkCompany();
		sampleCompany.setName("meganalysis");
		
		// Create all employees
		Employee craig = f.mkEmployee();
		craig.setName("Craig");
		craig.setAddress("Redmond");
		craig.setSalary(123456);
		craig.setManager(true);

		Employee erik = f.mkEmployee();
		erik.setName("Erik");
		erik.setAddress("Utrecht");
		erik.setSalary(12345);

		Employee ralf = f.mkEmployee();
		ralf.setName("Ralf");
		ralf.setAddress("Koblenz");
		ralf.setSalary(1234);		

		Employee ray = f.mkEmployee();
		ray.setName("Ray");
		ray.setAddress("Redmond");
		ray.setSalary(234567);
		ray.setManager(true);

		Employee klaus = f.mkEmployee();
		klaus.setName("Klaus");
		klaus.setAddress("Boston");
		klaus.setSalary(23456);
		klaus.setManager(true);
		
		Employee karl = f.mkEmployee();
		karl.setName("Karl");
		karl.setAddress("Riga");
		karl.setSalary(2345);
		karl.setManager(true);
		
		Employee joe = f.mkEmployee();
		joe.setName("Joe");
		joe.setAddress("Wifi City");
		joe.setSalary(2344);								

		// Create research department
		Department research = f.mkDepartment();
		research.setName("Research");
		research.add(craig);
		research.add(erik);
		research.add(ralf);
		sampleCompany.add(research);
		
	

		// Create development department
		Department development = f.mkDepartment();
		development.setName("Development");
		development.add(ray);
		sampleCompany.add(development);

		// Create sub-department dev1
		Department dev1 = f.mkDepartment();
		dev1.setName("Dev1");
		dev1.add(klaus);
		development.add(dev1);

		// Create sub-department dev11
		Department dev11 = f.mkDepartment();
		dev11.setName("Dev1.1");
		dev11.add(karl);
		dev11.add(joe);
		dev1.add(dev11);
		
		return sampleCompany;
	}

}
