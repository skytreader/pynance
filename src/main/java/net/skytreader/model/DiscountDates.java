package net.skytreader.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="discount_dates")
public class DiscountDates extends CreativeAwesomeModel{

    public enum Durations {
        Day, Week, Month
    }

    @Column(nullable = false)
    private LocalDate dateObserved;
    @Column(nullable = false)
    private String shop;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Durations duration;

    public DiscountDates(LocalDate dateObserved, String shop, String description, Durations duration) {
        super();
        this.dateObserved = dateObserved;
        this.shop = shop;
        this.description = description;
        this.duration = duration;
    }

    public LocalDate getDateObserved() {
        return dateObserved;
    }

    public void setDateObserved(LocalDate dateObserved) {
        this.dateObserved = dateObserved;
    }
}
