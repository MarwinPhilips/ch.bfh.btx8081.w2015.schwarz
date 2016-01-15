package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 *
 */
@Theme("mytheme")
@Widgetset("ch.bfh.btx8081.w2015.black.MyMedicationApp.MyAppWidgetset")
public class MyMedicationApp extends UI {
	private static final long serialVersionUID = 1L;
	private static Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("MyMedicationApp");
    	
    	navigator = new Navigator(this, this);
    	MedicationInsertView insertView = new MedicationInsertView();
    	MedicationEditView editView = new MedicationEditView();
    	MedicationOverView overView = new MedicationOverView(editView);
    	// Create and register the views
        navigator.addView("", overView);
        navigator.addView("medication", overView);
        //navigator.addView("activity", new DummyView2());
        navigator.addView("MedicationInsertView", insertView);
        navigator.addView("MedicationEditView", editView);
    }
    
    public static void navigateTo(String view) {
    	navigator.navigateTo(view);
    }

    @WebServlet(urlPatterns = "/*", name = "MyMedicationAppServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyMedicationApp.class, productionMode = false)
    public static class MyMedicationAppServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
    }
}
