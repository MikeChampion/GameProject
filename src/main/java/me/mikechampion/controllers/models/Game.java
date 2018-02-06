package me.mikechampion.controllers.models;

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

    @ManyToMany
    private List<Mechanic> mechanics = new ArrayList<>();

    public Game() { }

    public Game(String name) { this.name = name; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Mechanic> getMechanics() { return mechanics; }

    public void setMechanics(List<Mechanic> mechanics) { this.mechanics = mechanics; }

    public void addItem(Mechanic item) { mechanics.add(item); }


}