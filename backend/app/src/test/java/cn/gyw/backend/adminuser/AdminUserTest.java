package cn.gyw.backend.adminuser;

import cn.gyw.backend.system.domain.admin.creator.AdminUserCreator;
import cn.gyw.backend.system.domain.admin.service.IAdminUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 管理员
 *
 * @date 2023/5/25
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdminUserTest {

    @Resource
    private IAdminUserService adminUserService;

    @Test
    public void createAdminUser() {
        AdminUserCreator creator = new AdminUserCreator();
        creator.setUsername("12345678");
        creator.setPassword("123456");
        creator.setPhone("111111111");
        creator.setDepartmentId(1L);
        adminUserService.createAdminUser(creator);
    }
}
