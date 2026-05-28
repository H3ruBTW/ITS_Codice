package it.buonga.accessdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AccessController {

    @Autowired
    private UtenteDAO utenteDAO;

    AccessController(){
        
    }
    
    @RequestMapping(value="/")
    public String getHome(){
        return "redirect:index.html";
    }

    @RequestMapping(value="/accedi", method = RequestMethod.GET)
    public String getLoginForm(HttpSession session, LoginForm loginForm){
        if(session.getAttribute("user") != null)
            return "redirect:/riservata";

        return "login";
    }

    @RequestMapping(value = "/accedi", method = RequestMethod.POST)
    public String checkLogin(Model model, @Valid LoginForm loginForm, BindingResult br, HttpSession session){
        if(br.hasErrors())
            return "login";

        Utente u = utenteDAO.login(loginForm.getUsername(), loginForm.getPass());

        if(u == null){
            model.addAttribute("error", "Errore di accesso");
            return "login";
        }

        session.setAttribute("user", u);
        return "redirect:/riservata";
    }

    @RequestMapping(value = "/registrati", method = RequestMethod.GET)
    public String getRegisterForm(HttpSession session, Utente utente){
        if(session.getAttribute("user") != null)
            return "redirect:/riservata";

        return "register";
    }

    @RequestMapping(value = "/registrati", method = RequestMethod.POST)
    public String checkRegistration(@Valid Utente utente, BindingResult br, Model model, HttpSession session){
        if(br.hasErrors()){
            return "register";
        }

        utenteDAO.save(utente);

        session.setAttribute("user", utente);
        return "redirect:/riservata";
    }

    @RequestMapping(value = "/admin")
    public String getUsers(Model model){
        model.addAttribute("users", utenteDAO.findAll());
        return "admin-user-list";
    }

    @RequestMapping(value = "/dettagli")
    public String getDetails(Model model, @RequestParam(name = "u")Integer id){

        model.addAttribute("user", utenteDAO.findById(id).orElseThrow());
        return "details";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String signOutUser(HttpSession session){
        session.setAttribute("user", null);
        return "redirect:/";
    }

    @RequestMapping(value = "/riservata")
    public String getRiservata(Model model, HttpSession session){
        if(session.getAttribute("user") == null)
            return "redirect:/accedi";

        model.addAttribute("user", session.getAttribute("user"));
        return "reserved";
    }
}
