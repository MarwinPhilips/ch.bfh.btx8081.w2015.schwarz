package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import java.util.Date;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MedicationListRepository;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TwinColSelect;

/**
 * 
 * @author Amin,Mete
 *
 */
public class MedicationInsertView extends NavigatorContainer implements View {

	
	
	private FieldGroup insertForm; 
	private static final long serialVersionUID = 1L;
	private Button saveButton = new Button("Save", new SaveButtonListener());
	private GridLayout form ;
	private MedicationListRepository medicationsList;
	
	/**
	 * @param insertForm
	 */
	public MedicationInsertView() {
		super();
		setSizeFull();
		
		form = new GridLayout(2, 3);

	    ComboBox medicationName = new ComboBox ("Medications name:");
	    CheckBox  reserveTye = new CheckBox ("Reserved:");
	    
	    TwinColSelect  medicationTakingType = new TwinColSelect("Medication taking type:");
	   
	    for (int i = 0; i < 3; i++) {
	    	medicationTakingType.addItem(i);
	    	medicationTakingType.setItemCaption(i, "Option " + i);
        }
	    medicationTakingType.setItemCaption(0, "Morning");
	    medicationTakingType.setItemCaption(1, "Noon");
	    medicationTakingType.setItemCaption(2, "Afternoon");
	    medicationTakingType.setItemCaption(3, "Night");
	    
	    
	    
	    PopupDateField  startDatum = new PopupDateField ("Start Date:");
	    startDatum.setValue(new Date());
	    
	    PopupDateField  endDatum = new PopupDateField ("End Date:");
	    endDatum.setValue(new Date());
	    
	    
	    DateField dateOfBirth = new DateField("remark:");
	    TextArea comments = new TextArea("Comments:");
	    
	    insertForm=new FieldGroup();
	    insertForm.bind(medicationName, "MedicationName");
	    insertForm.bind(reserveTye, "lastName");
	    insertForm.bind(medicationTakingType, "MedTakeType");
	    insertForm.bind(startDatum, "StartDate");
	    insertForm.bind(endDatum, "EndDate");
	    insertForm.bind(dateOfBirth, "dateOfBirth");
	    insertForm.bind(comments, "comments");

	    form.addComponent(medicationName);
	    form.addComponent(reserveTye);
	    form.addComponent(medicationTakingType);
	    form.addComponent(startDatum);
	    form.addComponent(endDatum);
	    form.addComponent(comments);
	    form.addComponent(saveButton);
	    addComponent(form);
	}
	
	class SaveButtonListener implements Button.ClickListener {
    	private static final long serialVersionUID = 1L;
		 
        @Override
        public void buttonClick(ClickEvent event) {
        	//TODO: Validate and save the values to the database
        	try {
				insertForm.commit();
			} catch (CommitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	//MyMedicationApp.navigateTo("medication");
        	Notification.show("Hallo");
        }
    }

	/**
	 * Create the MEdications combo box to insert a new medication
	 */
	private void createMedicationComboBox(){
		ComboBox medicationComboBox = new ComboBox("Select your medication");
		medicationsList = new MedicationListRepository();
		medicationComboBox.addItem(medicationsList);
		form.addComponent(medicationComboBox);
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
