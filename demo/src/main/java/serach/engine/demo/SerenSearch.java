package serach.engine.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SerenSearch {
    @RequestMapping("/Seren")
    public String Hi(Model M){
       // M.addAttribute("message", "Bienvenue sur Thymeleaf avec Spring Boot!");
        return "test";
    }
}
