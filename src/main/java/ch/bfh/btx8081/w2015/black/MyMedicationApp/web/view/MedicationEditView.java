package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.DosisScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationEditModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MethodOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.WayOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MethodOfApplicationRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.WayOfApplicationRepository;

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
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


/**
 * 
 * @author Amin,Mete
 *
 */
public class MedicationEditView extends NavigatorContainer implements View, Observer{
	private static final long serialVersionUID = 1L;
	
	private FormLayout formLayoutEditView;
	private VerticalLayout dosisSchemaVerticalLayout;	
	
    private Button saveButton;
	private TextArea prescriptionCommentTextArea;
	private ComboBox methodOfApplicationComboBox;
	private ComboBox wayOfApplicationComboBox;
	private ComboBox timeSchemeComboBox;
	private CheckBox reserveMedicamentComboBox;
	private PopupDateField prescriptionEndDateDateField;
	private PopupDateField prescriptionStartDateDateField;
	private TextField prescriptionMedicamentNameTextField;

	private MethodOfApplicationRepository methodOfApplication;
	private WayOfApplicationRepository wayOfApplication;
	private MedicationEditModel medicationEditModel;
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
		
		prescriptionMedicamentNameTextField = new TextField("Your medication:");
		formLayoutEditView.addComponent(prescriptionMedicamentNameTextField);
		reserveMedicamentComboBox = new CheckBox("Reserve");
		formLayoutEditView.addComponent(reserveMedicamentComboBox);
		timeSchemeComboBox = new ComboBox("Select your time schema: ");
		timeSchemeComboBox.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				medicationEditModel.setPrescriptionTimeScheme((TimeScheme)event.getProperty().getValue());
				fillTimeSchemeTimes();
				}
		});
		formLayoutEditView.addComponent(timeSchemeComboBox);
		dosisSchemaVerticalLayout = new VerticalLayout();
		formLayoutEditView.addComponent(dosisSchemaVerticalLayout);
		
		methodOfApplicationComboBox = new ComboBox("Method of Application");
		methodOfApplication = new MethodOfApplicationRepository();
		
		BeanItemContainer<MethodOfApplication> containerMethod = new BeanItemContainer<MethodOfApplication>(MethodOfApplication.class, methodOfApplication.getAllMethodOfApplication());
		methodOfApplicationComboBox.setContainerDataSource(containerMethod);
		methodOfApplicationComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		methodOfApplicationComboBox.setItemCaptionPropertyId("name");		
		methodOfApplicationComboBox.setNullSelectionAllowed(false);
		methodOfApplicationComboBox.setNewItemsAllowed(false);
		formLayoutEditView.addComponent(methodOfApplicationComboBox);
		
		wayOfApplicationComboBox = new ComboBox("Way of Application");
		wayOfApplication = new WayOfApplicationRepository();
		
		BeanItemContainer<WayOfApplication> containerWay = new BeanItemContainer<WayOfApplication>(WayOfApplication.class, wayOfApplication.getAllWayOfApplication());
		wayOfApplicationComboBox.setContainerDataSource(containerWay);
		wayOfApplicationComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		wayOfApplicationComboBox.setItemCaptionPropertyId("name");		
		wayOfApplicationComboBox.setNullSelectionAllowed(false);
		wayOfApplicationComboBox.setNewItemsAllowed(false);
		formLayoutEditView.addComponent(wayOfApplicationComboBox);
		
		prescriptionStartDateDateField = new PopupDateField("Start Date");
		formLayoutEditView.addComponent(prescriptionStartDateDateField);
		prescriptionEndDateDateField = new PopupDateField("End Date");
		formLayoutEditView.addComponent(prescriptionEndDateDateField);
		prescriptionCommentTextArea = new TextArea("Comments:");
		formLayoutEditView.addComponent(prescriptionCommentTextArea);
		
		saveButton = new Button("Save", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				save();
	        	MyMedicationApp.navigateTo("medication");
			}
			
		});
		formLayoutEditView.addComponent(saveButton);
	}

	private void fillForm(){
		Prescription p = medicationEditModel.getPrescription();
		prescriptionMedicamentNameTextField.setValue(p.getMedicament().getName());
		prescriptionMedicamentNameTextField.setEnabled(false);
		prescriptionCommentTextArea.setValue(p.getComment());
		prescriptionStartDateDateField.setValue(p.getStartDate().getTime());
		if(p.getEndDate()!=null){
			prescriptionEndDateDateField.setValue(p.getEndDate().getTime());	
		}
		fillTimeSchemeComboBox();
		reserveMedicamentComboBox.setValue(p.isReserveMedication());
		
		//timeSchemeComboB.select(p.getTimeScheme().getName());
		timeSchemeComboBox.select((TimeScheme)p.getTimeScheme());
		fillTimeSchemeTimes();
	}
	private void fillTimeSchemeTimes() {
		dosisSchemaVerticalLayout.removeAllComponents();
		for(DosisScheme d : medicationEditModel.getPrescription().getDosisSchemes()){
			dosisSchemaVerticalLayout.addComponent(new DosisSchemeTimeView(d));
		}
	}
	
	private void fillTimeSchemeComboBox(){
		BeanItemContainer<TimeScheme> container = new BeanItemContainer<TimeScheme>(TimeScheme.class,medicationEditModel.getTimeSchemes());
		timeSchemeComboBox.setContainerDataSource(container);
		timeSchemeComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		timeSchemeComboBox.setItemCaptionPropertyId("name");		
		timeSchemeComboBox.setNullSelectionAllowed(false);
		timeSchemeComboBox.setNewItemsAllowed(false);
	}
	private void save() {
		Prescription p = medicationEditModel.getPrescription();
		//p.setMedicament(prescriptionMedicamentName.getValue());
		//prescriptionMedicamentName.setValue(p.getMedicament().getName());
		p.setReserveMedication(reserveMedicamentComboBox.getValue());
		
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
