package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("ch.bfh.btx8081.w2015.black.MyMedicationApp.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();   
        layout.setSizeFull();
        setContent(layout);
        
        
        final HorizontalLayout navigationBar = new HorizontalLayout();
        navigationBar.setHeight("100px");
        navigationBar.setWidth("100%");
        
        
        final HorizontalLayout mainContainer = new HorizontalLayout();
        mainContainer.setSizeFull();
        
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
        	mainContainer.addComponent(new Label("Thank you for clicking"));
            }
        });
        //navigationBar.addComponent(button);
        navigationBar.addComponent(new NavigationBarView());
        
        layout.addComponent(navigationBar);
        layout.addComponent(mainContainer);
        
        layout.setExpandRatio(mainContainer, 1);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
