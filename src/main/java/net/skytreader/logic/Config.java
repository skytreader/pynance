package net.skytreader.logic;

import net.skytreader.exceptions.ConfigConstraintException;
import net.skytreader.exceptions.ConfigValueException;
import net.skytreader.model.InstallationConfig;
import net.skytreader.repository.InstallationConfigRepository;

import java.util.*;

/**
 * This class encapsulates the config keys we expect to be in the database as
 * well as their validation.
 */
public class Config {

    private static final String KEY_NET_MONTHLY = "net-monthly";
    private static final String KEY_LIVING_COST_PERCENT = "living-costs" +
            "-percent";
    private static final String KEY_ALLOWANCE_PERCENT = "allowance-percent";
    private static final String KEY_PROJECTED_LIMIT_UTILITIES = "projected" +
            "-limit-utilities";
    private static final String KEY_PROJECTED_LIMIT_FOOD = "projected-limit" +
            "-food";

    private Map<String, String> cfg;

    public Config(Map<String, String> cfg) {
        this.cfg = cfg;
    }

    public Config(InstallationConfigRepository icr) {
        this.cfg = new HashMap<String, String>();
        Collection<String> configKeys = Arrays.asList(Config.KEY_NET_MONTHLY,
                Config.KEY_LIVING_COST_PERCENT, Config.KEY_ALLOWANCE_PERCENT,
                Config.KEY_PROJECTED_LIMIT_UTILITIES,
                Config.KEY_PROJECTED_LIMIT_FOOD);
        for (InstallationConfig configRecord : icr.fetchConfig(configKeys)){
            this.cfg.put(configRecord.getKey(), configRecord.getValue());
        }
    }

    public float fetchNetMonthly() {
        return Float.parseFloat(cfg.get(Config.KEY_NET_MONTHLY));
    }

    public float fetchLivingCostAllocation() throws ConfigValueException, ConfigConstraintException {
        int rawVal = Integer.parseInt(cfg.get(Config.KEY_LIVING_COST_PERCENT));

        if (rawVal > 100) {
            throw new ConfigValueException(Config.KEY_LIVING_COST_PERCENT,
                    "" + rawVal);
        }

        int rawTandemVal =
                Integer.parseInt(cfg.get(Config.KEY_ALLOWANCE_PERCENT));

        if ((rawVal + rawTandemVal) > 100) {
            throw new ConfigConstraintException(Config.KEY_LIVING_COST_PERCENT + "  and " + Config.KEY_ALLOWANCE_PERCENT + " together should not exceed 100.");
        }

        return rawVal / 100f;
    }

    public float fetchAllowanceAllocation() throws ConfigValueException,
            ConfigConstraintException {
        int rawVal = Integer.parseInt(cfg.get(Config.KEY_ALLOWANCE_PERCENT));

        if (rawVal > 100) {
            throw new ConfigValueException(Config.KEY_ALLOWANCE_PERCENT,
                    "" + rawVal);
        }

        int rawTandemVal =
                Integer.parseInt(cfg.get(Config.KEY_LIVING_COST_PERCENT));

        if ((rawVal + rawTandemVal) > 100) {
            throw new ConfigConstraintException(Config.KEY_LIVING_COST_PERCENT + "  and " + Config.KEY_ALLOWANCE_PERCENT + " together should not exceed 100.");
        }

        return rawVal / 100f;
    }

    public int fetchUtilitiesLimitProjection() {
        return Integer.parseInt(cfg.get(Config.KEY_PROJECTED_LIMIT_UTILITIES));
    }

    public int fetchFoodLimitProjection() {
        return Integer.parseInt(cfg.get(Config.KEY_PROJECTED_LIMIT_FOOD));
    }
}