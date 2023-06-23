package net.skytreader.pynance.service;

import net.skytreader.pynance.exceptions.ConfigConstraintException;
import net.skytreader.pynance.logic.Config;
import net.skytreader.pynance.repository.InstallationConfigRepository;
import org.springframework.stereotype.Service;

@Service
public class StartUpService {
    private final InstallationConfigRepository configRepository;

    public StartUpService(InstallationConfigRepository configRepository){
        this.configRepository = configRepository;
    }

    public boolean isInstallationComplete() {
        Config c = new Config(this.configRepository);
        try {
            return c.fetchAllowanceAllocation() != null &&
                    c.fetchNetMonthly() != null &&
                    c.fetchFoodLimitProjection() != null &&
                    c.fetchLivingCostAllocation() != null &&
                    c.fetchUtilitiesLimitProjection() != null;
        } catch (ConfigConstraintException cce) {
            return false;
        }
    }
}
