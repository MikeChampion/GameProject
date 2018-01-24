package me.mikechampion.controllers;


import me.mikechampion.controllers.models.Game;
import me.mikechampion.controllers.models.Mechanic;
import me.mikechampion.controllers.models.data.GameDao;
import me.mikechampion.controllers.models.data.MechanicDao;
import me.mikechampion.controllers.models.forms.AddGameMechForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("game")
public class GameController {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private MechanicDao mechanicDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("games", gameDao.findAll());
        model.addAttribute("title", "My Games");
        return "game/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add a new game");
        model.addAttribute("name", "Add Game");
        model.addAttribute(new Game());
        return "game/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddGame(@ModelAttribute @Valid Game game, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Game");
            return "game/add";
        }
        gameDao.save(game);
        return "redirect:edit-game/" + game.getId();
    }

    @RequestMapping(value = "view/{gameId}", method = RequestMethod.GET)
    public String viewGame(Model model, @PathVariable int gameId) {
        Game game = gameDao.findOne(gameId);
        model.addAttribute("title", "Specific Game");
        model.addAttribute("game", game);

        return "game/view";
    }

    @RequestMapping(value = "edit-game/{gameId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int gameId) {
        Game game = gameDao.findOne(gameId);
        AddGameMechForm form = new AddGameMechForm(mechanicDao.findAll(), game);
        model.addAttribute("title", "Add mechanics to game: " + game.getName());
        model.addAttribute("form", form);
        return "game/edit-game";
    }

    @RequestMapping(value = "edit-game", method = RequestMethod.POST)
    public String addItem(Model model, @ModelAttribute @Valid AddGameMechForm form, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "game/edit-game";
        }

        Mechanic theMechanic = mechanicDao.findOne(form.getMechanicId());
        Game theGame = gameDao.findOne(form.getGameId());
        theGame.addItem(theMechanic);
        gameDao.save(theGame);
        return "redirect:view/"+theGame.getId();
    }


        @RequestMapping(value = "remove", method = RequestMethod.GET)
        public String displayRemoveGame(Model model) {
            model.addAttribute("games", gameDao.findAll());
            model.addAttribute("title", "Remove Game");
            return "game/remove";
        }

        @RequestMapping(value = "remove", method = RequestMethod.POST)
        public String processRemoveGame(@RequestParam int[] gameIds) {

            for (int gameId : gameIds) {
                gameDao.delete(gameId);
            }

            return "redirect:";
        }
}
