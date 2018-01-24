package me.mikechampion.controllers.models.forms;

import me.mikechampion.controllers.models.Game;
import me.mikechampion.controllers.models.Mechanic;

import javax.validation.constraints.NotNull;

public class AddGameMechForm {

    @NotNull
    private int gameId;

    @NotNull
    private int mechanicId;

    private Iterable<Mechanic> mechanics;

    private Game game;

    public AddGameMechForm() {}

    public AddGameMechForm(Iterable<Mechanic> mechanics, Game game) {
        this.mechanics = mechanics;
        this.game = game;
    }

    public int getGameId() { return gameId; }

    public void setGameId(int gameId) { this.gameId = gameId; }

    public int getMechanicId() { return mechanicId; }

    public void setMechanicId(int mechanicId) { this.mechanicId = mechanicId; }

    public void setMechanics(Iterable<Mechanic> mechanics) {
        this.mechanics = mechanics; }

    public Iterable<Mechanic> getMechanics() { return mechanics; }

    public Game getGame() { return game; }
}
