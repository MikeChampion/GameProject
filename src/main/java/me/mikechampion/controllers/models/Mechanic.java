package me.mikechampion.controllers.models;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mechanic {

    @Id
    @GeneratedValue
    private int Id;

    @NotNull
    @Size(min=3, max=20)
    private String name;

    @ManyToMany(mappedBy = "mechanics")
    private List<Game> games = new ArrayList<>();

    public Mechanic() { }

    public Mechanic(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() { return games; }

    public void setGames(List<Game> games) { this.games = games; }

}
