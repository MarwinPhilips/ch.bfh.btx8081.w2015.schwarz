package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * NavigatorContainer to be implemented in every View which 
 * uses the navigation
 * 
 * @author Jonas Mosimann
 * 
 */
@Theme("mytheme")
public abstract class NavigatorContainer extends VerticalLayout implements View {
	
	private static final long serialVersionUID = 1L;
	final HorizontalLayout navigationBar = new HorizontalLayout();
	protected NavigationBarView navBar;
	
	public NavigatorContainer() {
    	setMargin(new MarginInfo(true, true, true, true));
    	navBar = new NavigationBarView();
    	navBar.setTitle(setNavBarTitle());
    	navBar.setSubTitle(setNavBarSubTitle());
    	navBar.setMenuButtonText(setNavBarMenuButtonText());
    	navBar.setHelpButtonText(setNavBarHelpButtonText());
    	navBar.setMenuButtonPath(setNavBarMenuButtonPath());
    	navBar.setHelpButtonPath(setNavBarHelpButtonPath());
    
    	addStyleName("backColorGrey");
        // add NavigationBar on top of view of every screen
        navigationBar.setHeight("60px");
        navigationBar.setWidth("100%");
        navigationBar.addComponent(navBar);
        
        addComponent(navigationBar);
    }
	
	@Override
	public void enter(ViewChangeEvent event) {
		// not used method
	}
	
	public abstract String setNavBarTitle();
	public abstract String setNavBarSubTitle();
	public abstract String setNavBarHelpButtonText();
	public abstract String setNavBarMenuButtonText();
	public abstract String setNavBarHelpButtonPath();
	public abstract String setNavBarMenuButtonPath();

}
