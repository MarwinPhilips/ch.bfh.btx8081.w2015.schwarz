package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.DosisScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MedicationEditModel;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MethodOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.Prescription;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.WayOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.PrescriptionStates.PrescriptionContext;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * This view allows to edit the selected medication from the list.
 * 
 * @author BlackTeam
 */
public class MedicationEditView extends NavigatorContainer implements View,
		Observer {
	private static final long serialVersionUID = 1L;

	private FormLayout formLayoutEditView;
	private VerticalLayout dosisSchemaVerticalLayout;

	private Button saveButton;
	private TextArea prescriptionCommentTextArea;
	private ComboBox methodOfApplicationComboBox;
	private ComboBox wayOfApplicationComboBox;
	private ComboBox timeSchemeComboBox;
	private CheckBox reserveMedicamentCheckBox;
	private PopupDateField prescriptionEndDateDateField;
	private PopupDateField prescriptionStartDateDateField;
	private TextField prescriptionMedicamentNameTextField;
	private MedicationEditModel medicationEditModel;

	private BeanItemContainer<WayOfApplication> wayOfApplicationContainer;
	private BeanItemContainer<TimeScheme> timeSchemeContainer;

	/**
	 * @param formLayoutEditView
	 *            Construct a view to edit selected medication.
	 */
	public MedicationEditView() {
		super();
		medicationEditModel = new MedicationEditModel();
		medicationEditModel.loadData();
		createForm();
		medicationEditModel.addObserver(this);
	}

	/**
	 * Create the main form in edit view.
	 */
	private void createForm() {
		formLayoutEditView = new FormLayout();
		Panel p = new Panel();
		p.setContent(formLayoutEditView);
		p.setSizeFull();
		addComponent(p);

		prescriptionMedicamentNameTextField = new TextField("Your medication:");
		formLayoutEditView.addComponent(prescriptionMedicamentNameTextField);
		reserveMedicamentCheckBox = new CheckBox("Reserve");
		formLayoutEditView.addComponent(reserveMedicamentCheckBox);
		timeSchemeComboBox = new ComboBox("Select your time schema: ");

		timeSchemeComboBox
				.addValueChangeListener(new Property.ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent event) {
						TimeScheme eventTimeScheme = (TimeScheme) event
								.getProperty().getValue();
						// If the selected TimeScheme is the same as the one we already have selected, nothing happens.
						if (eventTimeScheme.getTimeSchemeId() != medicationEditModel
								.getPrescription().getTimeScheme()
								.getTimeSchemeId()){
							medicationEditModel
									.setPrescriptionTimeScheme(eventTimeScheme);
						fillTimeSchemeTimes();}
					}
				});

		timeSchemeContainer = new BeanItemContainer<TimeScheme>(
				TimeScheme.class, medicationEditModel.getTimeSchemes());
		timeSchemeComboBox.setContainerDataSource(timeSchemeContainer);
		setComboboxProperties(timeSchemeComboBox);
		formLayoutEditView.addComponent(timeSchemeComboBox);

		dosisSchemaVerticalLayout = new VerticalLayout();
		formLayoutEditView.addComponent(dosisSchemaVerticalLayout);

		methodOfApplicationComboBox = new ComboBox("Method of Application");
		BeanItemContainer<MethodOfApplication> containerMethod = new BeanItemContainer<MethodOfApplication>(
				MethodOfApplication.class,
				medicationEditModel.getMethodOfApplications());
		methodOfApplicationComboBox.setContainerDataSource(containerMethod);
		setComboboxProperties(methodOfApplicationComboBox);
		formLayoutEditView.addComponent(methodOfApplicationComboBox);

		wayOfApplicationComboBox = new ComboBox("Way of Application");
		wayOfApplicationContainer = new BeanItemContainer<WayOfApplication>(
				WayOfApplication.class,
				medicationEditModel.getWayOfApplications());
		wayOfApplicationComboBox
				.setContainerDataSource(wayOfApplicationContainer);
		setComboboxProperties(wayOfApplicationComboBox);
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

	/**
	 * Create and fill the content of form.
	 */
	private void fillForm() {
		Prescription p = medicationEditModel.getPrescription();
		if (p != null) {
			prescriptionMedicamentNameTextField.setValue(p.getMedicament()
					.getName());
			prescriptionMedicamentNameTextField.setEnabled(false);
			prescriptionCommentTextArea.setValue(p.getComment());
			prescriptionStartDateDateField.setValue(p.getStartDate().getTime());
			if (p.getEndDate() != null) {
				prescriptionEndDateDateField.setValue(p.getEndDate().getTime());
			}

			reserveMedicamentCheckBox.setValue(p.isReserveMedication());
			
			timeSchemeComboBox.select(p.getTimeScheme());
			wayOfApplicationComboBox.select(p.getWayOfApplication());
			methodOfApplicationComboBox.select(p.getMethodOfApplication());

			fillTimeSchemeTimes();
		}
	}

	/**
	 * Add Time schema to the From.
	 */
	private void fillTimeSchemeTimes() {
		dosisSchemaVerticalLayout.removeAllComponents();
		for (DosisScheme d : medicationEditModel.getPrescription()
				.getDosisSchemes()) {
			dosisSchemaVerticalLayout.addComponent(new DosisSchemeTimeView(d,
					medicationEditModel));
		}
	}

	/**
	 * Set properties of each Combo box in the From.
	 * 
	 * @param cb
	 *            respective combo box
	 */
	private void setComboboxProperties(ComboBox cb) {
		cb.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		cb.setItemCaptionPropertyId("name");
		cb.setNullSelectionAllowed(false);
		cb.setNewItemsAllowed(false);
		cb.setImmediate(true);
	}

	/**
	 * Save the changed medication and respective information for certain
	 * Person.
	 */
	private void save() {
		try {
			// medicationNamesComboBox.validate();
		} catch (InvalidValueException e) {
			Notification.show(e.getMessage());
			// medicationNamesComboBox.setValidationVisible(true);
			return;
		}
		Prescription p = medicationEditModel.getPrescription();

		p.setPerson(medicationEditModel.getLoggedInPerson());
		p.setComment(prescriptionCommentTextArea.getValue());
		p.setReserveMedication(reserveMedicamentCheckBox.getValue());

		GregorianCalendar startDateGregorian = new GregorianCalendar();
		startDateGregorian.setTime(prescriptionStartDateDateField.getValue());
		p.setStartDate(startDateGregorian);
		/*
		 * GregorianCalendar endDateGregorian = new GregorianCalendar();
		 * endDateGregorian.setTime(prescriptionEndDateDateField.getValue());
		 * p.setEndDate(endDateGregorian);
		 */

		p.setMethodOfApplication((MethodOfApplication) methodOfApplicationComboBox
				.getValue());
		p.setWayOfApplication((WayOfApplication) wayOfApplicationComboBox
				.getValue());

		medicationEditModel.save();
		MyMedicationApp.navigateTo("medication");

	}

	/**
	 * Set the title of navigations bar for this form.
	 */
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

	/**
	 * Set the text of navigations bar menu for this form.
	 */
	@Override
	public String setNavBarMenuButtonText() {
		// TODO Should go to MedicatoinOverView without save of the edited Value
		return "Overview";
	}

	@Override
	public String setNavBarHelpButtonPath() {
		return null;
	}

	/**
	 * Set the path of navigations bar menu for this form.
	 */
	@Override
	public String setNavBarMenuButtonPath() {
		return "medication";
	}

	@Override
	public void update(Observable o, Object arg) {
		fillForm();
	}

	/**
	 * Set the context of Prescription and load data from this model.
	 * 
	 * @param prescriptionContext
	 *            context of Prescription
	 */
	public void setPrescriptionContext(PrescriptionContext prescriptionContext) {
		medicationEditModel.setPrescriptionContext(prescriptionContext);
		medicationEditModel.loadData();
	}

}
