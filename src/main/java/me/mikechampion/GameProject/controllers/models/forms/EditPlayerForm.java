package me.mikechampion.GameProject.controllers.models.forms;

import me.mikechampion.GameProject.controllers.models.Mechanic;
import me.mikechampion.GameProject.controllers.models.Player;

import javax.validation.constraints.NotNull;

public class EditPlayerForm {

    @NotNull
    private int playerId;

    @NotNull
    private int mechanicId;

    public Iterable<Mechanic> mechanics;

    private Player player;

    private String name;

    public EditPlayerForm() {}

    public EditPlayerForm(Iterable<Mechanic> pMechanics, Player player) {
        this.mechanics = pMechanics;
        this.player = player;
    }

    public void setMechanics(Iterable<Mechanic> mechanics) { this.mechanics = mechanics; }

    public void setPlayer(Player player) { this.player = player; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getPlayerId() { return playerId; }

    public void setPlayerId(int playerId) { this.playerId = playerId; }

    public Iterable<Mechanic> getMechanic() { return mechanics; }

    public int getMechanicId() { return mechanicId; }

    public void setMechanicId(int mechanicId) { this.mechanicId = mechanicId; }

    public Iterable<Mechanic> getMechanics() { return mechanics; }

    public Player getPlayer() { return player; }


}