package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;

public class DummyView extends NavigatorContainer {
	
    public DummyView() {
    	
        setSizeFull();

        Label actualView = new Label("Actual View: DummyView1");
        addComponent(actualView);
        setComponentAlignment(actualView, Alignment.MIDDLE_CENTER);
    }

	@Override
	public String setNavBarTitle() {
		return "DummyView1";
	}

	@Override
	public String setNavBarSubTitle() {
		return "subtitle";
	}

	@Override
	public String setNavBarHelpButtonText() {
		return "?";
	}

	@Override
	public String setNavBarMenuButtonText() {
		return "2";
	}

	@Override
	public String setNavBarMenuButtonPath() {
		return "activity";
	}

	@Override
	public String setNavBarHelpButtonPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
