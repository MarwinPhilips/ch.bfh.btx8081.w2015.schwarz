package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


public class NavigationBarView extends HorizontalLayout  {
    
    Label title = new Label("TITLE");
    Label subTitle = new Label("SUBTITLE");
    Button menuButton = new Button("menu", new Button.ClickListener() {
        @Override
        public void buttonClick(ClickEvent event) {
            MyUI.navigateTo("activity");
        }
    });
    Button helpButton = new Button("Help");
    
    public NavigationBarView(){  
    	final VerticalLayout titleArea = new VerticalLayout();
    	titleArea.setHeight("100%");
    	titleArea.setWidth("60%");
    	
    	titleArea.addComponent(title);
    	titleArea.addComponent(subTitle);
	
        menuButton.setWidth("20%");
        helpButton.setWidth("20%");
        
        addComponent(menuButton);
        addComponent(titleArea);
        addComponent(helpButton);
	
    }
}
