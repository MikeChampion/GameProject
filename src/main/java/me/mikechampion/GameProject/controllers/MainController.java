package me.mikechampion.GameProject.controllers;

import me.mikechampion.GameProject.controllers.models.Game;
import me.mikechampion.GameProject.controllers.models.Mechanic;
import me.mikechampion.GameProject.controllers.models.Player;
import me.mikechampion.GameProject.controllers.models.data.GameDao;
import me.mikechampion.GameProject.controllers.models.data.MechanicDao;
import me.mikechampion.GameProject.controllers.models.data.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("")
public class MainController {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private MechanicDao mechanicDao;

    //generates checkboxes for collection and player selection
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displaySearchForm(Model model) {
        Iterable<Player> players = playerDao.findAll();
        ArrayList<Player> owners = new ArrayList<>();

        for (Player player : players) {
            List<Game> games = player.getGames();
            int gamesSize = games.size();
            if (gamesSize != 0)
                owners.add(player);
        }
        model.addAttribute("players", playerDao.findAll());
        model.addAttribute("title", "Game Menu");
        model.addAttribute("owners", owners);
        return "index";
    }

    private void playerCount(List<Player> playerIds) {
/*        //removes games from list based on player count
        Integer players = playerIds.size();

        if (playerCount >= maxPlayers or <= minPlayer}
        and
        playerCount >= minPlayers

                    playerMechs.add(mechanic);

        return playerMechs;
    */
    }


    private List gameCollection(List<Player> ownerIds) {
        ArrayList<Game> gameColl = new ArrayList<>();
        for (Player ownerId : ownerIds) {
            Player owner = playerDao.findOne(ownerId.getId());
            List<Game> ownerGames = owner.getGames();
            //System.out.println(ownerGames.size());
            for (Game oGame : ownerGames) {
                if (!gameColl.contains(oGame))
                    gameColl.add(oGame);
            }
        }
        return gameColl;
    }


    private List notMechanics(List<Player> playerIds) {
        //compiles player disliked mechanics into one list
        ArrayList<Mechanic> playerMechs = new ArrayList<>();
        for (Player playerId : playerIds) {
            Player player = playerDao.findOne(playerId.getId());
            List<Mechanic> mechanics = player.getMechanics();
            for (Mechanic mechanic : mechanics) {
                if (!playerMechs.contains(mechanic))
                    playerMechs.add(mechanic);
            }
        }
        return playerMechs;
    }

    private List searchFilter(List<Game> gameColl, List<Mechanic> playerMechs) {
        ArrayList<Game> gameResults = new ArrayList<>();
        for (Game game : gameColl) {
            int count = 0;
            List<Mechanic> gameMechanics = game.getMechanics();
            for (Mechanic gMech : gameMechanics) {
                if (playerMechs.contains(gMech))
                    count++;
            }
            if (count == 0)
                gameResults.add(game);
        }
        return gameResults;
    }

    @RequestMapping(value = "output", method = RequestMethod.POST)
    public String generateResults(Model model, Game game, @RequestParam List<Player> ownerIds, @RequestParam List<Player> playerIds) {
        List<Game> gameColl = gameCollection(ownerIds);
        List<Mechanic> playerMechs = notMechanics(playerIds);
        List<Game> gameResults = searchFilter(gameColl, playerMechs);
        model.addAttribute("owners", ownerIds);
        model.addAttribute("players", playerIds);
        model.addAttribute("title", "Results Page");
        model.addAttribute("gameResults", gameResults);

        return "results";
    }

    @RequestMapping(value = "result", method = RequestMethod.GET)
    public String displayResults(Model model, Game game, @RequestParam List<Player> ownerIds, @RequestParam List<Player> playerIds, @RequestParam List<Mechanic> playerMechs, @RequestParam List<Game> gameColl) {
        searchFilter(gameColl, playerMechs);
        return "results";
    }
}