package tdd;

import java.util.ArrayList;
import java.util.List;

public class UserFinder {
    private UserRepository repository;

    public UserFinder(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findByProfile(String profile){
        List<User> users = repository.findAll();
        User user = users.get(0);
        if (user.isMatchingProfile(profile)){
            return users;
        }
        return new ArrayList<>();
    }

}
