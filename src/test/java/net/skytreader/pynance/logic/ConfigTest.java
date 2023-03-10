package net.skytreader.pynance.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ConfigTest {

    @Test
    void fetchMonthlyHappy(){
        HashMap<String, String> _cfg = new HashMap<String, String>();
        /*
        As it turns out, the fractional value .42 is great here because it
        does not parse exactly in floating point.
         */
        _cfg.put(Config.KEY_NET_MONTHLY, "100.42");
        Config cfg = new Config(_cfg);
        assertEquals(100.42f, cfg.fetchNetMonthly());
    }
}
