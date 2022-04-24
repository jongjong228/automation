package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.logging.Logger;
import keys.JsonKeys;
import keys.TestDataKeys;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.MenuForm;
import pages.ProfilePage;
import pojo.PhotoAnswer;
import pojo.Post;
import pojo.Response;
import utils.*;

public class VkApiTest {
    private final Logger logger = AqualityServices.getLogger();
    private final String authURL = JsonUtils.getString(JsonKeys.BASE_URL);
    private Browser browser;

    @BeforeMethod
    private void init() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
    }

    @Test()
    public void uiAndAPITest() {
        logger.info("Step:********* Go to Welcome page *********");
        browser.goTo(authURL);
        browser.waitForPageToLoad();
        AuthPage authPage = new AuthPage();
        Assert.assertTrue(authPage.state().isDisplayed(), "Authorization Page is not opened");
        logger.info("Step:********* AUTHORIZATION *********");
        authPage.inputEmail(JsonUtils.getUserString(TestDataKeys.EMAIL));
        authPage.inputPassword(JsonUtils.getUserString(TestDataKeys.PASSWORD));
        authPage.clickSignInButton();
        MenuForm menuForm = new MenuForm();
        Assert.assertTrue(menuForm.state().waitForDisplayed(), "Profile Page is not displayed");
        logger.info("Step:********* GO TO PROFILE PAGE *********");
        menuForm.clickProfileLInk();
        ProfilePage profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.state().waitForDisplayed(), "Profile Page is not displayed");
        logger.info("Step:********* CREATE POST *********");
        profilePage.saveNumOfPosts();
        String randomString = Randoms.getRandomString();
        APIUtils.getRequest(VkAPIUtils.createPost(randomString));
        Response response = ((Post) APIUtils.getResponse(Post.class)).getResponse();
        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Code is not OK");
        logger.info("Step:********* CHECK POST *********");
        Assert.assertEquals(profilePage.getPostAuthor(), JsonUtils.getUserString(TestDataKeys.AUTHOR_NAME));
        Assert.assertEquals(profilePage.getPostText(), randomString, "text is not equals");
        logger.info("Step:********* EDIT POST *********");
        String newRandomString = Randoms.getRandomString();
        PhotoAnswer photo = VkAPIUtils.downloadPhoto();
        APIUtils.getRequest(VkAPIUtils.editPost(newRandomString, response, photo));
        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Code is not OK");
        logger.info("Step:*********CHECK EDIT POST *********");
        Assert.assertNotEquals(profilePage.getPostText(), randomString, "text is not changed");
        Assert.assertEquals(photo.getPhotoHref(), profilePage.getPhotoId(), "Photo is not equals");
        logger.info("Step:*********CREATE POST COMMENT*********");
        randomString = Randoms.getRandomString();
        APIUtils.getRequest(VkAPIUtils.createComment(randomString, response, photo));
        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Code is not OK");
        logger.info("Step:*********CHECK POST COMMENT*********");
        profilePage.clickNextComment();
        Assert.assertTrue(profilePage.isNewCommentExist(), "New Comment doesn't exist");
        Assert.assertEquals(profilePage.getCommentText(), randomString, "text is not equal");
        Assert.assertEquals(profilePage.getCommentAuthor(), JsonUtils.getUserString(TestDataKeys.AUTHOR_NAME), "Authors are not equals");
        logger.info("Step:*********LIKE POST*********");
        profilePage.clickLikeButton();
        logger.info("Step:*********IS POST LIKED CHECKING*********");
        APIUtils.getRequest(VkAPIUtils.isLiked(response));
        Response likeResponse = ((Post) APIUtils.getResponse(Post.class)).getResponse();
        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Code is not OK");
        Assert.assertTrue(likeResponse.isLiked(), "Post is not liked");
        logger.info("Step:*********DELETE POST*********");
        APIUtils.getRequest(VkAPIUtils.deletePost(response));
        Assert.assertEquals(APIUtils.getStatusCode(), HttpStatus.SC_OK, "Code is not OK");
        logger.info("Step:*********CHEK IS POST DELETED*********");
        Assert.assertTrue(profilePage.isPostDeleted(), "Post is not deleted");
    }

    @AfterMethod
    private void quitBrowser() {
        logger.info("End of the test case");
        browser.quit();
    }
}
