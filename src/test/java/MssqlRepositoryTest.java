import static org.junit.Assert.*;

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


public class MssqlRepositoryTest {

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
		Duration settedDuration = Duration.ofHours(6l);
		tst.setTime(settedDuration);
		tst.setTimeScheme(ts);
		timeSchemeRepo.persist(ts);
		ts.getTimeSchemeTimes().add(tst);
		timeSchemeTimeRepo.persist(tst);
		TimeScheme loadedTs = (TimeScheme) timeSchemeRepo.getById(TimeScheme.class, ts.getTimeSchemeId());
		TimeSchemeTime loadedTST = loadedTs.getTimeSchemeTimes().get(0);
		assertTrue(loadedTST.getTimespan().equals(settedDuration));
		timeSchemeTimeRepo.remove(loadedTST);
		timeSchemeRepo.remove(loadedTs);
		TimeScheme againLoadedTs = (TimeScheme) timeSchemeRepo.getById(TimeScheme.class, ts.getTimeSchemeId());
		assertTrue(againLoadedTs==null);
	}
}
