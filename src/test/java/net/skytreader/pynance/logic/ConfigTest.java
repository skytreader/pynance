package net.skytreader.pynance.logic;

import static org.junit.jupiter.api.Assertions.*;

import net.skytreader.pynance.exceptions.ConfigConstraintException;
import net.skytreader.pynance.exceptions.ConfigValueException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Random;

public class ConfigTest {

    @Test
    void fetchMonthlyHappy() throws ConfigValueException {
        for (int i = 0; i < 100; i++) {
            Random r = new Random();
            String integerPart = Integer.toString(r.nextInt(Integer.MAX_VALUE));
            int _frac = r.nextInt(100);
            String fractionalPart = (_frac < 10 ? "0" : "") + _frac;
            String testVal = integerPart + "." + fractionalPart;
            HashMap<String, String> _cfg = new HashMap<String, String>();
            _cfg.put(Config.KEY_NET_MONTHLY, testVal);
            Config cfg = new Config(_cfg);
            assertEquals(testVal, cfg.fetchNetMonthly().toString());
        }
    }

    @Test
    void fetchMonthlyNonNegative() {
        assertThrows(ConfigConstraintException.class, () -> {
            HashMap<String, String> _cfg = new HashMap<String, String>();
            _cfg.put(Config.KEY_NET_MONTHLY, "-1");
            Config cfg = new Config(_cfg);
            cfg.fetchNetMonthly();
        });
    }

    @Test
    void fetchMonthlyNullity() throws ConfigValueException {
        Config cfg = new Config(new HashMap<String, String>());
        assertNull(cfg.fetchNetMonthly());
    }

    @Test
    void fetchLivingCostAllocationHappy() throws ConfigConstraintException {
        for (int i = 0; i < 100; i++) {
            Random r = new Random();
            int livingCostVal = r.nextInt(100);
            int allowanceVal = 100 - livingCostVal;
            boolean ltTen = livingCostVal < 10;
            String livingCostFrac = "0." + (ltTen ?
                    "0" : "") + livingCostVal;
            HashMap<String, String> _cfg = new HashMap<String, String>();
            _cfg.put(Config.KEY_LIVING_COST_PERCENT, "" + livingCostVal);
            _cfg.put(Config.KEY_ALLOWANCE_PERCENT, "" + allowanceVal);
            Config cfg = new Config(_cfg);
            assertEquals(0,
                    cfg.fetchLivingCostAllocation().compareTo(new BigDecimal(livingCostFrac)));
        }
    }
}
