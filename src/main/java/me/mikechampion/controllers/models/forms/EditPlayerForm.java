package me.mikechampion.controllers.models.forms;

import me.mikechampion.controllers.models.Player;
import me.mikechampion.controllers.models.Mechanic;

import javax.validation.constraints.NotNull;

public class EditPlayerForm {

    @NotNull
    private int playerId;

    @NotNull
    private int mechanicId;

    public Iterable<Mechanic> mechanics;

    private Player player;

    public EditPlayerForm() {}

    public EditPlayerForm(Iterable<Mechanic> mechanics, Player player) {
        this.mechanics = mechanics;
        this.player = player;
    }

    public int getPlayerId() { return playerId; }

    public void setPlayerId(int playerId) { this.playerId = playerId; }

    public Iterable<Mechanic> getMechanic() { return mechanics; }

    public int getMechanicId() { return mechanicId; }

    public void setMechanicId(int mechanicId) { this.mechanicId = mechanicId; }

    public Iterable<Mechanic> getMechanics() { return mechanics; }

    public Player getPlayer() { return player; }
}