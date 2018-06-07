package me.mikechampion.GameProject.controllers;

import me.mikechampion.GameProject.controllers.models.Mechanic;
import me.mikechampion.GameProject.controllers.models.data.MechanicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("mechanic")
public class MechanicController {

    @Autowired
    private MechanicDao mechanicDao;



    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("mechanics", mechanicDao.findAll());
        model.addAttribute("heading", "Mechanics");
        model.addAttribute("title", "Game Menu");
        return "mechanic/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("mechanics", mechanicDao.findAll());
        model.addAttribute("heading", "Add Mechanic");
        model.addAttribute(new Mechanic());
        return "mechanic/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Mechanic mechanic, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("heading", "Add Category");
            return "mechanic/add";
        }
        mechanicDao.save(mechanic);
        return "redirect:";
    }

    //Displays a list of all games in database with checkboxes for deleting games
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveMechanic(Model model) {
        model.addAttribute("mechanics", mechanicDao.findAll());
        model.addAttribute("heading", "Remove Mechanic");
        return "mechanic/remove";
    }

    //Submits mechanics for deletion
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveMech(@RequestParam int[] mechanicIds) {
        for (int mechanicId : mechanicIds) {
            mechanicDao.delete(mechanicId);
        }
        return "redirect:";
    }
}
