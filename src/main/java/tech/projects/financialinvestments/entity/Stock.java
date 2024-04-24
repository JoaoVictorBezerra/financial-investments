package tech.projects.financialinvestments.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String description;

    public Stock() {
    }

    public Stock(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
