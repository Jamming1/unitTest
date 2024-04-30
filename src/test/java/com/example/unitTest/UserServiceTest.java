package com.example.unitTest;
import com.example.unitTest.features.entities.User;
import com.example.unitTest.features.repository.UserRepository;
import com.example.unitTest.features.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Test
    void checkUserActivating() throws Exception {
        User personalUser = new User();
        personalUser.setId(personalUser.getId());
        personalUser.setActive(true);
        personalUser.setName("Jamming");
        personalUser.setSurname("Parrillo");
        personalUser.setAge(32);

        User userFromDB = userRepository.save(personalUser);
        assertThat(userFromDB).isNotNull();
        assertThat(userFromDB.getId()).isNotNull();

        User userFromService = userService.setUserActivationStatus(personalUser.getId(), true);
        assertThat(userFromService).isNotNull();
        assertThat(userFromService.getId()).isNotNull();
        assertThat(userFromService.isActive()).isTrue();

        User userFromFind = userRepository.findById(userFromDB.getId()).get();
        assertThat(userFromFind).isNotNull();
        assertThat(userFromFind.getId()).isNotNull();
        assertThat(userFromFind.getId()).isEqualTo(userFromDB.getId());
        assertThat(userFromService.isActive()).isTrue();
    }
}