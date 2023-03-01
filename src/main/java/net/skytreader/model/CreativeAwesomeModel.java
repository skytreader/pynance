package net.skytreader.model;

import java.time.ZonedDateTime;
import javax.persistence.*;

@MappedSuperclass
public abstract class CreativeAwesomeModel {

    @Column(name="created_at", nullable = false, columnDefinition =
            "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdAt;

    @Column(name="modified_at", nullable = false, columnDefinition =
            "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime modifiedAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public CreativeAwesomeModel() {
        this.createdAt = ZonedDateTime.now();
        this.modifiedAt = ZonedDateTime.now();
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(ZonedDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
