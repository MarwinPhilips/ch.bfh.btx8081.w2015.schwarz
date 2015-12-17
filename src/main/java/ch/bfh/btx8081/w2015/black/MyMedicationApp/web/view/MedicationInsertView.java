package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import java.util.Date;
import java.util.GregorianCalendar;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Medicament;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationEditModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MedicamentRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.TimeSchemeRepository;

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

/**
 * 
 * @author Amin,Mete
 *
 */
public class MedicationInsertView extends NavigatorContainer implements View {
	private static final long serialVersionUID = 1L;
	private Button saveButton = new Button("Save", new SaveButtonListener());
	private FormLayout form ;
	private Panel panel;
	private ComboBox medicationNames;
	private MedicamentRepository medicationsList;
	private MedicationEditModel medicationEditModel;
	private ComboBox timeSchemeComboB;
	private TimeSchemeRepository timeSchema;
	private CheckBox reserveType;
	private PopupDateField startDatum;
	private PopupDateField  endDatum;
	private TextArea comments;
	
	/**
	 * @param insertForm
	 */
	public MedicationInsertView() {
		super();
		createCheckBox();
	    fillTimeSchemeComboBox();
	    createStartEndDate();     
	    createCommentTextArea(); 
	    createMedicationComboBox();
	    createFormLayout();
	    createPanelInsideForm();

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
        	
        	p.setMedicament((Medicament)medicationNames.getValue());
        	//p.setMethodOfApplication(methodOfApplication); // TODO get method of dropdown
        	//p.setWayOfApplication(wayOfApplication); // TODO get way of dropdown
        	//p.setTimeScheme(timeScheme); // TODO get timescheme of dropdown
        	
        	medicationEditModel.save();
        	MyMedicationApp.navigateTo("medication");
        }
    }

	/**
	 * Create the Medications combo box to insert a new medication
	 */
	private void createMedicationComboBox(){
		medicationNames = new ComboBox("Select your medication");
		medicationsList = new MedicamentRepository();
		
		BeanItemContainer<Medicament> container = new BeanItemContainer<Medicament>(Medicament.class, medicationsList.getAllMedicaments());
		medicationNames.setContainerDataSource(container);
		medicationNames.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		medicationNames.setItemCaptionPropertyId("name");		
		medicationNames.setNullSelectionAllowed(false);
		medicationNames.setNewItemsAllowed(false);
	}
	
	/**
	 * 
	 */
	private void fillTimeSchemeComboBox(){
		medicationEditModel = new MedicationEditModel();
		timeSchemeComboB = new ComboBox("Select your time schema");
		BeanItemContainer<TimeScheme> container = new BeanItemContainer<TimeScheme>(TimeScheme.class,medicationEditModel.getTimeSchemes());
		timeSchemeComboB.setContainerDataSource(container);
		timeSchemeComboB.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		timeSchemeComboB.setItemCaptionPropertyId("name");		
		timeSchemeComboB.setNullSelectionAllowed(false);
		timeSchemeComboB.setNewItemsAllowed(false);
		
		timeSchema = new TimeSchemeRepository();
		for(TimeScheme schema:timeSchema.getAllTimeschemes()){
			timeSchemeComboB.addItem(schema);
		}
	}
	
	/**
	 * Create check box for reserve medication
	 */
	private void createCheckBox(){
		reserveType = new CheckBox("Reserve");
	}
	
	/**
	 * Create Start and End date
	 */
	private void createStartEndDate(){
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
		form.addComponent(medicationNames);
	    form.addComponent(reserveType);
	    form.addComponent(timeSchemeComboB);
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
