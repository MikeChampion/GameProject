package me.mikechampion.controllers.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class PlayerCount {

    @Id
    @GeneratedValue
    private int Id;

    @NotNull
    private int minPlayer;

    @NotNull
    private int maxPlayer;

    public PlayerCount() { }


}
