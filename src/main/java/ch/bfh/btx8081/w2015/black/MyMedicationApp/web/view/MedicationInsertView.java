package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Form;

/**
 * 
 * @author Amin,Mete
 *
 */
public class MedicationInsertView extends NavigatorContainer implements View {

	
	
	private Form insertForm;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
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

	@Override
	public String setNavBarTitle() {
		// TODO Auto-generated method stub
		return " Insert Medication";
	}

	@Override
	public String setNavBarSubTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setNavBarHelpButtonText() {
		// TODO Auto-generated method stub
		return "Menu";
	}

	@Override
	public String setNavBarMenuButtonText() {
		// TODO Auto-generated method stub
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
