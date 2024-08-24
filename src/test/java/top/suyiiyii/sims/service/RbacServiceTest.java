package top.suyiiyii.sims.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.suyiiyii.sims.entity.Role;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RbacServiceTest {

    @Autowired
    private RbacService rbacService;

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