package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import keys.JsonKeys;
import utils.JsonUtils;
import utils.LoggerUtils;

import java.time.Duration;
import java.util.List;

public class ProfilePage extends Form {
    private static final By addPhotoLinkPath = By.xpath("//div[@class='profile_rate_warning']");
    private final By postAuthorPath = By.xpath("//h5/a[@class='author']");
    private final By postTextPath = By.xpath("//div[contains(@class,'wall_post_text')]");
    private final By postPhotoPath = By.xpath("//div[contains(@class,'page_post')]/a");
    private final By commentsPath = By.xpath("//div[contains(@class,'replieable _post')]");
    private final By commentAuthorPath = By.xpath("//div[@class='reply_author']/a");
    private final By commentTextPath = By.xpath("//div[@class='wall_reply_text']");
    private final By likeButtonPath = By.xpath("//span[contains(@class,'_like_button_icon')]");
    private IComboBox post;
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final List<IComboBox> posts = elementFactory.findElements(By.xpath("//div[contains(@class,'_post post')]"), ElementType.COMBOBOX);
    private final IComboBox nextComment = elementFactory.getComboBox(By.xpath("//span[contains(@class,'js-replies_next')]"), "Next comment link");
    private final ILink photo = elementFactory.getLink(postPhotoPath, "Photo");
    private int numOfPosts;

    public ProfilePage() {
        super(addPhotoLinkPath, "Profile Page");
    }

    public void clickNextComment() {
        nextComment.click();
    }

    public void saveNumOfPosts() {
        LoggerUtils.getLogger().info("save num of posts");
        numOfPosts = posts.size();
    }

    private IButton getLikeButton() {
        return post.findChildElement(likeButtonPath, ElementType.BUTTON);
    }

    public void clickLikeButton() {
        getLikeButton().click();
    }

    private boolean isPostsRefreshed() {
        return posts.size() > numOfPosts;
    }

    private void setPost() {
        try {
            AqualityServices.getConditionalWait().waitForTrue(this::isPostsRefreshed,
                    Duration.ofSeconds(JsonUtils.getInt(JsonKeys.TIME_SECS)), Duration.ofMillis(JsonUtils.getInt(JsonKeys.TIME_MILLS)), "NEW POST IS NOT FOUND");
        } catch (java.util.concurrent.TimeoutException e) {
            LoggerUtils.getLogger().warn("Time out exception");
        }
        post = posts.get(0);
    }

    private ILink getPostAuthorLink() {
        return post.findChildElement(postAuthorPath, ElementType.LINK);
    }

    public String getPostAuthor() {
        setPost();
        return getPostAuthorLink().getText();
    }

    private ILabel getPostTextLabel() {
        return post.findChildElement(postTextPath, ElementType.LABEL);
    }

    public String getPostText() {
        return getPostTextLabel().getText();
    }

    private IElement getCommentAuthorLink() {
        return elementFactory.findElements(commentAuthorPath, ElementType.LINK).get(0);
    }

    public String getCommentAuthor() {
        return getCommentAuthorLink().getText();
    }

    private IElement getCommentTextLabel() {
        return elementFactory.findElements(commentTextPath, ElementType.LABEL).get(0);
    }

    public String getCommentText() {
        return getCommentTextLabel().getText();
    }

    private IComboBox getCommentComboBox() {
        return post.findChildElement(commentsPath, ElementType.COMBOBOX);
    }

    public boolean isCommentsRefresh() {
        return getCommentComboBox().state().isDisplayed();
    }

    public boolean isNewCommentExist() {
        try {
            AqualityServices.getConditionalWait().waitForTrue(this::isCommentsRefresh,
                    Duration.ofSeconds(JsonUtils.getInt(JsonKeys.TIME_SECS)), Duration.ofMillis(JsonUtils.getInt(JsonKeys.TIME_MILLS)), "NEW POST IS NOT FOUND");
        } catch (java.util.concurrent.TimeoutException e) {
            LoggerUtils.getLogger().warn("Time out exception");
            return false;
        }
        return true;
    }

    public boolean isPostDeleted() {
        return post.state().isExist();
    }

    public String getPhotoId() {
        return photo.getAttribute("data-photo-id");
    }
}
