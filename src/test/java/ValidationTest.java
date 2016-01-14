import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.MethodOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.WayOfApplication;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MethodOfApplicationRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MssqlEntityManager;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.WayOfApplicationRepository;

public class ValidationTest {
	
	private MethodOfApplicationRepository methodOfApplicationRepository;
	private WayOfApplicationRepository wayOfApplicationRepository;
	
	private BeanItemContainer<MethodOfApplication> containerMethodOfApplication;
	private BeanItemContainer<WayOfApplication> containerWayOfApplication;
	
	private ComboBox methodOfApplicationComboBox;
	private ComboBox wayOfApplicationComboBox;
	
	MssqlEntityManager mem = null;
	
	@Before
	public void setUp() throws Exception {
		mem = new MssqlEntityManager();
		mem.contextInitialized(null);
		
		setUpMethodOfApplication();
		setUpWayOfApplication();
	}
	
	public void setUpMethodOfApplication() {
		methodOfApplicationComboBox = new ComboBox("Method of Application");
		methodOfApplicationComboBox.addValidator(new NullValidator("you must select a method", false));
		
		methodOfApplicationRepository = new MethodOfApplicationRepository();
		
		containerMethodOfApplication = new BeanItemContainer<MethodOfApplication>(MethodOfApplication.class, methodOfApplicationRepository.getAllMethodOfApplication());
		methodOfApplicationComboBox.setContainerDataSource(containerMethodOfApplication);
		methodOfApplicationComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		methodOfApplicationComboBox.setItemCaptionPropertyId("name");		
		methodOfApplicationComboBox.setNullSelectionAllowed(false);
		methodOfApplicationComboBox.setNewItemsAllowed(false);
	}
	
	public void setUpWayOfApplication() {
		wayOfApplicationComboBox = new ComboBox("Method of Application");
		wayOfApplicationComboBox.addValidator(new NullValidator("you must select a method", false));
		
		wayOfApplicationRepository = new WayOfApplicationRepository();
		
		containerWayOfApplication = new BeanItemContainer<WayOfApplication>(WayOfApplication.class, wayOfApplicationRepository.getAllWayOfApplication());
		wayOfApplicationComboBox.setContainerDataSource(containerWayOfApplication);
		wayOfApplicationComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		wayOfApplicationComboBox.setItemCaptionPropertyId("name");		
		wayOfApplicationComboBox.setNullSelectionAllowed(false);
		wayOfApplicationComboBox.setNewItemsAllowed(false);
	}

	@Test
	public void testIfMethodApplicationIsSeleced() {
		methodOfApplicationComboBox.select(containerMethodOfApplication.firstItemId());
		try {
			methodOfApplicationComboBox.validate();
		} catch (InvalidValueException e) {
			fail("InvalidValueException of methodOfApplication");
		}
		assertTrue("OK", methodOfApplicationComboBox.isValid());
	}
	
	@Test
	public void testIfMethodApplicationIsNotSeleced() {
		try {
            methodOfApplicationComboBox.validate();
        } catch (InvalidValueException e) {
            return;
        }
		fail("no selection in methodOfApplication is not working");
	}
	
	@Test
	public void testIfWayOfApplicationIsSeleced() {
		wayOfApplicationComboBox.select(containerWayOfApplication.firstItemId());
		try {
			wayOfApplicationComboBox.validate();
		} catch (InvalidValueException e) {
			fail("InvalidValueException of wayOfApplication");
		}
		assertTrue("OK", wayOfApplicationComboBox.isValid());
	}
	
	@Test
	public void testIfWayOfApplicationIsNotSeleced() {
		try {
            wayOfApplicationComboBox.validate();
        } catch (InvalidValueException e) {
            return;
        }
		fail("no selection in wayOfApplication is not working");
	}
	
	@After
	public void destroy(){
		mem.contextDestroyed(null);
	}

}
