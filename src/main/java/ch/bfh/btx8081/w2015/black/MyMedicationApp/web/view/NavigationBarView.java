package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


public class NavigationBarView extends HorizontalLayout  {
    
    Label title = new Label();
    Label subTitle = new Label();
    ButtonListener menuButtonListener = new ButtonListener();
    ButtonListener helpButtonListener = new ButtonListener();
    Button menuButton = new Button(null, menuButtonListener);
    Button helpButton = new Button(null, helpButtonListener);
    
    class ButtonListener implements Button.ClickListener {
    	String navigateTo;
        
        @Override
        public void buttonClick(ClickEvent event) {
            // Navigate to a specific state
        	MyMedicationApp.navigateTo(navigateTo);
        }
    }
    
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
    
    public void setTitle(String title) {
    	this.title.setValue(title);
    }
    
    public void setSubTitle(String subTitle) {
    	this.subTitle.setValue(subTitle);
    }
    
    public void setMenuButtonText(String text) {
    	this.menuButton.setCaption(text);
    }

    public void setHelpButtonText(String text) {
    	this.helpButton.setCaption(text);
	}
    
    public void setMenuButtonPath(String text) {
    	this.menuButtonListener.navigateTo = text;
    }
    
    public void setHelpButtonPath(String text) {
    	this.helpButtonListener.navigateTo = text;
    }
}