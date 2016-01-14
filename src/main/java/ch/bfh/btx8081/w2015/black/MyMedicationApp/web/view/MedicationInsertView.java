package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.DosisScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Medicament;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationEditModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MethodOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.WayOfApplication;
import java.util.Date;
import java.util.GregorianCalendar;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
/**
 * 
 * @author 
 *This class provide a view to insert new Medications 
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
	private MedicationEditModel medicationEditModel;
	
	private BeanItemContainer<Medicament> medicationNamesContainer;
	
	/**
	 * Construct a MedicationInsertView to add medications.
	 */	
	public MedicationInsertView() {
		super();
		
		medicationEditModel = new MedicationEditModel();
		medicationEditModel.loadData();
	    createMedicationNameComboBox();
		createReserveCheckBox();
		createTimeSchemeComboBox();
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
		methodOfApplicationComboBox.addValidator(new NullValidator("you must select a method", false));
		BeanItemContainer<MethodOfApplication> container = new BeanItemContainer<MethodOfApplication>(MethodOfApplication.class, medicationEditModel.getMethodOfApplications());
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
		wayOfApplicationComboBox.addValidator(new NullValidator("you must select a way", false));		
		BeanItemContainer<WayOfApplication> container = new BeanItemContainer<WayOfApplication>(WayOfApplication.class, medicationEditModel.getWayOfApplications());
		wayOfApplicationComboBox.setContainerDataSource(container);
		wayOfApplicationComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		wayOfApplicationComboBox.setItemCaptionPropertyId("name");		
		wayOfApplicationComboBox.setNullSelectionAllowed(false);
		wayOfApplicationComboBox.setNewItemsAllowed(false);	
	}

	/**
	 *Create Listener for Save button.
	 */
	class SaveButtonListener implements Button.ClickListener {
    	private static final long serialVersionUID = 1L;
		 
        @Override
        public void buttonClick(ClickEvent event) {
        	//TODO: Validate before saving
        	try {
                medicationNamesComboBox.validate();
                wayOfApplicationComboBox.validate();
                methodOfApplicationComboBox.validate();
            } catch (InvalidValueException e) {
                Notification.show(e.getMessage());
                medicationNamesComboBox.setValidationVisible(true);
                wayOfApplicationComboBox.setValidationVisible(true);
                methodOfApplicationComboBox.setValidationVisible(true);
                return;
            }
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
        	
        	p.setMethodOfApplication((MethodOfApplication)methodOfApplicationComboBox.getValue());
        	p.setWayOfApplication((WayOfApplication)wayOfApplicationComboBox.getValue());
        	
        	medicationEditModel.save();
        	resetToInitialStatues();
        	MyMedicationApp.navigateTo("medication");
        	
        }

        /**
         * Reset every component of form to the initial status.
         */
    	private void resetToInitialStatues() {
    		// TODO Auto-generated method stub
    		
    		medicationNamesComboBox.clear();
    		methodOfApplicationComboBox.clear();
    		wayOfApplicationComboBox.clear();
    		startDatum.clear();
    		endDatum.clear();
    		comments.clear();
    	}
        
		
    }

	/**
	 * Create the medications combo box to insert a new medication.
	 */
	private void createMedicationNameComboBox(){
		medicationNamesComboBox = new ComboBox("Select your medication");
		medicationNamesComboBox.addValidator(new NullValidator("you must select a medicament", false));
		fillMedicationNameComboBox();
		medicationNamesComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		medicationNamesComboBox.setItemCaptionPropertyId("name");		
		medicationNamesComboBox.setNullSelectionAllowed(false);
		medicationNamesComboBox.setNewItemsAllowed(false);
		addListnerToMedicationNameComboBox();
		
	}
	
	
	/**
	 * Filling the MedicationNameBomboBox with different medications.
	 */
	private void fillMedicationNameComboBox(){
		medicationNamesContainer = new BeanItemContainer<Medicament>(Medicament.class, medicationEditModel.getMedicaments());
		medicationNamesComboBox.setContainerDataSource(medicationNamesContainer);
	}
	
	/**
	 * Add ValueChangeListener to MedicationNameBomboBox
	 */
	private void addListnerToMedicationNameComboBox(){
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
	 * Create a combo box for timeSchema.
	 */
	private void createTimeSchemeComboBox(){
		
		timeSchemeComboBox = new ComboBox("Select your time schema");
		fillTimeSchemaComboBox();
		timeSchemeComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		timeSchemeComboBox.setItemCaptionPropertyId("name");		
		timeSchemeComboBox.setNullSelectionAllowed(false);
		timeSchemeComboBox.setNewItemsAllowed(false);
		
		timeSchemeComboBox.setEnabled(false); // has to be disabled until we select a medicament
		
		addListnerToTimeSchemaComboBox();
		
		dosisSchemaVerticalLayout = new VerticalLayout();
	}
	
	/**
	 * Filling the timeSchemaComboBox with different time schemas.
	 */
	private void fillTimeSchemaComboBox(){
		
		BeanItemContainer<TimeScheme> container = new BeanItemContainer<TimeScheme>(TimeScheme.class,medicationEditModel.getTimeSchemes());
		timeSchemeComboBox.setContainerDataSource(container);
		for(TimeScheme schema:medicationEditModel.getTimeSchemes()){
			timeSchemeComboBox.addItem(schema);
		}
	}
	
	/**
	 * Add valueChangeListener to timeSchemaComboBox.
	 */
	private void addListnerToTimeSchemaComboBox(){
		timeSchemeComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				medicationEditModel.setPrescriptionTimeScheme((TimeScheme)timeSchemeComboBox.getValue());
				//TODO Not working atm
				fillTimeSchemeTimes();
				}
		});
	}
	
	/**
	 * Filling the Time schemas in dosis schema in form of vertical layout.
	 */
	private void fillTimeSchemeTimes() {
		dosisSchemaVerticalLayout.removeAllComponents();
		for(DosisScheme d : medicationEditModel.getPrescription().getDosisSchemes()){
			dosisSchemaVerticalLayout.addComponent(new DosisSchemeTimeView(d,medicationEditModel));
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
		panel.setContent(form);
	    addComponent(panel);
	    setComponentAlignment(panel, Alignment.TOP_CENTER);	
	}

	/**
	 * Set navigation bar title with "Insert Medication"
	 */
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

	/**
	 * Set  the button of Navigations bar menu with "Overview".
	 */
	@Override
	public String setNavBarMenuButtonText() {
		return "Overview";
	}

	@Override
	public String setNavBarHelpButtonPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Set the button path with "medication".
	 */
	@Override
	public String setNavBarMenuButtonPath() {
		return "medication";
	}
	@Override
	public void enter(ViewChangeEvent event) {
		medicationEditModel.resetPrescription();
	}

}
