package net.skytreader.pynance.service;

import net.skytreader.pynance.logic.Config;
import net.skytreader.pynance.repository.InstallationConfigRepository;

public class StartUpService {
    private final InstallationConfigRepository configRepository;

    public StartUpService(InstallationConfigRepository configRepository){
        this.configRepository = configRepository;
    }

    // TODO
    public boolean isInstallationComplete() {
        Config c = new Config(this.configRepository);
        return false;
    }
}
