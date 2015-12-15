package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author Amin,Mete
 *
 */
public class MedicationInsertView extends NavigatorContainer implements View {

	
	
	private Form insertForm; // TODO: Form() is deprecated! Use FielGroup instead
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Button saveButton = new Button("Save", new SaveButtonListener());
	
	
	/**
	 * @param insertForm
	 */
	public MedicationInsertView() {
		super();
		insertForm=new Form();
		insertForm.setCaption("Form Caption");
		insertForm.setDescription("This is a description of the Form that is " +
		        "displayed in the upper part of the form. You normally " +
		        "enter some descriptive text about the form and its " +
		        "use here.");
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

	@Override
	public String setNavBarTitle() {
		return " Insert Medication";
	}

	@Override
	public String setNavBarSubTitle() {
		return null;
	}

	@Override
	public String setNavBarHelpButtonText() {
		return "?";
	}

	@Override
	public String setNavBarMenuButtonText() {
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

}
