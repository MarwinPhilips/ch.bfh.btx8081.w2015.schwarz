package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationList;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationOverviewModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

/**
 * MedicationOverView lists all medications from patient
 * 
 * @author BlackTeam
 *
 */
public class MedicationOverView extends NavigatorContainer implements View,
		Observer, ActionListener {
	private static final long serialVersionUID = 1L;
	private Table medicationTable = null;
	private HorizontalLayout topHorizontalLayout = null;
	private Button insertDrug = null;
	private Button editDrug = null;
	private Button deleteDrug = null;
	private MedicationOverviewModel overviewModel = null;
	private MedicationEditView editView = null;
	private Panel bodyPanel;
	
	/**
	 * Create default constructor for MedicationOverView.
	 */
	public MedicationOverView() {

	}

	/**
	 * Create a customized constructor for MedicationOverView.
	 * @param editView 
	 */
	public MedicationOverView(MedicationEditView editView) {
		super();
		this.editView = editView;
		overviewModel = new MedicationOverviewModel();
		overviewModel.addObserver(this);
		medicationTable = new Table();
		addListenerToMedicationTable();
		createTopHorizontalLayout();
		createBodyVerticalLayout();
		addComponent(topHorizontalLayout);
		setComponentAlignment(topHorizontalLayout, Alignment.TOP_CENTER);
		addComponent(bodyPanel);
		setComponentAlignment(bodyPanel, Alignment.MIDDLE_CENTER);

	}
	
	/**
	 * Add itemClickListener to the medications table.
	 */
	public void addListenerToMedicationTable(){
		medicationTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				overviewModel.setSelectedPrescription(((Prescription)event.getItem().getItemProperty("prescription").getValue()));
				editDrug.setEnabled(overviewModel.canModifyPrescription());
				deleteDrug.setEnabled(overviewModel.canModifyPrescription());						
			}
		});
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
		bodyPanel = new Panel();
		bodyPanel.setSizeFull();
		bodyPanel.setWidth("800px");
		bodyPanel.setContent(medicationTable);
	}

	/**
	 * create three Buttons with icons.
	 */
	private void createButtons() {
		insertDrug = new Button("Insert", new ThemeResource("Icons/Insert.png"));
		addActionsToButtons(insertDrug.getCaption());
		editDrug = new Button("Edit", new ThemeResource("Icons/Edit.png"));
		addActionsToButtons(editDrug.getCaption());
		editDrug.setEnabled(true);
		deleteDrug = new Button("Delete", new ThemeResource("Icons/Delete.png"));
		deleteDrug.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				overviewModel.deletePrescription();
				editDrug.setEnabled(false);
				deleteDrug.setEnabled(false);
			}
		});
		editDrug.setEnabled(false);
		deleteDrug.setEnabled(false);
	}

	/**
	 * Create medication Table with its headers.
	 */
	private void createTable() {
		
		BeanItemContainer<MedicationList> container = new BeanItemContainer<MedicationList>(
				MedicationList.class);
		container.addAll(overviewModel.getMedications());
		medicationTable.setContainerDataSource(container);
		// sets another css class for reserved medication row
		medicationTable.setCellStyleGenerator(new Table.CellStyleGenerator() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getStyle(Table source, Object itemId, Object propertyId) {
				if(itemId != null){
					MedicationList mediList = (MedicationList)itemId;
					if(mediList.isReservemedication()){
						return "reserve-medication";
					}
				}
				return null;
			}
		});

		// Needs to be called due to the changes we do in our EditView.
		medicationTable.refreshRowCache();
		medicationTable.setVisibleColumns(new Object[] { "medicamentname",
				"timeschemename", "prescriptioncomment" });
		medicationTable.setColumnHeaders(new String[] { "Medikamentenname",
				"Zeischema", "Verordnungskommentar" });
		
		medicationTable.setSelectable(true);
	}

	/**
	 * Called by the Observable MedicationEditModel
	 */
	public void update(java.util.Observable observable, Object object) {
		createTable();
	};

	
	/**
	 * Add click Listener to specified buttons based on buttons captions.
	 * @param buttonName buttons caption.
	 */
	private void addActionsToButtons(String buttonName) {
		
		if (buttonName.equals("Insert")) {
			insertDrug.addClickListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				public void buttonClick(ClickEvent event) {
					MyMedicationApp.navigateTo("MedicationInsertView");
				}
			});
		}
			
		if (buttonName.equals("Edit")) {
				editDrug.addClickListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					public void buttonClick(ClickEvent event) {
						System.out.println(overviewModel.getSelectedPrescription());
						if (overviewModel.getSelectedPrescription()!=null) {
							overviewModel.edit();
							editView.setPrescriptionContext(overviewModel.getPrescriptionContext());
							MyMedicationApp.navigateTo("MedicationEditView");
						}
					}
			});
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {
		editDrug.setEnabled(false);
		deleteDrug.setEnabled(false);
		
		overviewModel.loadData();
	}

	
	@Override
	public String setNavBarTitle() {
		return "Medication Over View";
	}

	@Override
	public String setNavBarSubTitle() {
		return null;
	}

	@Override
	public String setNavBarHelpButtonText() {
		return null;
	}

	@Override
	public String setNavBarMenuButtonText() {
		return null;
	}

	@Override
	public String setNavBarHelpButtonPath() {
		return null;
	}

	@Override
	public String setNavBarMenuButtonPath() {
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
