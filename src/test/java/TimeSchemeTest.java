import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeScheme;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.businessLogic.model.TimeSchemeTime;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.MssqlEntityManager;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.TimeSchemeRepository;
import ch.bfh.btx8081.w2015.black.MyMedicationApp.dataLayer.dataModel.TimeSchemeTimeRepository;


public class TimeSchemeTest {
	MssqlEntityManager mem = null;
	TimeSchemeRepository timeSchemeRepo = null;
	TimeSchemeTimeRepository timeSchemeTimeRepo = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
		mem = new MssqlEntityManager();
		mem.contextInitialized(null);
		timeSchemeRepo = new TimeSchemeRepository();
		timeSchemeTimeRepo = new TimeSchemeTimeRepository();
	}
	
	@After
	public void destroy(){
		mem.contextDestroyed(null);
	}

	@Test
	public void test() {
		TimeScheme ts = timeSchemeRepo.getNewTimeScheme();
		TimeSchemeTime tst = timeSchemeTimeRepo.getNewTimeSchemeTime();
		tst.setTime(Duration.ofHours(6l));
		tst.setTimeScheme(ts);
		ts.getTimeSchemeTimes().add(tst);
	}

}
