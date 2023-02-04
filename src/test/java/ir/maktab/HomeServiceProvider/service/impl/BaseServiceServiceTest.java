package ir.maktab.HomeServiceProvider.service.impl;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.repository.BaseServiceRepository;
import ir.maktab.HomeServiceProvider.exception.ValidationException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BaseServiceServiceTest {
    @Autowired
    private BaseServiceServiceImpl baseServiceService;
    @Autowired
    private BaseServiceRepository baseServiceRepository;

    @Test
    @Order(1)
    void testSaveBaseService() {
        BaseService baseService = getBaseService();
        BaseService saveBaseService = baseServiceService.add(baseService);
        assertNotNull(saveBaseService);
    }

    @Test
    @Order(2)
    void testSaveBaseServiceWhenServiceIsExist() {
        BaseService baseService = getBaseService();
        assertThrows(ValidationException.class, () -> baseServiceService.add(baseService));
    }

    @Test
    void testSoftDeleteBaseService() {
        BaseService baseService = getBaseService2();
        baseServiceService.remove(baseService);
        BaseService saveBaseService = baseServiceService.add(baseService);
        Optional<BaseService> optionalBaseService = baseServiceRepository.findById(saveBaseService.getId());
        assertThat(optionalBaseService.get().isDeleted()).isEqualTo(true);
    }

    @Test
    @Order(3)
    void testUpdateBaseService() {
        BaseService baseService = baseServiceService.findById(1L);

        baseService.setName("baseService7");
        BaseService updateBaseService = baseServiceService.update(baseService);

        assertThat(updateBaseService.getName()).isEqualTo("baseService7");
    }

    @Test
    void testSelectAllAvailableBaseService() {
        List<BaseService> baseServiceList = baseServiceService.selectAllAvailableService();

        assertThat(baseServiceList).isNotNull();
        assertThat(baseServiceList.size()).isEqualTo(1);
    }

    protected BaseService getBaseService() {
        return new BaseService("service4");
    }

    protected BaseService getBaseService2() {
        return new BaseService("service5");
    }
}