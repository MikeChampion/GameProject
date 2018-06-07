package me.mikechampion.GameProject.controllers.models.forms;

import me.mikechampion.GameProject.controllers.models.Game;
import me.mikechampion.GameProject.controllers.models.Mechanic;
import me.mikechampion.GameProject.controllers.models.Player;

import javax.validation.constraints.NotNull;

public class EditGameForm {

    @NotNull
    private int gameId;

    @NotNull
    private int mechanicId;

    public Iterable<Mechanic> mechanics;

    public Iterable<Player> owners;

    public Game game;

    public EditGameForm() {}

    public EditGameForm(Iterable<Mechanic> mechanics, Iterable<Player> owners, Game game) {
        this.mechanics = mechanics;
        this.owners = owners;
        this.game = game;
    }

    public int getGameId() { return gameId; }

    public void setGameId(int gameId) { this.gameId = gameId; }

    public Iterable<Mechanic> getMechanic() { return mechanics; }

    //public int[] getMechanicIds() { return mechanicIds; }

    public void setMechanicId(int mechanicId) { this.mechanicId = mechanicId; }

    public Game getGame() { return game; }

    public Iterable<Player> getOwners() { return owners; }

    public void setOwners(Iterable<Player> owners) { this.owners = owners; }
}
