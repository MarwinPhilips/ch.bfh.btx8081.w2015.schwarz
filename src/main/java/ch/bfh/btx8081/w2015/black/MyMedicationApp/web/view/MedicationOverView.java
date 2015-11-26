package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class MedicationOverView extends VerticalLayout implements View{

	private Table medicationTable;
	//private VerticalLayout mainVerticalLayout;
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
	}

	/**
	 * Create the Horizontal Layout
	 */
	private void createTopHorizontalLayout(){
		topHorizontalLayout=new HorizontalLayout();
		
	}
	
	/**
	 * Create the Body VerticalLayout
	 */
	private void createBodyVerticalLayout(){
		bodyVerticalLayout=new VerticalLayout();
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
		
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
