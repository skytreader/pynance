package net.skytreader.pynance.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

public class ConfigTest {

    @Test
    void fetchMonthlyHappy() {
        for (int i = 0; i < 100; i++) {
            Random r = new Random();
            String integerPart = Integer.toString(r.nextInt());
            int _frac = r.nextInt(100);
            String fractionalPart = (_frac < 10 ? "0" : "") + _frac;
            String testVal = integerPart + "." + fractionalPart;
            HashMap<String, String> _cfg = new HashMap<String, String>();
            _cfg.put(Config.KEY_NET_MONTHLY, testVal);
            Config cfg = new Config(_cfg);
            assertEquals(testVal, cfg.fetchNetMonthly().toString());
        }
    }
}
