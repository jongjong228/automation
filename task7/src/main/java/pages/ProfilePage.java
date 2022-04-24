package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import keys.JsonKeys;
import org.openqa.selenium.By;
import utils.JsonUtils;
import utils.LoggerUtils;

import java.time.Duration;

public class ProfilePage extends Form {
    private final int durationOfSeconds = JsonUtils.getInt(JsonKeys.SECS, JsonUtils.PROPERTIES);
    private final int durationOfMills = JsonUtils.getInt(JsonKeys.MILLS, JsonUtils.PROPERTIES);
    private final By postAuthorPath = By.xpath("//h5/a[@class='author']");
    private final By postTextPath = By.xpath("//*[contains(@class,'wall_post_text')]");
    private final By commentsPath = By.xpath("//*[contains(@class,'replieable _post')]");
    private final By commentAuthorPath = By.xpath("//*[@class='reply_author']/a");
    private final By commentTextPath = By.className("wall_reply_text");
    private final By likeButtonPath = By.xpath("//*[contains(@class,'_like_button_icon')]");
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final IComboBox post = elementFactory.getComboBox(By.xpath("//*[contains(@class,'_post post')]"), "current post");
    private final IButton likeButton = elementFactory.findChildElement(post, likeButtonPath, ElementType.BUTTON);
    private final ILink postAuthor = elementFactory.findChildElement(post, postAuthorPath, ElementType.LINK);
    private final ILabel postText = elementFactory.findChildElement(post, postTextPath, ElementType.LABEL);
    private final ILink commentAuthor = elementFactory.getLink(commentAuthorPath, "comment author link");
    private final ILabel commentText = elementFactory.getLabel(commentTextPath, "comment text label");
    private final IComboBox comment = elementFactory.getComboBox(commentsPath, "comment combo box");
    private final IComboBox nextComment = elementFactory.getComboBox(By.xpath("//*[contains(@class,'js-replies_next')]"), "Next comment link");
    private final IComboBox photo = elementFactory.getComboBox(By.xpath("//*[contains(@class,'page_post')]/a"), "Photo");

    public ProfilePage() {
        super(By.className("profile_rate_warning"), "Profile Page");
    }

    public void clickNextComment() {
        nextComment.click();
    }

    public void clickLikeButton() {
        likeButton.click();
    }

    public String getPostAuthor() {
        return postAuthor.getText();
    }

    public String getPostText() {
        return postText.getText();
    }

    public String getCommentAuthor() {
        return commentAuthor.getText();
    }

    public String getCommentText() {
        return commentText.getText();
    }

    private boolean isCommentsRefresh() {
        return comment.state().isDisplayed();
    }

    public boolean isNewCommentExist() {
        try {
            AqualityServices.getConditionalWait().waitForTrue(this::isCommentsRefresh,
                    Duration.ofSeconds(durationOfSeconds), Duration.ofMillis(durationOfMills), "NEW POST IS NOT FOUND");
        } catch (java.util.concurrent.TimeoutException e) {
            LoggerUtils.getLogger().warn("Time out exception");
            return false;
        }
        return true;
    }

    public boolean isPostExist() {
        return post.state().isExist();
    }

    public String getPhotoId() {
        return photo.getAttribute("href");
    }
}
