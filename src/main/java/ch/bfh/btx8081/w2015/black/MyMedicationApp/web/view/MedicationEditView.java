package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;


import java.util.Observable;
import java.util.Observer;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.DosisScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationEditModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;


/**
 * 
 * @author Amin,Mete
 *
 */
public class MedicationEditView extends NavigatorContainer implements View, Observer{
	
	private MedicationEditModel medicationEditModel;
	private FormLayout formLayoutEditView = null; // TODO: Form() is deprecated! Use FielGroup instead
	private VerticalLayout dosisSchemaVerticalLayout = null;
    private Button saveButton;
	private TextField prescriptionCommentTF;
	private TextField prescriptionTimeSchemeNameTF;
	private Label endDateLabel;
	private DateField prescriptionEndDateDF;
	private CheckBox reserveMedicamentCB;
	private Label beginDateLabel;
	private DateField prescriptionStartDateDF;
	private TextField prescriptionMedicamentName;
	private ComboBox timeSchemeComboB;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * @param formLayoutEditView
	 */
	public MedicationEditView() {
		super();
		createForm();
		medicationEditModel = new MedicationEditModel();
		medicationEditModel.addObserver(this);
		//medicationEditModel.loadData();
		
	}
	
	private void createForm() {
		formLayoutEditView = new FormLayout();
		Panel p=new Panel();
		p.setContent(formLayoutEditView);
		p.setSizeFull();
		addComponent(p);

		
		prescriptionMedicamentName = new TextField("Medikament:");
		formLayoutEditView.addComponent(prescriptionMedicamentName);
		dosisSchemaVerticalLayout = new VerticalLayout();
		formLayoutEditView.addComponent(dosisSchemaVerticalLayout);
		prescriptionCommentTF = new TextField("Verschreibungsnotiz:");
		formLayoutEditView.addComponent(prescriptionCommentTF);
		prescriptionTimeSchemeNameTF = new TextField("Zeitschema:");
		formLayoutEditView.addComponent(prescriptionTimeSchemeNameTF);
		beginDateLabel = new Label("Beginn der Verordnung");
		formLayoutEditView.addComponent(beginDateLabel);
		prescriptionStartDateDF = new DateField();
		formLayoutEditView.addComponent(prescriptionStartDateDF);
		endDateLabel = new Label("Ablaufdatum der Verordnung");
		formLayoutEditView.addComponent(endDateLabel);
		prescriptionEndDateDF = new DateField();
		formLayoutEditView.addComponent(prescriptionEndDateDF);
		reserveMedicamentCB = new CheckBox("Reserve: ");
		formLayoutEditView.addComponent(reserveMedicamentCB);
		saveButton = new Button("Save", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				save();
	        	MyMedicationApp.navigateTo("medication");
			}
			
		});
		formLayoutEditView.addComponent(saveButton);
		timeSchemeComboB = new ComboBox("Zeitschema: ");
		timeSchemeComboB.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				medicationEditModel.setPrescriptionTimeScheme((TimeScheme)event.getProperty().getValue());
				fillTimeSchemeTimes();
				}
		});
		formLayoutEditView.addComponent(timeSchemeComboB);
	}

	private void fillForm(){
		Prescription p = medicationEditModel.getPrescription();
		prescriptionMedicamentName.setValue(p.getMedicament().getName());
		prescriptionCommentTF.setValue(p.getComment());
		prescriptionTimeSchemeNameTF.setValue(p.getTimeScheme().getName());
		prescriptionStartDateDF.setValue(p.getStartDate().getTime());
		if(p.getEndDate()!=null){
			prescriptionEndDateDF.setValue(p.getEndDate().getTime());	
		}
		fillTimeSchemeComboBox();
		reserveMedicamentCB.setValue(p.isReserveMedication());
		
		timeSchemeComboB.select(p.getTimeScheme().getName());	
		fillTimeSchemeTimes();
	}
	private void fillTimeSchemeTimes() {
		dosisSchemaVerticalLayout.removeAllComponents();
		for(DosisScheme d : medicationEditModel.getPrescription().getDosisSchemes()){
			dosisSchemaVerticalLayout.addComponent(new DosisSchemeTimeDisplay(d));
		}
	}
	private class DosisSchemeTimeDisplay extends HorizontalLayout {
		private static final long serialVersionUID = 1L;
		private DosisScheme dosisScheme;
		private Label timeSchemeName;
		private Label time;
		private TextField medicamentAmount;
		private Label dose;
		public DosisSchemeTimeDisplay(DosisScheme d) {
			this.dosisScheme = d;
			this.timeSchemeName = new Label(dosisScheme.getDosisSchemeName()+": ");
			addComponent(timeSchemeName);
			this.time = new Label(dosisScheme.getTimespan().toHours() + "h");
			addComponent(time);
			this.medicamentAmount = new TextField();
			this.medicamentAmount.setValue(dosisScheme.getAmount()+"");
			medicamentAmount.addValueChangeListener(new Property.ValueChangeListener() {
				private static final long serialVersionUID = 1L;
				@Override
				public void valueChange(ValueChangeEvent event) {
					dosisScheme.setAmount(Double.parseDouble(medicamentAmount.getValue()));
				}
			});
			addComponent(medicamentAmount);
			this.dose = new Label(dosisScheme.getQuantityUnit());
			addComponent(dose);
		}
	}
	private void fillTimeSchemeComboBox(){
		BeanItemContainer<TimeScheme> container = new BeanItemContainer<TimeScheme>(TimeScheme.class,medicationEditModel.getTimeSchemes());
		timeSchemeComboB.setContainerDataSource(container);
		timeSchemeComboB.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		timeSchemeComboB.setItemCaptionPropertyId("name");		
		timeSchemeComboB.setNullSelectionAllowed(false);
		timeSchemeComboB.setNewItemsAllowed(false);
	}
	private void save() {
		Prescription p = medicationEditModel.getPrescription();
		//p.setMedicament(prescriptionMedicamentName.getValue());
		//prescriptionMedicamentName.setValue(p.getMedicament().getName());
		p.setReserveMedication(reserveMedicamentCB.getValue());
		
	}
	@Override
	public String setNavBarTitle() {
		return "Edit medication";
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
		// TODO Should go to MedicatoinOverView without save of the edited Value
		return "Overview";
	}

	@Override
	public String setNavBarHelpButtonPath() {
		return null;
	}

	@Override
	public String setNavBarMenuButtonPath() {
		return "medication";
	}

	
	@Override
	public void update(Observable o, Object arg) {
		fillForm();		
	}
	
	
	public void setPrescriptionContext(PrescriptionContext prescriptionContext) {
		medicationEditModel.setPrescriptionContext(prescriptionContext);
		medicationEditModel.loadData();		
	}

}
