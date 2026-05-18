package it.alebuonga.calcolatrice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalcController {
    @RequestMapping(value = "/sum")
    public String somma(Model model, @RequestParam int v1, @RequestParam int v2){
        model.addAttribute("v1", v1);
        model.addAttribute("v2", v2);
        model.addAttribute("simb", "+");
        model.addAttribute("ris", v1 + v2);
        return "calc";
    }

    @RequestMapping(value = "/sot")
    public String sottrazione(Model model, @RequestParam int v1, @RequestParam int v2){
        model.addAttribute("v1", v1);
        model.addAttribute("v2", v2);
        model.addAttribute("simb", "-");
        model.addAttribute("ris", v1 - v2);
        return "calc";
    }

    @RequestMapping(value = "/mol")
    public String moltiplicazione(Model model, @RequestParam int v1, @RequestParam int v2){
        model.addAttribute("v1", v1);
        model.addAttribute("v2", v2);
        model.addAttribute("simb", "x");
        model.addAttribute("ris", v1 * v2);
        return "calc";
    }

    @RequestMapping(value = "/div")
    public String divisione(Model model, @RequestParam int v1, @RequestParam int v2){
        model.addAttribute("v1", v1);
        model.addAttribute("v2", v2);
        model.addAttribute("simb", "/");
        if(v2 != 0){
            model.addAttribute("ris", (double) v1 / v2);
        } else {
            model.addAttribute("ris", "Impossibile dividere per 0");
        }
        
        return "calc";
    }

    @RequestMapping(value = "/pot")
    public String potenza(Model model, @RequestParam int v1, @RequestParam int v2){
        model.addAttribute("v1", v1);
        model.addAttribute("v2", v2);
        model.addAttribute("simb", "^");
        model.addAttribute("ris", (int) Math.pow(v1, v2));
        
        return "calc";
    }
}
