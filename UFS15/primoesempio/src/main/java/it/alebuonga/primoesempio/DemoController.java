package it.alebuonga.primoesempio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
    @RequestMapping(value = "/usr")
    public String getUser(){
        return "users";
    }

    @RequestMapping(value = "/prds")
    public String getProducts(){
        return "products";
    }

    @RequestMapping(value = "/prod/{categ}/{prod_id}")
    public String getProduct(Model model, @PathVariable("categ") String categ, @PathVariable int prod_id){
        model.addAttribute("categ", categ);
        model.addAttribute("id", prod_id);
        return "prodotto";
    }
}
