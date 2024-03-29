package org.softlang.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.softlang.company.Company;
import org.softlang.company.Department;
import org.softlang.company.Employee;
import org.softlang.swing.model.Model;
import org.softlang.swing.view.CompanyView;
import org.softlang.swing.view.DepartmentView;
import org.softlang.swing.view.EmployeeView;
import org.softlang.swing.view.MainView;

public class Controller {

	private Model model;
	private MainView view;

	/**
	 * The controller manages the gui and model changes for the application.
	 * 
	 * @author Tobias Zimmer, Eduard Ditler, Hanna-Marike Reger, Helena
	 *         Swerdlow, Jan Ruether
	 * 
	 * @param model
	 * @param view
	 */
	public Controller(Model model, MainView view) {
		this.model = model;
		this.view = view;
	}

	/**
	 * starts the application.
	 */
	public void start() {
		view.addTreeListener(new CompaniesTreeListener());
		view.setVisible(true);
	}

	/*
	 * Listener for the tree view: Creates the GUI components for a selected
	 * company, department or employee.
	 */
	private class CompaniesTreeListener implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {

			// find sender
			TreePath path = e.getNewLeadSelectionPath();

			if (path != null) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path
						.getLastPathComponent();

				model.setCurrentValue((org.softlang.company.Component) selectedNode.getUserObject());

				JPanel gui;
				if (model.getCurrentValue() != null) {
					if (model.getCurrentValue() instanceof Company) {
						CompanyView comp = new CompanyView(model);
						/*#if($Cut)*/
						comp.addCutListener(new CutListener());
						/*#end*/
						comp.addNameListener(new ChangeListener(
								Controller.this, "name"));
						gui = comp;
					} else if (model.getCurrentValue() instanceof Department) {
						DepartmentView dep = new DepartmentView(model);
						/*#if($Cut)*/
						dep.addCutListener(new CutListener());
						/*#end*/
						dep.addNameListener(new ChangeListener(Controller.this,
								"name"));
						gui = dep;
					} else if (model.getCurrentValue() instanceof Employee) {
						EmployeeView emp = new EmployeeView(model);
						/*#if($Cut)*/
						emp.addCutListener(new CutListener());
						/*#end*/
						emp.addNameListener(new ChangeListener(Controller.this,
								"name"));
						emp.addAddressListener(new ChangeListener(
								Controller.this, "address"));
						/*#if($Total)*/
						emp.addSalaryListener(new ChangeListener(Controller.this, "salary"));
						/*#end*/
						gui = emp;
					} else {
						gui = new JPanel();
					}
				} else {
					gui = new JPanel();
				}

				view.initGui(gui);
			}
		}
	};

	/*#if($Cut)*/
	/*
	 * Listener for the cut buttons
	 */
	private class CutListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.cut();
			/*#if($Total)*/
			view.refresh();
			/*#end*/
		}
	}
	/*#end*/

	/**
	 * If the name has changed, this method changes the model and refreshes the
	 * tree.
	 * 
	 * @param newValue
	 */
	public void nameChanged(String newValue) {
		model.setName(newValue);
		view.refreshTree();
	}

	/**
	 * If the address of an employee has changed, this method changes the model.
	 * 
	 * @param newValue
	 */
	public void addressChanged(String newValue) {
		model.setAddress(newValue);
	}

	/**
	 * If the salary of an employee has changed, this method changes the model.
	 * 
	 * @param newValue
	 */
	public void salaryChanged(String newValue) {
		model.setSalary(newValue);
		view.refresh();
	}
}
