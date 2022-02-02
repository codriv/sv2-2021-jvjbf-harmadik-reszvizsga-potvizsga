package contentsite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Podcast implements Content{

    private String title;
    private List<String> speakers;
    private List<User> clickedBy = new ArrayList<>();

    public Podcast(String title, List<String> names) {
        this.title = title;
        this.speakers = names;
    }

    public List<String> getSpeakers() {
        return new ArrayList<>(speakers);
    }

    @Override
    public boolean isPremiumContent() {
        return false;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<User> clickedBy() {
        return new ArrayList<>(clickedBy);
    }

    @Override
    public void click(User user) {
        clickedBy.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podcast podcast = (Podcast) o;
        return title.equals(podcast.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
