package learn.field_agent.controllers;

import learn.field_agent.domain.AgentService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Agent;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AgentThymeController {

    private final AgentService service;

    public AgentThymeController(AgentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getHome() {
        return "agents/home";
    }

    @GetMapping("/agents/index")
    public String index(Model model) {
        List<Agent> agents = service.findAll();
        model.addAttribute("agents", agents);
        return "agents/index";

    }

    @GetMapping("/agents/create")
    public String create(@ModelAttribute("agent") Agent agent, Model model) {
        return "agents/form";
    }

    @PostMapping("/agents/create")
    public String create(
            @ModelAttribute("agent") @Valid Agent agent, BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            return "agents/form";
        }

        Result<Agent> agentResult = service.add(agent);

        if(!agentResult.isSuccess()) {
            for (String message : agentResult.getMessages()) {
                result.addError(new ObjectError("agent", message));
            }

            return "agents/form";
        }

        return "redirect:/agents/index";
    }

    @GetMapping("/update/{agentId}")
    public String update(@PathVariable int agentId, Model model) {
        Agent agent = service.findById(agentId);

        if(agent == null) {
            return "agents/not-found";
        }

        model.addAttribute("agent", agent);

        return "agents/form";
    }

    @PostMapping("/update/*")
    public String update(
            @ModelAttribute("agent") @Valid Agent agent,
            BindingResult result, Model model
    ){
        if(result.hasErrors()) {
            return "agents.form";
        }

        Result<Agent> agentResult = service.update(agent);

        if(!agentResult.isSuccess()) {
            for (String message : agentResult.getMessages()) {
                result.addError(new ObjectError("agent", message));
            }
            return "agents/form";
        }
        return "redirect:/agents/index";
    }

    @GetMapping("/delete/{agentId}")
    public String delete(@PathVariable int agentId, Model model) {
        Agent agent = service.findById(agentId);

        if (agent == null) {
            return "agents/not-found";
        }

        model.addAttribute("agent",agent);

        return "agents/delete";
    }

    @PostMapping("delete/{agentId}")
    public String delete(@PathVariable int agentId) {
        Agent agent = service.findById(agentId);

        if(agent == null) {
            return "not-found";
        }

        service.deleteById(agentId);

        return "redirect:/agents/index";
    }
}
