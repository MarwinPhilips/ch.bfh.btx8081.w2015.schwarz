package ch.bfh.btx8081.w2015.black.MyMedicationApp.web.view;

import com.vaadin.annotations.Theme;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * NavigationBarView which contains a title, subtitle,
 * two buttons for navigating
 * 
 * @author Jonas Mosimann
 * 
 */
@Theme("mytheme")
public class NavigationBarView extends HorizontalLayout  {
    
    private static final long serialVersionUID = 1L;
	Label title = new Label();
    Label subTitle = new Label();
    ButtonListener menuButtonListener = new ButtonListener();
    ButtonListener helpButtonListener = new ButtonListener();
    Button menuButton = new Button(null, menuButtonListener);
    Button helpButton = new Button(null, helpButtonListener);
    
    private static class ButtonListener implements Button.ClickListener {
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
        
        addComponent(menuButton);
        addComponent(titleArea);
        addComponent(helpButton);
        
        addStyleName("titleLabel");
        setWidth("100%");
        setExpandRatio(titleArea, 1);
    }
    
    /**
	 * Sets the title
	 * @param	title	The title of the view
	 */
    public void setTitle(String title) {
    	this.title.setValue(title);
    }
    
    /**
	 * Sets the subtitle
	 * @param	subTitle	The subtitle of the view
	 */
    public void setSubTitle(String subTitle) {
    	this.subTitle.setValue(subTitle);
    }
    
    /**
	 * Sets the text of the menu button
	 * @param	text	The text of menu button
	 */
    public void setMenuButtonText(String text) {
    	if(text == null){
    		this.menuButton.setVisible(false);
    	}else{
    		this.menuButton.setVisible(true);
    		this.menuButton.setCaption(text);
    	}
    }

    /**
	 * Sets the text of the help button
	 * @param	text	The text of help button
	 */
    public void setHelpButtonText(String text) {
    	if(text == null){
    		this.helpButton.setVisible(false);
    	}else{
    		this.helpButton.setVisible(true);
    		this.helpButton.setCaption(text);
    	}
	}
    
    /**
	 * Sets the path of the location for the menu button
	 * @param	path	The path
	 */
    public void setMenuButtonPath(String path) {
    	this.menuButtonListener.navigateTo = path;
    }
    
    /**
	 * Sets the path of the location for the help button
	 * @param	path	The path
	 */
    public void setHelpButtonPath(String path) {
    	this.helpButtonListener.navigateTo = path;
    }
    
}