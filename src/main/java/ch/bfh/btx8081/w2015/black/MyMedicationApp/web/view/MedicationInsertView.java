package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.DosisScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Medicament;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationEditModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MethodOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.WayOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MedicamentRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MethodOfApplicationRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.TimeSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.WayOfApplicationRepository;

import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author Amin,Mete
 *
 */
public class MedicationInsertView extends NavigatorContainer implements View {
	private static final long serialVersionUID = 1L;
	
	private FormLayout form;
	private Panel panel;
	private VerticalLayout dosisSchemaVerticalLayout;
	
	private CheckBox reserveType;
	private ComboBox medicationNamesComboBox;
	private ComboBox timeSchemeComboBox;
	private ComboBox methodOfApplicationComboBox;
	private ComboBox wayOfApplicationComboBox;
	private PopupDateField startDatum;
	private PopupDateField  endDatum;
	private TextArea comments;
	private Button saveButton = new Button("Save", new SaveButtonListener());
	
	private MedicamentRepository medicationsListRepository;
	private MethodOfApplicationRepository methodOfApplicationRepository;
	private WayOfApplicationRepository wayOfApplicationRepository;
	private TimeSchemeRepository timeSchemaRepository;
	private MedicationEditModel medicationEditModel;
	
	/**
	 * @param insertForm
	 */
	public MedicationInsertView() {
		super();
	    createMedicationNameComboBox();
		createReserveCheckBox();
		createAndFillTimeSchemeComboBox();
	    createMethodOfApplicationComboBox();
	    createWayOfApplicationComboBox();
	    createStartEndDateDateFields();     
	    createCommentTextArea(); 
	    createFormLayout();
	    createPanelInsideForm();
	}
	
	/**
	 * Creates new combo box to insert a new method of Application
	 */
	private void createMethodOfApplicationComboBox() {
		methodOfApplicationComboBox = new ComboBox("Method of Application");
		methodOfApplicationRepository = new MethodOfApplicationRepository();
		
		BeanItemContainer<MethodOfApplication> container = new BeanItemContainer<MethodOfApplication>(MethodOfApplication.class, methodOfApplicationRepository.getAllMethodOfApplication());
		methodOfApplicationComboBox.setContainerDataSource(container);
		methodOfApplicationComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		methodOfApplicationComboBox.setItemCaptionPropertyId("name");		
		methodOfApplicationComboBox.setNullSelectionAllowed(false);
		methodOfApplicationComboBox.setNewItemsAllowed(false);	
	}
	
	/**
	 * Creates a new combo box to insert a new way of application
	 */
	private void createWayOfApplicationComboBox() {
		wayOfApplicationComboBox = new ComboBox("Way of Application");
		wayOfApplicationRepository = new WayOfApplicationRepository();
		
		BeanItemContainer<WayOfApplication> container = new BeanItemContainer<WayOfApplication>(WayOfApplication.class, wayOfApplicationRepository.getAllWayOfApplication());
		wayOfApplicationComboBox.setContainerDataSource(container);
		wayOfApplicationComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		wayOfApplicationComboBox.setItemCaptionPropertyId("name");		
		wayOfApplicationComboBox.setNullSelectionAllowed(false);
		wayOfApplicationComboBox.setNewItemsAllowed(false);	
	}

	class SaveButtonListener implements Button.ClickListener {
    	private static final long serialVersionUID = 1L;
		 
        @Override
        public void buttonClick(ClickEvent event) {
        	//TODO: Validate before saving
        	Prescription p = medicationEditModel.getPrescription();
        	
        	p.setPerson(medicationEditModel.getLoggedInPerson());
        	p.setComment(comments.getValue());
        	p.setReserveMedication(reserveType.getValue());
        	
        	GregorianCalendar startDateGregorian = new GregorianCalendar();
        	startDateGregorian.setTime(startDatum.getValue());
        	p.setStartDate(startDateGregorian);
        	
        	GregorianCalendar endDateGregorian = new GregorianCalendar();
        	endDateGregorian.setTime(endDatum.getValue());
        	p.setEndDate(endDateGregorian);
        	
        	//p.setMedicament((Medicament)medicationNames.getValue());
        	p.setMethodOfApplication((MethodOfApplication)methodOfApplicationComboBox.getValue());
        	p.setWayOfApplication((WayOfApplication)wayOfApplicationComboBox.getValue());
        	//p.setTimeScheme(timeScheme); // TODO get timescheme of dropdown
        	
        	medicationEditModel.save();
        	MyMedicationApp.navigateTo("medication");
        }
    }

	/**
	 * Create the Medications combo box to insert a new medication
	 */
	private void createMedicationNameComboBox(){
		medicationNamesComboBox = new ComboBox("Select your medication");
		medicationsListRepository = new MedicamentRepository();
		
		BeanItemContainer<Medicament> container = new BeanItemContainer<Medicament>(Medicament.class, medicationsListRepository.getAllMedicaments());
		medicationNamesComboBox.setContainerDataSource(container);
		medicationNamesComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		medicationNamesComboBox.setItemCaptionPropertyId("name");		
		medicationNamesComboBox.setNullSelectionAllowed(false);
		medicationNamesComboBox.setNewItemsAllowed(false);
		
		medicationNamesComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				medicationEditModel.getPrescription().setMedicament((Medicament)medicationNamesComboBox.getValue());
				timeSchemeComboBox.setEnabled(true);
			}
		});
	}
	
	/**
	 * 
	 */
	private void createAndFillTimeSchemeComboBox(){
		medicationEditModel = new MedicationEditModel();
		timeSchemeComboBox = new ComboBox("Select your time schema");
		BeanItemContainer<TimeScheme> container = new BeanItemContainer<TimeScheme>(TimeScheme.class,medicationEditModel.getTimeSchemes());
		timeSchemeComboBox.setContainerDataSource(container);
		timeSchemeComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		timeSchemeComboBox.setItemCaptionPropertyId("name");		
		timeSchemeComboBox.setNullSelectionAllowed(false);
		timeSchemeComboBox.setNewItemsAllowed(false);
		
		timeSchemaRepository = new TimeSchemeRepository();
		for(TimeScheme schema:timeSchemaRepository.getAllTimeschemes()){
			timeSchemeComboBox.addItem(schema);
		}
		timeSchemeComboBox.setEnabled(false); // has to be disabled until we select a medicament
		
		timeSchemeComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				medicationEditModel.setPrescriptionTimeScheme((TimeScheme)event.getProperty().getValue());
				//TODO Not working atm
				fillTimeSchemeTimes();
				}
		});
		
		dosisSchemaVerticalLayout = new VerticalLayout();
	}
	
	private void fillTimeSchemeTimes() {
		dosisSchemaVerticalLayout.removeAllComponents();
		for(DosisScheme d : medicationEditModel.getPrescription().getDosisSchemes()){
			dosisSchemaVerticalLayout.addComponent(new DosisSchemeTimeView(d));
		}
	}
	
	/**
	 * Create check box for reserve medication
	 */
	private void createReserveCheckBox(){
		reserveType = new CheckBox("Reserve");
	}
	
	/**
	 * Create Start and End date Datefields
	 */
	private void createStartEndDateDateFields(){
		startDatum = new PopupDateField ("Start Date:");
		startDatum.setValue(new Date());
	    
	    endDatum = new PopupDateField ("End Date:");
	    endDatum.setValue(new Date());
	}
	
	/**
	 * create a Text Area for a comment
	 */
	private void createCommentTextArea(){
		comments = new TextArea("Comments:");
	}
	
	/**
	 * Create a form layout to put component in it.
	 */
	private void createFormLayout(){
		form = new FormLayout();
		form.addComponent(medicationNamesComboBox);
	    form.addComponent(reserveType);
	    form.addComponent(timeSchemeComboBox);
	    form.addComponent(dosisSchemaVerticalLayout);
	    form.addComponent(methodOfApplicationComboBox);
	    form.addComponent(wayOfApplicationComboBox);
	    form.addComponent(startDatum);
	    form.addComponent(endDatum);
	    form.addComponent(comments);
	    form.addComponent(saveButton);
	}
	
	/**
	 * Create the Panel
	 */
	private void createPanelInsideForm(){
		panel = new Panel();
		panel.setSizeFull();
		panel.setWidth("100%");
		panel.setContent(form);
	    addComponent(panel);
	    setComponentAlignment(panel, Alignment.TOP_CENTER);	
	}

	@Override
	public String setNavBarTitle() {
		return "Insert Medication";
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
		return "Overview";
	}

	@Override
	public String setNavBarHelpButtonPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setNavBarMenuButtonPath() {
		return "medication";
	}

}
