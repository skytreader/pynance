package net.skytreader.pynance.service;

import net.skytreader.pynance.Application;
import net.skytreader.pynance.logic.Config;
import net.skytreader.pynance.model.InstallationConfig;
import net.skytreader.pynance.repository.InstallationConfigRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class StartUpServiceTest {

    @Autowired
    private InstallationConfigRepository icr;

    @MockBean
    private InstallationConfigRepository testIcr;

    @Test
    void testIsInstallationCompleteFalse() {
        StartUpService sss = new StartUpService(icr);
        assertFalse(sss.isInstallationComplete());
    }

    @Test
    void testIsInstallationCompleteTrue() {
        Mockito.when(testIcr.fetchConfig(any())).thenReturn(
                Arrays.asList(new InstallationConfig(Config.KEY_NET_MONTHLY,
                        "500"),
                        new InstallationConfig(Config.KEY_PROJECTED_LIMIT_FOOD, "1"),
                        new InstallationConfig(Config.KEY_PROJECTED_LIMIT_UTILITIES, "2"),
                        new InstallationConfig(Config.KEY_ALLOWANCE_PERCENT,
                                "3"),
                        new InstallationConfig(Config.KEY_LIVING_COST_PERCENT
                                , "4"))
        );
        StartUpService testSus = new StartUpService(testIcr);
        assertTrue(testSus.isInstallationComplete());
    }
}
