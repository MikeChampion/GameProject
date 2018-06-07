package me.mikechampion.GameProject.controllers;

import me.mikechampion.GameProject.controllers.models.data.GameDao;
import me.mikechampion.GameProject.controllers.models.forms.EditGameForm;
import me.mikechampion.GameProject.controllers.models.Game;
import me.mikechampion.GameProject.controllers.models.Player;
import me.mikechampion.GameProject.controllers.models.Mechanic;
import me.mikechampion.GameProject.controllers.models.data.MechanicDao;
import me.mikechampion.GameProject.controllers.models.data.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("game")
public class GameController {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private MechanicDao mechanicDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("games", gameDao.findAll());
        model.addAttribute("title", "Game Menu");
        model.addAttribute("heading", "Games");
        return "game/index";
    }

    //Page to add a new game to list of games in database
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Game Menu");
        model.addAttribute("heading", "Add Game");
        model.addAttribute(new Game());
        return "game/add";
    }

    //Adds a new game to list of games in database, redirects to the edit page for that entry
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddGame(@ModelAttribute @Valid Game game, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Game");
            return "game/add";
        }
        gameDao.save(game);
        return "redirect:edit-game/" + game.getId();
    }

    //Displays a specific game in database
    @RequestMapping(value = "view/{gameId}", method = RequestMethod.GET)
    public String viewGame(Model model, @PathVariable int gameId) {
        Game game = gameDao.findOne(gameId);
        model.addAttribute("title", "Game Menu");

        model.addAttribute("game", game);
        
        return "game/view";
    }

    //Displays the edit page for a specific game in database
    @RequestMapping(value = "edit-game/{gameId}", method = RequestMethod.GET)
    public String editGame(Model model, @PathVariable int gameId) {
        Game game = gameDao.findOne(gameId);
        EditGameForm form = new EditGameForm(mechanicDao.findAll(), playerDao.findAll(), game);
        List gMech = game.mechanics();
        List gOwners = game.owners();

        model.addAttribute("title", "Game Menu");
        model.addAttribute("mechanics", mechanicDao.findAll());
        model.addAttribute("owners", playerDao.findAll());
        model.addAttribute("gMech", gMech);
        model.addAttribute("gOwners", gOwners);
        model.addAttribute("heading", "Add mechanics and owner(s) to game: " + game.getName());
        model.addAttribute("form", form);

        return "game/edit-game";
    }

    //Submits changes to a game entry (mechanics) in the database
    @RequestMapping(value = "edit-game", method = RequestMethod.POST)
    public String ProcessEditGame(Model model, @ModelAttribute @Valid EditGameForm form, Errors errors, @RequestParam int[] ownerIds, @RequestParam int[] mechanicIds) {
        if (errors.hasErrors()) {
            model.addAttribute("form", "form");
            return "game/add";
        }

        Game game = gameDao.findOne(form.getGameId());

        List<Mechanic> gMechanics = new ArrayList<>(game.getMechanics());
        for(Mechanic gMech : gMechanics) {
            game.delMechItem(gMech);
            gameDao.save(game);
        }
        for(int mechanicId : mechanicIds) {
            Mechanic mechanic = mechanicDao.findOne(mechanicId);
            game.addMechItem(mechanic);
            gameDao.save(game);
        }

        List<Player> gOwners = new ArrayList<>(game.getOwners());
        for(Player gOwner : gOwners) {
            game.delOwnerItem(gOwner);
            gameDao.save(game);
        }

        for(int ownerId : ownerIds) {
            Player player = playerDao.findOne(ownerId);
            game.addOwnerItem(player);
            gameDao.save(game);
        }

        return "redirect:view/" + game.getId();
    }


    //Displays a list of all games in database with checkboxes for deleting games
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveGame(Model model) {
        model.addAttribute("games", gameDao.findAll());
        model.addAttribute("title", "Game Menu");
        model.addAttribute("heading", "Remove Game");
        return "game/remove";
    }

    //Submits games for deletion
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveGame(@RequestParam int[] gameIds) {
        for (int gameId : gameIds) {
            gameDao.delete(gameId);
        }
        return "redirect:";
    }
}
