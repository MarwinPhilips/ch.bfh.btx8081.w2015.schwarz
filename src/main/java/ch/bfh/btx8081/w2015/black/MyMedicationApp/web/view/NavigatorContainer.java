package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public abstract class NavigatorContainer extends VerticalLayout implements View {
	
	final HorizontalLayout navigationBar = new HorizontalLayout();
	
	
	public NavigatorContainer() {
    	setSizeFull();
    	
    	NavigationBarView navBar = new NavigationBarView();
    	navBar.setTitle(setNavBarTitle());
    	navBar.setSubTitle(setNavBarSubTitle());
    	navBar.setMenuButtonText(setNavBarMenuButtonText());
    	navBar.setHelpButtonText(setNavBarHelpButtonText());
    	navBar.setMenuButtonPath(setNavBarMenuButtonPath());
    	navBar.setHelpButtonPath(setNavBarHelpButtonPath());
    
        // add NavigationBar on top of view of every screen
        navigationBar.setHeight("100px");
        navigationBar.setWidth("100%");
        navigationBar.addComponent(navBar);
        
        addComponent(navigationBar);
    }
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public abstract String setNavBarTitle();
	public abstract String setNavBarSubTitle();
	public abstract String setNavBarHelpButtonText();
	public abstract String setNavBarMenuButtonText();
	public abstract String setNavBarHelpButtonPath();
	public abstract String setNavBarMenuButtonPath();

}
