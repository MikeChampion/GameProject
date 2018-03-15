package me.mikechampion.controllers;

import me.mikechampion.controllers.models.Game;
import me.mikechampion.controllers.models.Mechanic;
import me.mikechampion.controllers.models.Player;
import me.mikechampion.controllers.models.data.GameDao;
import me.mikechampion.controllers.models.data.MechanicDao;
import me.mikechampion.controllers.models.data.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
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
        //System.out.println(playerMechs);
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

    @RequestMapping(value = "output", method = RequestMethod.POST)
    public String generateResults(Model model, Game game, @RequestParam List<Player> ownerIds, @RequestParam List<Player> playerIds) {
        List<Mechanic> playerMechs = notMechanics(playerIds);
        //.addAttribute("playerMechs", notMechanics(playerIds));
        List<Game> gameColl = gameCollection(ownerIds);
        //model.addAttribute("gameColl", gameCollection(ownerIds));
        List<Game> gameResults = searchFilter(gameColl, playerMechs);

        //System.out.println(curatedList.size());



        //model.addAttribute("results", searchFilter(gameColl, playerMechs));
        //model.addAttribute("curatedList", curatedList);
        model.addAttribute("owners", ownerIds);
        model.addAttribute("players", playerIds);
        model.addAttribute("title", "Results Page");
        model.addAttribute("gameResults", gameResults);
        //return "result";
        return "results";
    }

    private List searchFilter(List<Game> gameColl, List<Mechanic> playerMechs) {
        ArrayList<Game> gameResults = new ArrayList<>();
        for (Game game : gameColl) {
            int count = 0;
            List<Mechanic> gameMechanics = game.getMechanics();
            //System.out.println(gameMechanics.size());
            for (Mechanic gMech : gameMechanics) {
                if (playerMechs.contains(gMech))
                    count++;
            }
                if (count == 0)
                    gameResults.add(game);
        }
        return gameResults;
    }

    @RequestMapping(value = "results", method = RequestMethod.GET)
    public String displayResults(Model model, Game game, @RequestParam List<Player> ownerIds, @RequestParam List<Player> playerIds, @RequestParam List<Mechanic> playerMechs, @RequestParam List<Game> gameColl) {
        searchFilter(gameColl, playerMechs);


        //System.out.println(ownerIds.size());
        //System.out.println(playerIds.size());
        //System.out.println(gameColl.size());
        //System.out.println(playerMechs.size());
        //System.out.println(curatedList.size());
        return "results";
    }
}