package utils;

import pojo.Post;
import pojo.User;

import java.util.List;

public class Utils {
    public static boolean isSorted(List<Post> posts) {
        LoggerUtils.getLogger().info("is List sorted");
        for (int i = 0; i < posts.size() - 1; i++) {
            if (posts.get(i + 1).getId() - posts.get(i).getId() != 1)
                return false;
        }
        return true;
    }

    public static User findUserById(List<User> users, int id) {
        LoggerUtils.getLogger().info("find user by id");
        for (int i = 0; i < users.size() - 1; i++) {
            if (users.get(i).getId() == id)
                return users.get(i);
        }
        LoggerUtils.getLogger().warn("user with id:" + (id + 1) + "is not find");
        return null;
    }
}
