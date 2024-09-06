package top.suyiiyii.sims.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import top.suyiiyii.sims.entity.Role;
import top.suyiiyii.sims.utils.S3Client;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class RbacServiceTest {

    @Autowired
    private RbacService rbacService;

    @MockBean
    private S3Client s3Client;

    @Test
    void addRoleWithUserId() {
        int userId = 1; // mock userId
        String roleName = "ROLE"; // mock roleName
        boolean result = rbacService.addRoleWithUserId(userId, roleName);
        assertTrue(result);
    }
    @Test
    void getRolesByUserId() {
        int userId = 1; // mock userId
        List<Role> roles = rbacService.getRolesByUserId(userId);
        assertNotNull(roles);
        assert roles.stream().map(Role::getRoleName).toList().contains("ROLE"); // mock roleName
    }

}