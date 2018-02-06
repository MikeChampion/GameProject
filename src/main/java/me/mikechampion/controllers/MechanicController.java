package me.mikechampion.controllers;

import me.mikechampion.controllers.models.Mechanic;
import me.mikechampion.controllers.models.data.MechanicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("mechanic")
public class MechanicController {

    @Autowired
    private MechanicDao mechanicDao;


    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("mechanics", mechanicDao.findAll());
        model.addAttribute("title", "Mechanics");

        return "mechanic/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("mechanics", mechanicDao.findAll());
        model.addAttribute("title", "Add Mechanic");
        model.addAttribute(new Mechanic());
        return "mechanic/add";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Mechanic mechanic, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "mechanic/add";
        }
        mechanicDao.save(mechanic);
        return "redirect:";
    }
}
