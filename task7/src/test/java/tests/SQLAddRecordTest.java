package tests;

import CRUD.DBCRUD;
import keys.SQLTestKeys;
import listeners.BaseListener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.JsonUtils;
import utils.LoggerUtils;
import utils.MySQLUtils;
import utils.Randoms;

import java.util.ArrayList;

@Listeners(BaseListener.class)
public class SQLAddRecordTest {
    private final int OF_TESTS = JsonUtils.getInt(SQLTestKeys.OF_TESTS, JsonUtils.SQL_DATA);
    private final int FOR_SELECT = JsonUtils.getInt(SQLTestKeys.FOR_SELECT, JsonUtils.SQL_DATA);
    private final ArrayList<pojo.Test> tests = new ArrayList<>();

    @BeforeClass
    public void copySQLTests() {
        LoggerUtils.getLogger().info("-----------TS-2 SELECT TESTS FROM DB-----------");
        MySQLUtils.prepareDB();
        DBCRUD.getContainsTests(tests, OF_TESTS, FOR_SELECT);
        LoggerUtils.getLogger().info("-----------TS-2 COPY TESTS-----------");
        pojo.Test.changeValues(tests);
        DBCRUD.addTests(tests);
        tests.clear();
        DBCRUD.getUpdatedTests(tests, OF_TESTS);
    }

    @Test
    public void simulateFirstTest() {
        LoggerUtils.getLogger().info("-----------TS-2 FIRST TEST-----------");
        pojo.Test test = tests.get(0);
        int anotherId = Randoms.getAnotherID(test.getStatusId());
        test.setStatusId(anotherId);
        Assert.assertEquals(DBCRUD.updateTest(test.getId(), test.getStatusId()), 1, "information in sot updated");
    }

    @Test
    public void simulateSecondTest() {
        LoggerUtils.getLogger().info("-----------TS-2 Second TEST-----------");
        pojo.Test test = tests.get(1);
        test.setStatusId(Randoms.getAnotherID(test.getStatusId()));
        Assert.assertEquals(DBCRUD.updateTest(test.getId(), test.getStatusId()), 1, "information in sot updated");
    }

    @Test
    public void simulateThirdTest() {
        LoggerUtils.getLogger().info("-----------TS-2 Third TEST-----------");
        pojo.Test test = tests.get(2);
        test.setStatusId(Randoms.getAnotherID(test.getStatusId()));
        Assert.assertEquals(DBCRUD.updateTest(test.getId(), test.getStatusId()), 1, "information in sot updated");
    }

    @Test
    public void simulateFourthTest() {
        LoggerUtils.getLogger().info("-----------TS-2 Fourth TEST-----------");
        pojo.Test test = tests.get(3);
        test.setStatusId(Randoms.getAnotherID(test.getStatusId()));
        Assert.assertEquals(DBCRUD.updateTest(test.getId(), test.getStatusId()), 1, "information in sot updated");
    }

    @Test
    public void simulateFifthTest() {
        LoggerUtils.getLogger().info("-----------TS-2 Fifth TEST-----------");
        pojo.Test test = tests.get(4);
        test.setStatusId(Randoms.getAnotherID(test.getStatusId()));
        Assert.assertEquals(DBCRUD.updateTest(test.getId(), test.getStatusId()), 1, "information in sot updated");
    }

    @Test
    public void simulateSixthTest() {
        LoggerUtils.getLogger().info("-----------TS-2 Sixth TEST-----------");
        pojo.Test test = tests.get(5);
        test.setStatusId(Randoms.getAnotherID(test.getStatusId()));
        Assert.assertEquals(DBCRUD.updateTest(test.getId(), test.getStatusId()), 1, "information in sot updated");
    }

    @Test
    public void simulateSeventhTest() {
        LoggerUtils.getLogger().info("-----------TS-2 Seventh TEST-----------");
        pojo.Test test = tests.get(6);
        test.setStatusId(Randoms.getAnotherID(test.getStatusId()));
        Assert.assertEquals(DBCRUD.updateTest(test.getId(), test.getStatusId()), 1, "information in sot updated");
    }

    @Test
    public void simulateEighthTest() {
        LoggerUtils.getLogger().info("-----------TS-2 Eighth TEST-----------");
        pojo.Test test = tests.get(7);
        test.setStatusId(Randoms.getAnotherID(test.getStatusId()));
        Assert.assertEquals(DBCRUD.updateTest(test.getId(), test.getStatusId()), 1, "information in sot updated");
    }

    @Test
    public void simulateNinthTest() {
        LoggerUtils.getLogger().info("-----------TS-2 Ninth TEST-----------");
        pojo.Test test = tests.get(8);
        test.setStatusId(Randoms.getAnotherID(test.getStatusId()));
        Assert.assertEquals(DBCRUD.updateTest(test.getId(), test.getStatusId()), 1, "information in sot updated");
    }

    @AfterClass
    public void deleteSQLTest() {
        LoggerUtils.getLogger().info("-----------TS-2 Delete updated tests-----------");
        Assert.assertEquals(DBCRUD.deleteUpdatedTests(OF_TESTS), tests.size(), "records are not deleted");
    }
}
