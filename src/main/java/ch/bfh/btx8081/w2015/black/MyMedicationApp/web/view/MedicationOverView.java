package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationList;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationOverviewModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class MedicationOverView extends NavigatorContainer implements View,
		Observer, ActionListener {
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
	private MedicationEditView editView = null;
	private Prescription selectedPrescription;

	/**
	 * 
	 */
	public MedicationOverView() {

	}

	public MedicationOverView(MedicationEditView editView) {
		super();
		this.editView = editView;
		overviewModel = new MedicationOverviewModel();
		overviewModel.addObserver(this);
		medicationTable = new Table();
		medicationTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void itemClick(ItemClickEvent event) {
						selectedPrescription = ((Prescription)event.getItem().getItemProperty("prescription").getValue());
						editDrug.setEnabled(selectedPrescription!=null);
						deleteDrug.setEnabled(selectedPrescription!=null);						
					}
				});
		createTopHorizontalLayout();
		createBodyVerticalLayout();

		addComponent(topHorizontalLayout);
		addComponent(bodyVerticalLayout);
	}

	/**
	 * Create the Horizontal Layout
	 */
	private void createTopHorizontalLayout() {
		topHorizontalLayout = new HorizontalLayout();
		createButtons();
		topHorizontalLayout.addComponent(insertDrug);
		topHorizontalLayout.addComponent(editDrug);
		topHorizontalLayout.addComponent(deleteDrug);
	}

	/**
	 * Create the Body VerticalLayout
	 */
	private void createBodyVerticalLayout() {
		bodyVerticalLayout = new VerticalLayout();
		overviewModel.loadData();
		bodyVerticalLayout.addComponent(medicationTable);
	}

	/**
	 * create three Buttons
	 */
	private void createButtons() {
		insertDrug = new Button("Insert", new ThemeResource("Icons/Insert.png"));
		addActionToButton(insertDrug.getCaption());
		editDrug = new Button("Edit", new ThemeResource("Icons/Edit.png"));
		addActionToButtonEdit(editDrug.getCaption());
		editDrug.setEnabled(true);
		deleteDrug = new Button("Delete", new ThemeResource("Icons/Delete.png"));
		editDrug.setEnabled(false);
		deleteDrug.setEnabled(false);
	}

	/**
	 * Create medication Table with its headers.
	 */
	private void createTable() {
		medicationTable.clear();
		BeanItemContainer<MedicationList> container = new BeanItemContainer<MedicationList>(
				MedicationList.class);
		container.addAll(overviewModel.getMedications());
		medicationTable.setContainerDataSource(container);
		medicationTable.setVisibleColumns(new Object[] { "medicamentname",
				"timeschemename", "prescriptioncomment" });
		medicationTable.setColumnHeaders(new String[] { "Medikamentenname",
				"Zeischema", "Verordnungskommentar" });
		medicationTable.setSelectable(true);
		medicationTable.addContainerProperty("Drug", String.class, null);
		//The dose is not that easy to get, so for the moment i remove it.
		//medicationTable.addContainerProperty("Dose", String.class, null);
		medicationTable.addContainerProperty("Schema", String.class, null);
		medicationTable.addContainerProperty("Note", String.class, null);
		// TODO: Maybe there is a better option to set titles just once?!
		for(MedicationList medicationList : overviewModel.getMedications()){
			medicationTable.addItem(new Object[]{medicationList.getMedicamentname(), 
			                         	//medicationList.getDose(),
										medicationList.getTimeschemename(),
										medicationList.getPrescriptioncomment()}, null);
			/* TODO: addItem() has a itemId which is generated automatically generated atm
			 * should be replaced by actual ID of the object medicationOverview.
			 */
			
			// TODO: the Value: medicationTable should be replaced with the right one!
			// 			now is this If condition always true
			if(medicationTable == null){
			editDrug.setEnabled(false);}
			else{
				editDrug.setEnabled(true);
			// Allow selecting items from the table.
				medicationTable.setSelectable(true);}
			
		}		
	}

	/**
	 * 
	 */
	public void update(java.util.Observable observable, Object object) {
		createTable();
	};

	private void addActionToButton(String buttonName) {
		if (buttonName == "Insert") {
			insertDrug.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				public void buttonClick(ClickEvent event) {
					MyMedicationApp.navigateTo("MedicationInsertView");
				}
			});
		}
	}

	private void addActionToButtonEdit(String buttonName) {
		if (true && buttonName == "Edit") {
			editDrug.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				public void buttonClick(ClickEvent event) {
					if (selectedPrescription != null) {
						// ToDO: Fehlermeldung, falls kein Medi ausgewählt ist.
						editView.setPrescriptionId(selectedPrescription.getPrescriptionId());
						MyMedicationApp.navigateTo("MedicationEditView");
					}
				}
			});
		}
	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
