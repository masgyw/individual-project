package cn.gyw.backend.template;

import cn.gyw.backend.template.domain.objecttemplate.creator.ObjectTemplateCreator;
import cn.gyw.backend.template.domain.objecttemplate.service.IObjectTemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TemplateServiceTest {

    @Autowired
    private IObjectTemplateService objectTemplateService;

    @Test
    public void testAdd() {
        ObjectTemplateCreator creator = new ObjectTemplateCreator();
        creator.setCreateUser("system");
        creator.setCategoryCode("stake");
        creator.setCategoryId(1L);
        creator.setName("充电桩模板");
        creator.setDescription("充电桩模板");
        creator.setCode("stake_template");
        objectTemplateService.createObjectTemplate(creator);
    }
}
