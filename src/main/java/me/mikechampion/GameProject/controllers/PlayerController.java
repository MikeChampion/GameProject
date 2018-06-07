package me.mikechampion.GameProject.controllers;

import me.mikechampion.GameProject.controllers.models.Mechanic;
import me.mikechampion.GameProject.controllers.models.Player;
import me.mikechampion.GameProject.controllers.models.data.MechanicDao;
import me.mikechampion.GameProject.controllers.models.data.PlayerDao;
import me.mikechampion.GameProject.controllers.models.forms.EditPlayerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("heading", "Players");
        model.addAttribute("title", "Game Menu");
        return "player/index";
        }

    //Page to add a new player to list of players in database
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("heading", "Add Player");
        model.addAttribute("title", "Game Menu");
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
        model.addAttribute("title", "Game Menu");
        model.addAttribute("player", player);

        return "player/view";
    }

    //Displays the edit page for a specific player in database
    @RequestMapping(value = "edit-player/{playerId}", method = RequestMethod.GET)
    public String editPlayer(Model model, @PathVariable int playerId) {
        Player player = playerDao.findOne(playerId);
        List ePlayerMech = player.mechanics();
        String name = "";
        EditPlayerForm form = new EditPlayerForm(mechanicDao.findAll(), player);


        model.addAttribute("player", player);
        model.addAttribute("mechanics", mechanicDao.findAll());
        model.addAttribute("ePlayerMech", ePlayerMech);
        model.addAttribute("title", "Edit player: " + player.getName());

        model.addAttribute("form", form);
        //System.out.println(pMech);
        return "player/edit-player";
    }


    //Submits changes to a player entry (mechanics) in the database
    @RequestMapping(value = "edit-player", method = RequestMethod.POST)
    public String editPlayer(Model model, @ModelAttribute @Valid EditPlayerForm form, Errors errors, @RequestParam int[] mechanicIds) {
        if (errors.hasErrors()) {
            model.addAttribute("form", "form");
            return "player/add";
        }
        //player being edited
        Player player = playerDao.findOne(form.getPlayerId());
        int playerId = form.getPlayerId();
        String pName = player.getName();
        String name = form.getName();
        Player update = new Player();
        //int[] mechanicIds = form.getMechanicIds();

/*
//TODO 2.1 add ability to edit player name HIBERNATE SESSIONFACTORY tutorial
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        update = (Player) session.get(Player.class, playerId);
        update.setName(nName);
        session.update(update);
        session.getTransaction().commit();
        session.close();
*/
//TODO 2.2 copy 'edit player name' to implement 'edit bggName'

        List<Mechanic> pMechanics = new ArrayList<>(player.getMechanics());
        for(Mechanic pMech : pMechanics) {
                player.delMechItem(pMech);
                playerDao.save(player);
        }

        for(int mechanicId : mechanicIds) {
            Mechanic mechanic = mechanicDao.findOne(mechanicId);
            player.addMechItem(mechanic);
            playerDao.save(player);
        }

        return "redirect:";
    }

    //Displays a list of all players in database with checkboxes for deleting players
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePlayer(Model model) {
        model.addAttribute("players", playerDao.findAll());
        model.addAttribute("heading", "Remove Player");
        model.addAttribute("title", "Game Menu");
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
