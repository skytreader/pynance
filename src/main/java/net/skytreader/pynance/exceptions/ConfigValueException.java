package net.skytreader.pynance.exceptions;

public class ConfigValueException extends ConfigConstraintException {
    public ConfigValueException(String key, String val){
        super("Invalid value for key '" + key + "': " + val);
    }
}
