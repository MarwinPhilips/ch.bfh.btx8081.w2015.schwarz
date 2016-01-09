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
	 */
	public MedicationEditView() {
		super();
		medicationEditModel = new MedicationEditModel();
		medicationEditModel.loadData();
		createForm();
		medicationEditModel.addObserver(this);
	}

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
						medicationEditModel
								.setPrescriptionTimeScheme((TimeScheme) event
										.getProperty().getValue());
						fillTimeSchemeTimes();
					}
				});

		timeSchemeContainer = new BeanItemContainer<TimeScheme>(
				TimeScheme.class, medicationEditModel.getTimeSchemes());
		timeSchemeComboBox.setContainerDataSource(timeSchemeContainer);

		formLayoutEditView.addComponent(timeSchemeComboBox);

		dosisSchemaVerticalLayout = new VerticalLayout();
		formLayoutEditView.addComponent(dosisSchemaVerticalLayout);

		methodOfApplicationComboBox = new ComboBox("Method of Application");
		BeanItemContainer<MethodOfApplication> containerMethod = new BeanItemContainer<MethodOfApplication>(
				MethodOfApplication.class,
				medicationEditModel.getMethodOfApplications());
		methodOfApplicationComboBox.setContainerDataSource(containerMethod);
		setComboboxProperties(methodOfApplicationComboBox);
		setComboboxProperties(timeSchemeComboBox);
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

			// TODO load setted dropdown values
			timeSchemeComboBox.select(p.getTimeScheme());
			wayOfApplicationComboBox.select(p.getWayOfApplication());
			methodOfApplicationComboBox.select(p.getMethodOfApplication());

			fillTimeSchemeTimes();
		}
	}

	private void fillTimeSchemeTimes() {
		dosisSchemaVerticalLayout.removeAllComponents();
		for (DosisScheme d : medicationEditModel.getPrescription()
				.getDosisSchemes()) {
			dosisSchemaVerticalLayout.addComponent(new DosisSchemeTimeView(d,
					medicationEditModel));
		}
	}

	private void setComboboxProperties(ComboBox cb) {
		cb.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		cb.setItemCaptionPropertyId("name");
		cb.setNullSelectionAllowed(false);
		cb.setNewItemsAllowed(false);
	}

	private void save() {
		// TODO: Validate before saving
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

		// TODO why does is not save
		medicationEditModel.save();
		MyMedicationApp.navigateTo("medication");

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
