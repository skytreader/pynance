package net.skytreader.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="expenses")
public class Expenses extends CreativeAwesomeModel {

    public enum Categories {
        Utilities, Food, Allowance
    }

    @Column(nullable = false)
    private LocalDate expenseDate;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, precision=2)
    private float cost;
    @Column
    private String comments;
    @Enumerated(EnumType.STRING)
    private Categories category;

    public Expenses(LocalDate expenseDate, String name, float cost, String comments, Categories category) {
        super();
        this.expenseDate = expenseDate;
        this.name = name;
        this.cost = cost;
        this.comments = comments;
        this.category = category;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
