package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import java.util.Observer;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationOverviewModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationOverviewView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class MedicationOverView extends NavigatorContainer implements View, Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Table medicationTable;
	private HorizontalLayout topHorizontalLayout;
	private Button insertDrug;
	private Button editDrug;
	private Button deleteDrug;
	private VerticalLayout bodyVerticalLayout;
	private MedicationOverviewModel overviewModel;
	/**
	 * 
	 */
	public MedicationOverView() {
		super();
		overviewModel = new MedicationOverviewModel();
		overviewModel.addObserver(this);
		medicationTable=new Table();
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
		overviewModel.loadData();
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
		//maybe its better to do it somehow like https://www.vaadin.com/web/vaadin/forum#!/thread/85890
		//Table table2 = new Table("Table 2, automation with BeanItemContainer");
//        BeanItemContainer<Person> container2 = new BeanItemContainer<Person>(
//                persons);
//        table2.setContainerDataSource(container2);
		medicationTable.clear();
		medicationTable.addContainerProperty("Drug", String.class, null);
		medicationTable.addContainerProperty("Dose", String.class, null);
		medicationTable.addContainerProperty("Schema", String.class, null);
		medicationTable.addContainerProperty("Note", String.class, null);
		// TODO: Maybe there is a better option to set titles just once?!
		for(MedicationOverviewView medicationOverview : overviewModel.getMedications()){
			medicationTable.addItem(new Object[]{medicationOverview.getMedicamentname(), 
			                         	medicationOverview.getDose(),
										medicationOverview.getTimeschemename(),
										medicationOverview.getPrescriptioncomment()}, null);
			/* TODO: addItem() has a itemId which is generated automatically generated atm
			 * should be replaced by actual ID of the object medicationOverview.
			 */
		}		
	}
	public void update(java.util.Observable observable, Object object) {
		createTable();		
	};
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public String setNavBarTitle() {
		return "Medication Over View";
	}

	@Override
	public String setNavBarSubTitle() {
		return "subtitle";
	}

	@Override
	public String setNavBarHelpButtonText() {
		return "Forward";
	}

	@Override
	public String setNavBarMenuButtonText() {
		return "Dum2";
	}

	@Override
	public String setNavBarHelpButtonPath() {
		return null;
	}

	@Override
	public String setNavBarMenuButtonPath() {
		return "activity";
	}

}
