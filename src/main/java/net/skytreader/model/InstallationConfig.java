package net.skytreader.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="installation_config")
public class InstallationConfig extends CreativeAwesomeModel{

    @Column(nullable = false)
    private String key;
    @Column(nullable = false)
    private String value;

    public InstallationConfig(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
