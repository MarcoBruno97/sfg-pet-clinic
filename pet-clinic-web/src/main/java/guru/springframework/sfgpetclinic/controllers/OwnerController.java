package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/owners")//this is a prefixed for others paths
@Controller

public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder ) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String listOwners(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", new Owner());

        return "owners/findOwners";
    }

    @GetMapping("/{id}")
    public String showDetails(@PathVariable Long id, Model model){
        model.addAttribute("owner", ownerService.findById(id));
        return "owners/ownerDetails";
    }

    @GetMapping
    public String processFindForm(@ModelAttribute Owner owner, BindingResult bindingResult, Model model){//maybe ModelAttribute is not mandatory here
        if(owner.getLastName() == null) {
            owner.setLastName("");
        }
        List<Owner> owners = ownerService.findAllByLastNameLike(owner.getLastName().toLowerCase());
        if(owners.isEmpty()) {
            bindingResult.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        if(owners.size() == 1) {
            model.addAttribute("owner", owners.iterator().next());
            return "redirect:/owners/" + owners.get(0).getId();
        }
        model.addAttribute("selections", owners);
        return "owners/ownersList";
    }



    @GetMapping("/new")
    public String addOrUpdateOwner(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String addOwner(@ModelAttribute Owner owner, Model model) {
        Owner savedOwner = ownerService.save(owner);
        model.addAttribute("owner", savedOwner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/{id}/edit")
    public String initUpdateOwnerForm(@PathVariable Long id, Model model) {
        model.addAttribute("owner", ownerService.findById(id));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{id}/edit")
    public String processUpdateOwnerForm(@ModelAttribute Owner owner, @PathVariable Long id, BindingResult result, Model model) {//in post verb, model contains what get verb had contained, so here owner is inside model param
        if(result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        owner.setId(id);
        ownerService.save(owner);
        //Owner owner1 = (Owner) model.getAttribute("owner");
        //System.out.println(owner1.getFirstName());
        return "redirect:/owners/" + owner.getId();
    }
}
