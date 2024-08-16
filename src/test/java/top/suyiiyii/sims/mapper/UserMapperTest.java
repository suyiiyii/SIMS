package top.suyiiyii.sims.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import top.suyiiyii.sims.entity.User;

@SpringBootTest
@ActiveProfiles("test")
public class UserMapperTest {

    @Autowired
    private MpUserMapper userMapper;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setStudentId(1);
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test");
        user.setGrade("test");
        user.setUserGroup("test");



        int result = userMapper.insert(user);
        Assertions.assertEquals(1, result);
    }

}