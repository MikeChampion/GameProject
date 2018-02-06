package me.mikechampion.controllers.models.forms;

import me.mikechampion.controllers.models.Game;
import me.mikechampion.controllers.models.Mechanic;

import javax.validation.constraints.NotNull;

public class EditGameForm {

    @NotNull
    private int gameId;

    @NotNull
    private int mechanicId;

    public Iterable<Mechanic> mechanics;

    private Game game;

    public EditGameForm() {}

    public EditGameForm(Iterable<Mechanic> mechanics, Game game) {
        this.mechanics = mechanics;
        this.game = game;
    }

    public int getGameId() { return gameId; }

    public void setGameId(int gameId) { this.gameId = gameId; }

    public int getMechanicId() { return mechanicId; }

    public void setMechanicId(int mechanicId) { this.mechanicId = mechanicId; }

    public Iterable<Mechanic> getMechanics() { return mechanics; }

    public Game getGame() { return game; }
}
