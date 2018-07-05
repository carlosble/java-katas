package tdd;

class Profile {
    private String description;

    public Profile(String description) {
        this.description = description;
    }
}

public class User {
    private long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return profile != null ? profile.equals(user.profile) : user.profile == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        return result;
    }

    private String name;
    private String profile;

    public User(long id, String name, String profile) {
        this.id = id;
        this.name = name;
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public String getName() {
        return name;
    }

    public boolean isMatchingProfile(String profile) {
        return getProfile().equals(profile);
    }
}
