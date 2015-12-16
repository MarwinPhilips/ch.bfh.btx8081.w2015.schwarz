package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;


public class NavigationBarView extends HorizontalLayout  {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Label title = new Label();
    Label subTitle = new Label();
    ButtonListener menuButtonListener = new ButtonListener();
    ButtonListener helpButtonListener = new ButtonListener();
    Button menuButton = new Button(null, menuButtonListener);
    Button helpButton = new Button(null, helpButtonListener);
    
    class ButtonListener implements Button.ClickListener {
    	private static final long serialVersionUID = 1L;
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
    	titleArea.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    	
    	titleArea.addComponent(title);
    	titleArea.addComponent(subTitle);

    	title.setWidthUndefined();
    	subTitle.setWidthUndefined();
	
        menuButton.setWidth("100px");
        helpButton.setWidth("100px");
        
        addComponent(menuButton);
        addComponent(titleArea);
        addComponent(helpButton);
        
        setWidth("100%");
        setExpandRatio(titleArea, 1);
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
    	if(text == null){
    		this.helpButton.setVisible(false);
    	}else{
    		this.helpButton.setVisible(true);
    		this.helpButton.setCaption(text);
    	}
	}
    
    public void setMenuButtonPath(String text) {
    	this.menuButtonListener.navigateTo = text;
    }
    
    public void setHelpButtonPath(String text) {
    	this.helpButtonListener.navigateTo = text;
    }
    
}