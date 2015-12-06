package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;


import java.util.Observable;
import java.util.Observer;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationEditModel;

import com.vaadin.ui.Button.ClickEvent;


/**
 * 
 * @author Amin,Mete
 *
 */
@SuppressWarnings("deprecation")
public class MedicationEditView extends NavigatorContainer implements View, Observer{
	
	private MedicationEditModel medicationEdit;
	private Form insertForm;
	private TextField dosisTextField = new TextField();
	private TextField noteTextField = new TextField();
	private TextField textFieldMo = new TextField();
	private TextField textFieldMi = new TextField();
	private TextField textFieldAb = new TextField();
	private TextField textFieldNa = new TextField();
	private HorizontalLayout hl = new HorizontalLayout();
	private Button saveBt = new Button("Save");
	
	
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
		insertForm.getLayout().addComponent(saveBt);
		
		
		/**
		 * ClickListener for the save button
		 */
		saveBt.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			/**
			 *  Save the edited Value
			 */
			//TODO: Write the code for saving
			
			
			/**
			 * Go Back to MedicationOverView
			 */
			public void buttonClick(ClickEvent event) {
				MyMedicationApp.navigateTo("medication");
				}
			});
		
		addComponent(insertForm);
		
	}
	
	// TODO: Get the actually Value of Medication/Prescription from the DB
	private void insertEditMedicationText(){
		/*
		 * something like that? medicationName = medicationEdit.getPrescription().getMedicament().getName();
		 */
		dosisTextField.setValue("Aktuelle Dosis aufrufen");
		noteTextField.setValue("AktuelleNote");
		textFieldMo.setValue("aktuelle Mo");
		textFieldMi.setValue("aktuelle Mi");
		textFieldAb.setValue("aktuelle Ab");
		textFieldNa.setValue("aktuelle Na");
	}
	
	

	@Override
	public String setNavBarTitle() {
		// TODO Auto-generated method stub
		return "Edit medication";
	}

	@Override
	public String setNavBarSubTitle() {
		// TODO Should show the MedicationName of the selected medication
		return "Medication name";
	}

	@Override
	public String setNavBarHelpButtonText() {
		// TODO Auto-generated method stub
		return "Menu";
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
		// TODO Auto-generated method stub
		insertEditMedicationText();
		
	}

}
