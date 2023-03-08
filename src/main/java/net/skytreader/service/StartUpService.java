package net.skytreader.service;

import net.skytreader.repository.InstallationConfigRepository;

import java.util.Map;

public class StartUpService {
    private final InstallationConfigRepository configRepository;

    public StartUpService(InstallationConfigRepository configRepository){
        this.configRepository = configRepository;
    }

    // TODO
    public boolean isInstallationComplete() {
        return false;
    }
}
