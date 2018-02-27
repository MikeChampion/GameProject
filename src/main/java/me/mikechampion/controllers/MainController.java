package me.mikechampion.controllers;

import me.mikechampion.controllers.models.Game;
import me.mikechampion.controllers.models.Player;
import me.mikechampion.controllers.models.data.GameDao;
import me.mikechampion.controllers.models.data.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class MainController {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private PlayerDao playerDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, Game game) {
        Iterable<Player> players = playerDao.findAll();
        ArrayList<Player> owners = new ArrayList<>();
        for (Player player : players) {
            List<Game> games = player.getGames();
            int gamesSize = games.size();
            //System.out.println(games);
            if (gamesSize != 0)
                owners.add(player);
        }
        model.addAttribute("players", playerDao.findAll());
        model.addAttribute("title", "Game Collections");
        model.addAttribute("owners", owners);
        //System.out.println(owners);
        return "index";
    }

    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String results(Model model, Game game) {

        /*
        playerMech = ""
        playerIds
        players = playerIds
        for player : players
            mechs = player.getMechanics()
            for mech : mechs
                if mech not in playerMech
                playerMech.add(mech)

        gameResultLirt = ""
        ownerIds
        for owner : ownerIds
            games = owner.getGames()
            for game in games
                gameMech = game.getMechanics()
                for gMech : gameMech
                    if gMech in playerMech
                        break (move on to next game)
                    else
                        if game not in gameResultList
                        gameResultList.add(game)
        gameResultList.random
        results = index point 0-4 from random list above



         */

        return "index";
    }
}