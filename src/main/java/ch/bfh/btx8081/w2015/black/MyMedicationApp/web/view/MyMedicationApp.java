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
public class MyMedicationApp extends UI {
	private static Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("MyMedicationApp");
    	
    	navigator = new Navigator(this, this);
    	
    	// Create and register the views
        navigator.addView("", new DummyView());
        navigator.addView("medication", new DummyView());
        navigator.addView("activity", new DummyView2());
    }
    
    public static void navigateTo(String view) {
    	navigator.navigateTo(view);
    }

    @WebServlet(urlPatterns = "/*", name = "MyMedicationAppServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyMedicationApp.class, productionMode = false)
    public static class MyMedicationAppServlet extends VaadinServlet {
    }
}
