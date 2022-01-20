package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/owners")//thi is a prefixed for others paths
@Controller
public class OwnerController {
    @RequestMapping({"","/","/index","/index.html"})
    public String listOwners(){
        return "owners/index";
    }

}
