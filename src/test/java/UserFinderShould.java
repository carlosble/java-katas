import org.junit.Before;
import org.junit.Test;
import tdd.User;
import tdd.UserFinder;
import tdd.UserRepository;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserFinderShould {

    class UserRepositoryStub implements UserRepository {
        private List<User> stubUsers;
        public void configure(User... stubUsers){
            this.stubUsers = Arrays.asList(stubUsers);
        }
        @Override
        public List<User> findAll() {
            return stubUsers;
        }
    }

    private UserFinder finder;
    private UserRepositoryStub repo;

    @Before
    public void setup() {
        repo = new UserRepositoryStub();
        finder = new UserFinder(repo);
    }

    @Test
    public void get_users_from_repository() throws Exception {
        repo.configure(aUser());
        String aProfile = aUser().getProfile();

        List<User> result = finder.findByProfile(aProfile);

        assertThat(result).hasSize(1);
        assertThat(result)
                .extracting("profile")
                .contains(aProfile);
    }

    @Test
    public void filter_users_by_profile() throws Exception {
        repo.configure(aUser());

        List<User> result = finder.findByProfile(aRandomProfile());

        assertThat(result).hasSize(0);
    }

    @Test
    public void filter_users_by_profile_using_mockito() throws Exception {
        UserRepository mockitoRepo = mock(UserRepository.class);
        when(mockitoRepo.findAll()).thenReturn(
                Arrays.asList(aUser()));
        repo.configure(aUser());

        List<User> result = finder.findByProfile(aRandomProfile());

        assertThat(result).hasSize(0);
    }

    private String aRandomProfile() {
        return "asdfasdlkajsdfkljasdff";
    }

    private User aUser() {
        return new User(0, "irrelevant", "irrelevant");
    }
}
