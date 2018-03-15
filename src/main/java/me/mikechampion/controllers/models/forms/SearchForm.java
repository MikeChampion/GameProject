package me.mikechampion.controllers.models.forms;

import me.mikechampion.controllers.models.Player;
import me.mikechampion.controllers.models.data.PlayerDao;
import me.mikechampion.controllers.models.Mechanic;
import me.mikechampion.controllers.models.data.MechanicDao;
import me.mikechampion.controllers.models.Game;
import me.mikechampion.controllers.models.data.GameDao;
import javax.validation.constraints.NotNull;

public class SearchForm {

    @NotNull
    private int owners;

    @NotNull
    private int players;







}
