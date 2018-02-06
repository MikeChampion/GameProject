package me.mikechampion.controllers;

import me.mikechampion.controllers.models.Player;
import me.mikechampion.controllers.models.data.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("player")
public class PlayerController {

    @Autowired
    private PlayerDao playerDao;


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("players", playerDao.findAll());
        model.addAttribute("title", "Players");

        return "player/index";
        }
    }
