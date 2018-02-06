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
    private int id;

    @NotNull
    @Size(min=3, max=20)
    private String name;

    @ManyToMany(mappedBy = "mechanics")
    private List<Game> games = new ArrayList<>();

    public Mechanic() { }

    public Mechanic(Iterable<Mechanic> all) { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Game> getGames() { return games; }

    public void setGames(List<Game> games) { this.games = games; }


}
