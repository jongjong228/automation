package test.api;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Post;
import pojo.User;
import utils.*;

import java.util.List;

public class RestApiTests {


    @Test
    public void restCallsTest() {
        LoggerUtils.getLogger().info("***************STEP: make posts get***************");
        APIUtils.initGetRequest(URLUtils.createURL(URLUtils.posts));
        APIUtils.makeGetRequestCall(JsonUtils.getValue(JsonKeys.clearString));
        Assert.assertEquals(APIUtils.getStatusCode(), Integer.parseInt(JsonUtils.getValue(JsonKeys.okCode)), "Not 200 Code");
        Assert.assertEquals(APIUtils.getResponseHeader(JsonUtils.getValue(JsonKeys.jsonPath)), JsonUtils.getValue(JsonKeys.contentTypeValue), "Not json");
        List<Post> posts = APIUtils.getResponseBodyAsPostList();
        Assert.assertTrue(Utils.isSorted(posts), "sorted not ascending");
        LoggerUtils.getLogger().info("***************STEP: make 99th posts get***************");
        APIUtils.initGetRequest(URLUtils.createURL(URLUtils.post99));
        APIUtils.makeGetRequestCall(JsonUtils.getValue(JsonKeys.clearString));
        Assert.assertEquals(APIUtils.getStatusCode(), Integer.parseInt(JsonUtils.getValue(JsonKeys.okCode)), "Not 200 Code");
        Post post = (Post) APIUtils.getResponseBody(Post.class);
        Assert.assertEquals(post.getUserId(), Integer.parseInt(JsonUtils.getValue(JsonKeys.secondUserId)), "userId not equals");
        Assert.assertEquals(post.getId(), Integer.parseInt(JsonUtils.getValue(JsonKeys.secondId)), "id not equals");
        Assert.assertNotEquals(post.getBody(), JsonUtils.getValue(JsonKeys.clearString), "Body is not clear");
        Assert.assertNotEquals(post.getTitle(), JsonUtils.getValue(JsonKeys.clearString), "Title is not clear");
        LoggerUtils.getLogger().info("***************STEP: make 150 posts get(404)***************");
        APIUtils.initGetRequest(URLUtils.createURL(URLUtils.post150));
        APIUtils.makeGetRequestCall(JsonUtils.getValue(JsonKeys.clearString));
        Assert.assertEquals(Integer.parseInt(JsonUtils.getValue(JsonKeys.failCode)), APIUtils.getStatusCode(), "Not 404 Code");
        Assert.assertNull(APIUtils.getJsonString(JsonUtils.getValue(JsonKeys.bodyPath)), "Body is not null");
        LoggerUtils.getLogger().info("***************STEP: make post ***************");
        APIUtils.initGetRequest(URLUtils.createURL(URLUtils.posts));
        Post secondPost = new Post();
        secondPost.setUserId(Integer.parseInt(JsonUtils.getValue(JsonKeys.fourthCaseUserId)));
        String body = Strings.getRandomString();
        String title = Strings.getRandomString();
        secondPost.setBody(body);
        secondPost.setTitle(title);
        APIUtils.postRequest(secondPost);
        Assert.assertEquals(APIUtils.getStatusCode(), Integer.parseInt(JsonUtils.getValue(JsonKeys.createdCOde)), "Title is not clear");
        secondPost = (Post) APIUtils.getResponseBody(Post.class);
        Assert.assertEquals(secondPost.getBody(), body, "Body is not clear");
        Assert.assertEquals(secondPost.getTitle(), title, "Title is not clear");
        Assert.assertEquals(secondPost.getUserId(), Integer.parseInt(JsonUtils.getValue(JsonKeys.fourthCaseUserId)), "UserId in not equals 1");
        Assert.assertNotNull(secondPost.getId(), "Id  is clear");
        LoggerUtils.getLogger().info("***************STEP: make users get and check 5th user***************");
        APIUtils.initGetRequest(URLUtils.createURL(URLUtils.users));
        APIUtils.makeGetRequestCall(JsonUtils.getValue(JsonKeys.clearString));
        Assert.assertEquals(APIUtils.getStatusCode(), Integer.parseInt(JsonUtils.getValue(JsonKeys.okCode)), "Not 200 Code");
        Assert.assertEquals(APIUtils.getResponseHeader(JsonUtils.getValue(JsonKeys.jsonPath)), JsonUtils.getValue(JsonKeys.contentTypeValue), "Not json");
        List<User> users = APIUtils.getResponseBodyAsUserList();
        User user = Utils.findUserById(users, Integer.parseInt(JsonUtils.getValue(JsonKeys.searchUserId)));
        User exUser = JsonUtils.deserializeToPojo(User.class, JsonUtils.getUserValue(JsonKeys.fifthUser));
        Assert.assertEquals(user, exUser, "users are not equals");
        LoggerUtils.getLogger().info("***************STEP: make 5th user get***************");
        APIUtils.initGetRequest(URLUtils.createURL(URLUtils.user5));
        APIUtils.makeGetRequestCall(JsonUtils.getValue(JsonKeys.clearString));
        Assert.assertEquals(APIUtils.getStatusCode(), Integer.parseInt(JsonUtils.getValue(JsonKeys.okCode)), "Not 200 Code");
        User fifthUser = (User) APIUtils.getResponseBody(User.class);
        Assert.assertEquals(user, fifthUser, "users are not equals");
    }
}
