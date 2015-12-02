	package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.data.Item;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class MedicationOverView extends NavigatorContainer implements View{

	private Table medicationTable;
	private HorizontalLayout topHorizontalLayout;
	private Button insertDrug;
	private Button editDrug;
	private Button deleteDrug;
	private VerticalLayout bodyVerticalLayout;
	/**
	 * 
	 */
	public MedicationOverView() {
		super();
		createTopHorizontalLayout();
		createBodyVerticalLayout();
		
		addComponent(topHorizontalLayout);
		addComponent(bodyVerticalLayout);
		
		
		
	}

	/**
	 * Create the Horizontal Layout
	 */
	private void createTopHorizontalLayout(){
		topHorizontalLayout=new HorizontalLayout();
		createButtons();
		topHorizontalLayout.addComponent(insertDrug);
		topHorizontalLayout.addComponent(editDrug);
		topHorizontalLayout.addComponent(deleteDrug);
	}
	
	/**
	 * Create the Body VerticalLayout
	 */
	private void createBodyVerticalLayout(){
		bodyVerticalLayout=new VerticalLayout();
		createTable();
		bodyVerticalLayout.addComponent(medicationTable);
	}
	/**
	 * create three Buttons 
	 */
	private void createButtons(){
		
		
		insertDrug=new Button("Insert", new ThemeResource("Icons/Insert.png"));
		editDrug=new Button("Edit", new ThemeResource("Icons/Edit.png"));
		deleteDrug=new Button("Delete", new ThemeResource("Icons/Delete.png"));
		editDrug.setEnabled(false);
		deleteDrug.setEnabled(false);
	}
	
	/**
	 * Create medication Table with its headers.
	 */
	private void createTable(){
		medicationTable=new Table();
		medicationTable.addContainerProperty("Drug", String.class, null);
		medicationTable.addContainerProperty("Dose", String.class, null);
		medicationTable.addContainerProperty("Schema", String.class, null);
		medicationTable.addContainerProperty("Note", String.class, null);
		
		Object newItemId = medicationTable.addItem();
		Item row1 = medicationTable.getItem(newItemId);
		row1.getItemProperty("Drug").setValue("Brufen");
		row1.getItemProperty("Dose").setValue("400 mg");
		row1.getItemProperty("Schema").setValue("1-1-1-0");
		row1.getItemProperty("Note").setValue("Nach dem Essen");
		
		

		
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public String setNavBarTitle() {
		// TODO Auto-generated method stub
		return "Medication Over View";
	}

	@Override
	public String setNavBarSubTitle() {
		// TODO Auto-generated method stub
		return "subtitle";
	}

	@Override
	public String setNavBarHelpButtonText() {
		// TODO Auto-generated method stub
		return "Forward";
	}

	@Override
	public String setNavBarMenuButtonText() {
		// TODO Auto-generated method stub
		return "Dum2";
	}

	@Override
	public String setNavBarHelpButtonPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setNavBarMenuButtonPath() {
		// TODO Auto-generated method stub
		return "activity";
	}

}
