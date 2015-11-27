package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("ch.bfh.btx8081.w2015.black.MyMedicationApp.MyAppWidgetset")
public class MyUI extends UI {
	private static Navigator navigator;
	
	protected static final String MEDICATION_VIEW = "medication";
	protected static final String ACTIVITY_FEEDBACK_VIEW = "activity";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("MyMedicationApp");
    	
    	navigator = new Navigator(this, this);
    	
    	// Create and register the views
        navigator.addView("", new MainView()); //TODO: Add MediView
        navigator.addView(MEDICATION_VIEW, new MainView()); //TODO: Add MediView
        navigator.addView(ACTIVITY_FEEDBACK_VIEW, new DummyView("Activity")); //TODO: Add Feeback View
    }
    
    public static void navigateTo(String view) {
    	navigator.navigateTo(view);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    
    /** A start view for navigating to the main view */
    public class MainView extends VerticalLayout implements View {
        public MainView() {
            final VerticalLayout layout = new VerticalLayout();   
            layout.setSizeFull();
        
            final HorizontalLayout navigationBar = new HorizontalLayout();
            navigationBar.setHeight("100px");
            navigationBar.setWidth("100%");
            
            final HorizontalLayout mainContainer = new HorizontalLayout();
            mainContainer.setSizeFull();
            
            //mainContainer.addComponent(new MedicationOverView());
            
            navigationBar.setWidth("100%");
            navigationBar.addComponent(new NavigationBarView());
            
            layout.addComponent(navigationBar);
            layout.addComponent(mainContainer);
            
            layout.setExpandRatio(mainContainer, 1);
            
            addComponent(layout);
        }        
            
        @Override
        public void enter(ViewChangeEvent event) {
        	System.out.println(event.getParameters());
            //Notification.show("MEDI_VIEW");
        }
    }
    
    /** A start view for navigating to the main view */
    public class DummyView extends VerticalLayout implements View {
        public DummyView(String viewName) {
            setSizeFull();

            Button button = new Button("go to other view",
                    new Button.ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    navigator.navigateTo(MEDICATION_VIEW);
                }
            });
            addComponent(button);
            setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        }        
            
        @Override
        public void enter(ViewChangeEvent event) {
            Notification.show("FEEDBACK_VIEW");
        }
    }
}
