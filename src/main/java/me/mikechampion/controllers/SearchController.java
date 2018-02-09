package me.mikechampion.controllers;

import me.mikechampion.controllers.models.Player;
import me.mikechampion.controllers.models.data.PlayerDao;
import me.mikechampion.controllers.models.Mechanic;
import me.mikechampion.controllers.models.data.MechanicDao;
import me.mikechampion.controllers.models.Game;
import me.mikechampion.controllers.models.data.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private GameDao gameDao;

    @Autowired
    private MechanicDao mechanicDao;



    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("games", gameDao.findAll());
        model.addAttribute("players", playerDao.findAll());
        model.addAttribute("title", "MAIN PAGE");
        return "search/index";
    }
}
