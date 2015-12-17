package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.DosisScheme;

public class DosisSchemeTimeView extends HorizontalLayout {
	private static final long serialVersionUID = 1L;
	private DosisScheme dosisScheme;
	private Label timeSchemeName;
	private Label time;
	private TextField medicamentAmount;
	private Label dose;
	
	public DosisSchemeTimeView(DosisScheme d) {
		this.dosisScheme = d;
		this.timeSchemeName = new Label(dosisScheme.getDosisSchemeName()+": ");
		addComponent(timeSchemeName);
		this.time = new Label(dosisScheme.getTimespan().toHours() + "h");
		addComponent(time);
		this.medicamentAmount = new TextField();
		this.medicamentAmount.setValue(dosisScheme.getAmount()+"");
		medicamentAmount.addValueChangeListener(new Property.ValueChangeListener() {
			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				dosisScheme.setAmount(Double.parseDouble(medicamentAmount.getValue()));
			}
		});
		addComponent(medicamentAmount);
		this.dose = new Label(dosisScheme.getQuantityUnit());
		addComponent(dose);
	}
}
