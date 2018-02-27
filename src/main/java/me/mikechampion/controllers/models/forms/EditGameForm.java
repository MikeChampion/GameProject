package me.mikechampion.controllers.models.forms;

import me.mikechampion.controllers.models.Game;
import me.mikechampion.controllers.models.Mechanic;
import me.mikechampion.controllers.models.Player;

import javax.validation.constraints.NotNull;

public class EditGameForm {

    @NotNull
    private int gameId;

    @NotNull
    private int mechanicId;

    private Iterable<Mechanic> mechanics;

    private Iterable<Player> owners;

    private Game game;

    public EditGameForm() {}

    public EditGameForm(Iterable<Mechanic> mechanics, Iterable<Player> owners, Game game) {
        this.mechanics = mechanics;
        this.owners = owners;
        this.game = game;
    }

    public int getGameId() { return gameId; }

    public void setGameId(int gameId) { this.gameId = gameId; }

    public Iterable<Mechanic> getMechanic() { return mechanics; }

    public int getMechanicId() { return mechanicId; }

    public void setMechanicId(int mechanicId) { this.mechanicId = mechanicId; }

    public Iterable<Mechanic> getMechanics() { return mechanics; }

    public Game getGame() { return game; }

    public Iterable<Player> getOwners() { return owners; }

    public void setOwners(Iterable<Player> owners) { this.owners = owners; }
}
