package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;

public class DummyView2 extends NavigatorContainer {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1;

	public DummyView2() {
    	
        setSizeFull();

        Label actualView = new Label("Actual View: DummyView2");
        addComponent(actualView);
        setComponentAlignment(actualView, Alignment.MIDDLE_CENTER);
    }

	@Override
	public String setNavBarTitle() {
		return "DummyView2";
	}

	@Override
	public String setNavBarSubTitle() {
		return "subtitle";
	}

	@Override
	public String setNavBarHelpButtonText() {
		return "!";
	}

	@Override
	public String setNavBarMenuButtonText() {
		return "1";
	}

	@Override
	public String setNavBarMenuButtonPath() {
		return "medication";
	}

	@Override
	public String setNavBarHelpButtonPath() {
		return null;
	}
}
