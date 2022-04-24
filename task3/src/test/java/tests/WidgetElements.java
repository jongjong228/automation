package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProgressBarForm;
import pages.SlidersForm;
import pages.WidgetsForm;
import utils.ConfigUtils;
import utils.DriverUtils;
import utils.MyLogger;
import utils.RandomUtil;

public class WidgetElements extends BaseTest {
    @Test
    public void sliderCase(){
        MyLogger.info("First step");
        DriverUtils.openURL(ConfigUtils.getConfProperty("HomePageURL"));
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isPageOpened()," Home Page is not opened");
        MyLogger.info("Second step");
        homePage.clickWidgetsButton();
        WidgetsForm widgetsForm = new WidgetsForm();
        Assert.assertTrue(widgetsForm.isPageOpened()," Widgets Page is not opened");
        widgetsForm.clickSliderButton();
        SlidersForm slidersForm = new SlidersForm();
        Assert.assertTrue(slidersForm.isPageOpened()," Sliders Form is not opened");
        MyLogger.info("Third step");
        int randomSliderValue = RandomUtil.getRandomSliderValue();
        slidersForm.moveSlider(randomSliderValue);
        Assert.assertEquals(slidersForm.getSliderValue(),randomSliderValue,"Slider Value is not equals");
        MyLogger.info("Fourth step");
        slidersForm.clickProgressBarButton();
        ProgressBarForm progressBarForm = new ProgressBarForm();
        Assert.assertTrue(progressBarForm.isPageOpened()," Progress Bar Form is not opened");
        MyLogger.info("Fifth step");
        progressBarForm.clickStartButton();
        MyLogger.info("Sixth step");
        progressBarForm.waitProgress();
        progressBarForm.clickStopButton();
        int currentProgress = progressBarForm.getCurrentProgress();
        Assert.assertTrue((Math.abs(currentProgress-Integer.parseInt(ConfigUtils.getTestProperty("EngineerAge")))<=2),"Progress Bar value is not equals");
    }
}
