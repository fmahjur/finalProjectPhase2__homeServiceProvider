package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BaseServiceServiceImplTest {
    @Autowired
    private BaseServiceServiceImpl baseServiceService;

    @Test
    @Order(1)
    void testSaveMethod() {
        // given
        BaseService baseService = getBaseService();

        // when
        BaseService saveBaseService = baseServiceService.add(baseService);

        // then
        assertThat(saveBaseService.getName()).isEqualTo(baseService.getName());
    }

    @Test
    @Order(2)
    void testSaveMethodWhenServiceIsExist() {
        // given
        BaseService baseService = getBaseService();

        // then
        assertThrows(ValidationException.class, () -> baseServiceService.add(baseService));
    }

    @Test
    void testDeleteMethod() {
        // given
        BaseService baseService = getBaseService2();

        // when
        baseServiceService.remove(baseService);

        // then

    }

    @Test
    void testUpdateMethod() {
        // given
        BaseService baseService = getBaseService2();
        BaseService saveBaseService = baseServiceService.add(baseService);

        // when
        saveBaseService.setName("baseService3");
        BaseService updateBaseService = baseServiceService.update(baseService);

        // then
        assertThat(updateBaseService.getName()).isEqualTo("baseService3");
    }

    @Test
    void selectAll() {
    }

    protected BaseService getBaseService() {
        return new BaseService("service1");
    }

    protected BaseService getBaseService2() {
        return new BaseService("service2");
    }
}