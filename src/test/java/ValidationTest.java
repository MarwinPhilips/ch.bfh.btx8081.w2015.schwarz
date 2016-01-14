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
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MethodOfApplicationRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MssqlEntityManager;

public class ValidationTest {
	
	private MethodOfApplicationRepository methodOfApplicationRepository;
	private BeanItemContainer<MethodOfApplication> container;
	
	private ComboBox methodOfApplicationComboBox;
	MssqlEntityManager mem = null;
	
	@Before
	public void setUp() throws Exception {
		mem = new MssqlEntityManager();
		mem.contextInitialized(null);
		
		methodOfApplicationComboBox = new ComboBox("Method of Application");
		methodOfApplicationComboBox.addValidator(new NullValidator("you must select a method", false));
		
		methodOfApplicationRepository = new MethodOfApplicationRepository();
		
		container = new BeanItemContainer<MethodOfApplication>(MethodOfApplication.class, methodOfApplicationRepository.getAllMethodOfApplication());
		methodOfApplicationComboBox.setContainerDataSource(container);
		methodOfApplicationComboBox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		methodOfApplicationComboBox.setItemCaptionPropertyId("name");		
		methodOfApplicationComboBox.setNullSelectionAllowed(false);
		methodOfApplicationComboBox.setNewItemsAllowed(false);
	}

	@Test
	public void testIfApplicationIsSeleced() {
		methodOfApplicationComboBox.select(container.firstItemId());
		try {
			methodOfApplicationComboBox.validate();
		} catch (InvalidValueException e) {
			fail("InvalidValueException of methodOfApplication");
		}
		assertTrue("OK", methodOfApplicationComboBox.isValid());
	}
	
	@Test
	public void testIfApplicationIsNotSeleced() {
		try {
            methodOfApplicationComboBox.validate();
        } catch (InvalidValueException e) {
            return;
        }
		fail("no selection in methodOfApplication is not working");
	}
	
	@After
	public void destroy(){
		mem.contextDestroyed(null);
	}

}
