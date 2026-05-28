package it.itsrizzoli.access;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

@Controller
public class AccessController {

    List<RegisterForm> utenti = new ArrayList<>();

    AccessController(){
        utenti.add(new RegisterForm("Ale", "Buonga", "aleb", "password"));
        utenti.add(new RegisterForm("Luca", "D'angelo", "luka", "password"));
        utenti.add(new RegisterForm("Fra", "Beschi", "frab", "password"));
        utenti.add(new RegisterForm("Ale", "Nigzi", "aler", "password"));
        utenti.add(new RegisterForm("Ale", "PagoPa", "alep", "password"));
    }
    
    @RequestMapping(value="/")
    public String getHome(){
        return "redirect:index.html";
    }

    @RequestMapping(value="/accedi", method = RequestMethod.GET)
    public String getLoginForm(LoginForm loginForm){
        return "login";
    }

    @RequestMapping(value = "/accedi", method = RequestMethod.POST)
    public String checkLogin(@Valid LoginForm loginForm, BindingResult br, Model model){
        if(br.hasErrors())
            return "login";

        for (RegisterForm utente : utenti) {
            if(utente.username.equals(loginForm.username) && utente.pass.equals(loginForm.pass)){
                model.addAttribute("user", utente);
                return "riservata";
            }
        }

        model.addAttribute("error", "Errore di accesso");
        return "login";
    }

    @RequestMapping(value = "/registrati", method = RequestMethod.GET)
    public String getRegisterForm(RegisterForm registerForm){
        return "register";
    }

    @RequestMapping(value = "/registrati", method = RequestMethod.POST)
    public String checkRegistration(@Valid RegisterForm registerForm, BindingResult br, Model model){
        if(br.hasErrors()){
            return "register";
        }

        utenti.add(registerForm);

        model.addAttribute("user", registerForm);
        return "riservata";
    }

    @RequestMapping(value = "/admin")
    public String getUsers(Model model){
        model.addAttribute("users", utenti);
        return "admin-user-list";
    }

    @RequestMapping(value = "/dettagli")
    public String getDetails(Model model, @RequestParam(name = "u")String user){

        for (RegisterForm registerForm : utenti) {
            if(registerForm.username.equals(user)){
                model.addAttribute("user", registerForm);
                break;
            }
        }
        return "details";
    }
}
