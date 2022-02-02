package contentsite;

import java.util.HashSet;
import java.util.Set;

public class ContentService {

    private Set<User> allUsers = new HashSet<>();
    private Set<Content> contentService = new HashSet<>();

    public void registerUser(String name, String password) {
       if(!allUsers.add(new User(name, password))) {
           throw new IllegalArgumentException("Username is already taken: " + name);
       }
    }

    public Set<User> getAllUsers() {
        return allUsers;
    }

    public Set<Content> getAllContent() {
        return contentService;
    }

    public void addContent(Content content) {
        if (!contentService.add(content)) {
            throw new IllegalArgumentException("Content name is already taken: " + content.getTitle());
        }

    }

    public void logIn(String username, String password) {
        if (!allUsers.stream().anyMatch(user -> username.equals(user.getUserName()))) {
            throw new IllegalArgumentException("Username is wrong!");
        } else if (!password.equals(allUsers.stream()
                        .filter(user -> username.equals(user.getUserName()))
                        .findFirst()
                        .get().getPassword())) {
            throw new IllegalArgumentException("Password is Invalid!");
        }
        allUsers.stream().filter(user -> username.equals(user.getUserName())).findFirst().get().isLogIn();
    }

    public void clickOnContent(User user, Content content) {
        if (user.isLogIn()) {
            if (content.isPremiumContent()) {
                if (user.isPremiumMember()) {
                    content.click(user);
                }
            }
        }
    }
}
