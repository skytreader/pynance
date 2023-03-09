package net.skytreader.service;

import net.skytreader.logic.Config;
import net.skytreader.repository.InstallationConfigRepository;

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
