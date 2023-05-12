package net.skytreader.pynance.service;

import net.skytreader.pynance.Application;
import net.skytreader.pynance.logic.Config;
import net.skytreader.pynance.model.InstallationConfig;
import net.skytreader.pynance.repository.InstallationConfigRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testIsInstallationCompleteFalse() {
        StartUpService sss = new StartUpService(icr);
        assertFalse(sss.isInstallationComplete());
    }
}
