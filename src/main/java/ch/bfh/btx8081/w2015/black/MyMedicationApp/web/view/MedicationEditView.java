package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationEditModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeScheme;
import com.vaadin.ui.Button.ClickEvent;


/**
 * 
 * @author Amin,Mete
 *
 */
@SuppressWarnings("deprecation")
public class MedicationEditView extends NavigatorContainer implements View, Observer{
	
	private MedicationEditModel medicationEdit;
	private Form insertForm; // TODO: Form() is deprecated! Use FielGroup instead
	private TextField dosisTextField = new TextField();
	private TextField noteTextField = new TextField();
	private TextField textFieldMo = new TextField();
	private TextField textFieldMi = new TextField();
	private TextField textFieldAb = new TextField();
	private TextField textFieldNa = new TextField();
	private HorizontalLayout hl = new HorizontalLayout();
    private Button saveButton = new Button("Save", new SaveButtonListener());
    private List<TimeScheme> timeSchemes = null;
	private Prescription prescription = null;
	private TimeScheme selectedTimeScheme = null;
	private int prescriptionId = 0;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * @param insertForm
	 */
	public MedicationEditView() {
		super();
		medicationEdit = new MedicationEditModel();
		medicationEdit.addObserver(this);
		medicationEdit.loadData(1);
		timeSchemes = medicationEdit.getTimeSchemes();
		prescription = medicationEdit.getPrescription();
		insertEditMedicationText();
		
		/**
		 * Create the Form of MedicationEditView
		 */
		insertForm=new Form();
		textFieldMo.setMaxLength(5);
		hl.addComponent(new Label("Mo: "));
		hl.addComponent(textFieldMo);
		textFieldMi.setMaxLength(5);
		hl.addComponent(new Label("Mi: "));
		hl.addComponent(textFieldMi);
		textFieldAb.setMaxLength(5);	
		hl.addComponent(new Label("Ab: "));;
		hl.addComponent(textFieldAb);
		textFieldNa.setMaxLength(5);
		hl.addComponent(new Label("Na: "));
		hl.addComponent(textFieldNa);
		
		insertForm.setCaption("Edit: <MedicationName>");
		insertForm.getLayout().addComponents(new Label("Dosis: "),dosisTextField);
		insertForm.getLayout().addComponents(new Label("Schema:"),hl);
		insertForm.getLayout().addComponents(new Label("Note: "), noteTextField);	
		insertForm.getLayout().addComponent(saveButton);
		
		addComponent(insertForm);
		
	}
	
	class SaveButtonListener implements Button.ClickListener {
    	private static final long serialVersionUID = 1L;
		 
        @Override
        public void buttonClick(ClickEvent event) {
        	//TODO: Validate and save the values to the database
        	
        	MyMedicationApp.navigateTo("medication");
        }
    }
	
	// TODO: Get the actually Value of Medication/Prescription from the DB
	private void insertEditMedicationText(){
		/*
		 * something like that? medicationName = medicationEdit.getPrescription().getMedicament().getName();
		 */
		/*
		for(TimeSchemeTime t : selectedTimeScheme.getTimeSchemeTimes()){
			TextField tf = new TextField();
			tf.setMaxLength(5);
			hl.addComponent(new Label(t.getTimeSchemeTimeName()));
			hl.addComponent(tf);
		}*/
		dosisTextField.setValue("Aktuelle Dosis aufrufen");
		noteTextField.setValue("AktuelleNote");
		
		textFieldMo.setValue("aktuelle Mo");
		textFieldMi.setValue("aktuelle Mi");
		textFieldAb.setValue("aktuelle Ab");
		textFieldNa.setValue("aktuelle Na");
	}
	
	

	@Override
	public String setNavBarTitle() {
		return "Edit medication";
	}

	@Override
	public String setNavBarSubTitle() {
		// TODO Should show the MedicationName of the selected medication
		return "Medication name";
	}

	@Override
	public String setNavBarHelpButtonText() {
		return "?";
	}

	@Override
	public String setNavBarMenuButtonText() {
		// TODO Should go to MedicatoinOverView without save of the edited Value
		return "Back";
	}

	@Override
	public String setNavBarHelpButtonPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setNavBarMenuButtonPath() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void update(Observable o, Object arg) {
		createView();		
	}

	private void createView() {
		medicationEdit.loadData(prescriptionId);		
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;		
	}

}
