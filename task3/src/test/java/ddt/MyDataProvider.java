package ddt;

import org.testng.annotations.DataProvider;
import utils.ConfigUtils;

public class MyDataProvider {
    @DataProvider
    public static Object[][] testData() {
        return new Object[][] {
                new Object[] {ConfigUtils.getTestProperty("FFName"), ConfigUtils.getTestProperty("FSName"), ConfigUtils.getTestProperty("FEmail"), ConfigUtils.getTestProperty("FAge"), ConfigUtils.getTestProperty("FSalary"), ConfigUtils.getTestProperty("FDepartment")},
                new Object[] {ConfigUtils.getTestProperty("SFName"), ConfigUtils.getTestProperty("SSName"), ConfigUtils.getTestProperty("SEmail"), ConfigUtils.getTestProperty("SAge"), ConfigUtils.getTestProperty("SSalary"), ConfigUtils.getTestProperty("SDepartment")},
        };
    }
}
