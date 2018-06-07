package me.mikechampion.GameProject.controllers.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=25)
    public String name;

    private String bggName;

    //linking players and mechanics
    @ManyToMany
    public List<Mechanic> mechanics = new ArrayList<>();

    //linking games and owners
    @ManyToMany(mappedBy = "owners")
    public List<Game> games = new ArrayList<>();

    public Player() { }

    public Player(String name) { this.name = name; }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Mechanic> getMechanics() { return mechanics; }

    public void setMechanics(List<Mechanic> mechanics) { this.mechanics = mechanics; }

    public void addMechItem(Mechanic item) { mechanics.add(item); }

    public void delMechItem(Mechanic item) { mechanics.remove(item); }



    public String getBggName() { return bggName; }

    public void setBggName(String bggName) { this.bggName = bggName; }

    public List<Game> getGames() { return games; }

    public void setGames(List<Game> games) { this.games = games; }

    public List mechanics() { return mechanics; }
}