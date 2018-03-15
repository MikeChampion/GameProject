package me.mikechampion.controllers;

import me.mikechampion.controllers.models.Player;
import me.mikechampion.controllers.models.data.PlayerDao;
import me.mikechampion.controllers.models.Mechanic;
import me.mikechampion.controllers.models.data.MechanicDao;
import me.mikechampion.controllers.models.forms.EditPlayerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("player")
public class PlayerController {

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private MechanicDao mechanicDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("players", playerDao.findAll());
        model.addAttribute("title", "Players");
        return "player/index";
        }

    //Page to add a new player to list of players in database
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add a new player");
        model.addAttribute("name", "Add player");
        model.addAttribute(new Player());
        return "player/add";
    }

    //Adds a new player to list of players in database, redirects to the edit page for that entry
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPlayer(@ModelAttribute @Valid Player player, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Player");
            return "player/add";
        }
        playerDao.save(player);
        return "redirect:edit-player/" + player.getId();
    }

    //Displays a specific player in database
    @RequestMapping(value = "view/{playerId}", method = RequestMethod.GET)
    public String viewPlayer(Model model, @PathVariable int playerId) {
        Player player = playerDao.findOne(playerId);
        model.addAttribute("title", "Specific Player");
        model.addAttribute("player", player);
        return "player/view";
    }

    //Displays the edit page for a specific player in database
    @RequestMapping(value = "edit-player/{playerId}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable int playerId) {
        Player player = playerDao.findOne(playerId);
        String pName = player.getName();
        EditPlayerForm form = new EditPlayerForm(mechanicDao.findAll(), player);
        List pMech = player.mechanics();

        model.addAttribute("mechanics", mechanicDao.findAll());
        model.addAttribute("pMech", pMech);
        model.addAttribute("title", "Add mechanics to player: " + player.getName());
        model.addAttribute("form", form);
        //System.out.println(pMech);
        return "player/edit-player";
    }


    //Submits changes to a player entry (mechanics) in the database
    @RequestMapping(value = "edit-player", method = RequestMethod.POST)
    public String ProcessAddItem(Model model, @ModelAttribute @Valid EditPlayerForm form, Errors errors, @RequestParam int[] mechanicIds) {
        if (errors.hasErrors()) {
            model.addAttribute("form", "form");
            return "player/add";
        }
        //player being edited
        Player player = playerDao.findOne(form.getPlayerId());

        List<Mechanic> pMechanics = new ArrayList<>(player.getMechanics());
        for(Mechanic pMech : pMechanics) {
                player.delItem(pMech);
                playerDao.save(player);
        }

        for(int mechanicId : mechanicIds) {
            Mechanic mechanic = mechanicDao.findOne(mechanicId);
            //System.out.println(mechanic);
            player.addItem(mechanic);
            playerDao.save(player);
        }

        return "redirect:";
    }

    //Displays a list of all players in database with checkboxes for deleting players
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePlayer(Model model) {
        model.addAttribute("players", playerDao.findAll());
        model.addAttribute("title", "Remove Player");
        return "player/remove";
    }

    //Submits players for deletion
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePlayer(@RequestParam int[] playerIds){
            for (int playerId : playerIds) {
                playerDao.delete(playerId);
            }
            return "redirect:";
        }
}
