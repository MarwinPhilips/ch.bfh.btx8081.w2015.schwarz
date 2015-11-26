package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;


public class NavigationBarView extends HorizontalLayout  {
    
    Label title = new Label("TITLE");
    Label subTitle = new Label("SUBTITLE");
    Button menuButton = new Button("Menu");
    Button helpButton = new Button("Help");
    
    public NavigationBarView(){  
	
        addComponent(title);
        addComponent(subTitle);
	
    }
    
    



}
