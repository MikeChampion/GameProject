package me.mikechampion.GameProject.controllers.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=25)
    private String name;

    //linking games and mechanics
    @ManyToMany
    private List<Mechanic> mechanics = new ArrayList<>();

    //linking games and owners
    @ManyToMany
    private List<Player> owners = new ArrayList<>();

    public Game() { }

    public Game(String name) { this.name = name; }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Mechanic> getMechanics() { return mechanics; }

    public void setMechanics(List<Mechanic> mechanics) { this.mechanics = mechanics; }

    public void addMechItem(Mechanic item) { mechanics.add(item); }

    public void delMechItem(Mechanic item) { mechanics.remove(item); }

    public void addOwnerItem(Player item) { owners.add(item); }

    public void delOwnerItem(Player item) { owners.remove(item); }

    public List<Player> getOwners() { return owners; }

    public void setOwners(List<Player> owners) { this.owners = owners; }

    public List mechanics() { return mechanics; }

    public List owners() { return owners; }
}