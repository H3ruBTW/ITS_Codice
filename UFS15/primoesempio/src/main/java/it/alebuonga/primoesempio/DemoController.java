package it.alebuonga.primoesempio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
    @RequestMapping(value = "/usr")
    public String getUsers(){
        return "users";
    }
}
