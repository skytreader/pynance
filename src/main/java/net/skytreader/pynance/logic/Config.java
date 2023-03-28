package net.skytreader.pynance.logic;

import net.skytreader.pynance.exceptions.ConfigConstraintException;
import net.skytreader.pynance.exceptions.ConfigValueException;
import net.skytreader.pynance.model.InstallationConfig;
import net.skytreader.pynance.repository.InstallationConfigRepository;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

/**
 * This class encapsulates the config keys we expect to be in the database as
 * well as their validation. The methods will return null if the underlying
 * data persistence source (e.g. the database) does not have a value for the
 * key.
 */
public class Config {

    protected static final String KEY_NET_MONTHLY = "net-monthly";
    protected static final String KEY_LIVING_COST_PERCENT = "living-costs" +
            "-percent";
    protected static final String KEY_ALLOWANCE_PERCENT = "allowance-percent";
    protected static final String KEY_PROJECTED_LIMIT_UTILITIES = "projected" +
            "-limit-utilities";
    protected static final String KEY_PROJECTED_LIMIT_FOOD = "projected-limit" +
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
        for (InstallationConfig configRecord : icr.fetchConfig(configKeys)) {
            this.cfg.put(configRecord.getKey(), configRecord.getValue());
        }
    }

    public BigDecimal fetchNetMonthly() throws ConfigValueException {
        String configValue = cfg.get(Config.KEY_NET_MONTHLY);
        if (configValue == null) {
            return null;
        }
        BigDecimal fromCfg = new BigDecimal(configValue);
        if (fromCfg.signum() < 0) {
            throw new ConfigValueException(Config.KEY_NET_MONTHLY,
                    fromCfg.toString());
        }
        return fromCfg;
    }

    /**
     * Living cost and Allowance together may not exceed 100 (percent). But
     * it does not have to be exactly 100 because the setting allows for
     * "savings" which is not explicitly represented here.
     *
     * @return
     * @throws ConfigConstraintException
     */
    public BigDecimal fetchLivingCostAllocation() throws ConfigConstraintException {
        String configValue = cfg.get(Config.KEY_LIVING_COST_PERCENT);
        if (configValue == null) {
            return null;
        }
        int rawVal = Integer.parseInt(configValue);

        if (rawVal > 100) {
            throw new ConfigValueException(Config.KEY_LIVING_COST_PERCENT,
                    "" + rawVal);
        }

        String tandemVal = cfg.get(Config.KEY_ALLOWANCE_PERCENT);
        if (tandemVal == null) {
            throw new ConfigConstraintException(Config.KEY_LIVING_COST_PERCENT + " is defined but not " + Config.KEY_ALLOWANCE_PERCENT);
        }
        int rawTandemVal = Integer.parseInt(tandemVal);

        if ((rawVal + rawTandemVal) > 100) {
            throw new ConfigConstraintException(Config.KEY_LIVING_COST_PERCENT + "  and " + Config.KEY_ALLOWANCE_PERCENT + " together should not exceed 100.");
        }

        return new BigDecimal(rawVal / 100.0, new MathContext(2));
    }

    public BigDecimal fetchAllowanceAllocation() throws ConfigConstraintException {
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

        return new BigDecimal(rawVal / 100.0, new MathContext(2));
    }

    public int fetchUtilitiesLimitProjection() {
        return Integer.parseInt(cfg.get(Config.KEY_PROJECTED_LIMIT_UTILITIES));
    }

    public int fetchFoodLimitProjection() {
        return Integer.parseInt(cfg.get(Config.KEY_PROJECTED_LIMIT_FOOD));
    }
}