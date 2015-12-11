package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;


import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationEditModel;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateField;


/**
 * 
 * @author Amin,Mete
 *
 */
public class MedicationEditView extends NavigatorContainer implements View, Observer{
	
	private MedicationEditModel medicationEditModel;
	private FormLayout formLayoutEditView = null; // TODO: Form() is deprecated! Use FielGroup instead
	private HorizontalLayout horizontalLayoutEditView = null;
    private Button saveButton = new Button("Save", new SaveButtonListener());
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * @param formLayoutEditView
	 */
	public MedicationEditView() {
		super();
		medicationEditModel = new MedicationEditModel();
		medicationEditModel.addObserver(this);
		medicationEditModel.loadData();
		
		formLayoutEditView = new FormLayout();
		horizontalLayoutEditView = new HorizontalLayout();


		createView();
		
		addComponent(formLayoutEditView);
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
		return "Edit medication";
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
		formLayoutEditView = new FormLayout();
		getPrescriptionNote();
		createHorizontalTimeScheme();
		getPrescriptionEndDate();
		getPrescriptionDosisScheme();
		getReserveMedication();
		formLayoutEditView.addComponent(saveButton);
	}
	
	
	private void createHorizontalTimeScheme(){
		horizontalLayoutEditView = new HorizontalLayout();
		
		TextField tf = new TextField("Mo:");
		tf.setValue(String.valueOf(medicationEditModel.getTimeSchemes()));
		horizontalLayoutEditView.addComponent(tf);
		
		TextField tf1 = new TextField("Mi:");
		tf1.setValue(String.valueOf(medicationEditModel.getTimeSchemes()));
		horizontalLayoutEditView.addComponent(tf1);

		TextField tf2 = new TextField("Ab:");
		tf2.setValue(String.valueOf(medicationEditModel.getTimeSchemes()));
		horizontalLayoutEditView.addComponent(tf2);
		
		TextField tf3 = new TextField("Na:");
		tf3.setValue(String.valueOf(medicationEditModel.getTimeSchemes()));
		horizontalLayoutEditView.addComponent(tf3);
		
		formLayoutEditView.addComponent(horizontalLayoutEditView);
	}

	
	private void getPrescriptionDosisScheme(){
//		setPrescriptionId(1);
//		TextField tf = new TextField();
//		tf.setValue(String.valueOf(medicationEditModel.getTimeSchemes()));
//		formLayoutEditView.addComponent(tf);
	}
	
	// TODO: TextFild tf should be bigger ^^
	private void getPrescriptionNote(){
		TextField tf = new TextField("Prescription Note:");
		String st = new String();
		setPrescriptionId(1);
		st = medicationEditModel.getPrescription().getComment();
		tf.setValue(st);
		formLayoutEditView.addComponent(tf);
	}
	
	// TODO: Set the right Date of Prescription, actually its only a example 
	private void getPrescriptionEndDate(){
		GregorianCalendar g = new GregorianCalendar();
		Date d = new Date();
		d = g.getTime();
		DateField pDate = new DateField();
		pDate.setData(d);
		pDate.setValue(d);
//		DateField dateField = new DateField("End Date:");
//		Date pDate = new Date();
//		setPrescriptionId(1);
//		pDate = medicationEditModel.getPrescription().getEndDate().getTime();
//		dateField.setData(pDate);
		formLayoutEditView.addComponent(pDate);
	}
	
	private void getReserveMedication(){
		OptionGroup single = new OptionGroup("Reserve Medication:");
		single.addItems("Yes", "No");
		setPrescriptionId(1);
		formLayoutEditView.addComponent(single);
		if (medicationEditModel.getPrescription().isReserveMedication() == true){
		single.select("Yes");} else
			single.select("No");
	}
	
	public void setPrescriptionId(int prescriptionId) {
		medicationEditModel.setPrescriptionId(prescriptionId);
	}

}
