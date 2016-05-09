package Aplicacao.controller;

import Aplicacao.model.Area;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
public class AreaController {

    RestTemplate restTemplate = new RestTemplate();
    String mensagem = "";

    @RequestMapping(value = "/cadastroArea")
    public String cadastrarFuncionario(Model model) {
        model.addAttribute("area", new Area());
        model.addAttribute("mensagem", mensagem);
        return "CadastroArea";
    }

    @RequestMapping(value = "/adicionaArea", method = RequestMethod.POST)
    public String adicionaFuncionario(Area area) {
        if (!area.getNome().equals("") && !area.getNome().equals("null")) {
            mensagem = restTemplate.getForObject("http://localhost:8080/adicionaArea/"+area.getNome(), String.class);
        } else {
            mensagem = "Digite um valor válido.";
        }
        return "redirect:/cadastroArea";
    }

    @RequestMapping(value = "/listaArea", method=RequestMethod.GET)
    public String listarArea(Model model) {
        List<Map<Integer, Area>> area = restTemplate.getForObject("http://localhost:8080/listaArea", List.class);
        model.addAttribute("lista", area);
        model.addAttribute("mensagem", mensagem);
        return "ListaArea";
    }

    @RequestMapping(value = "/deletaArea/{codigo}", method=RequestMethod.GET)
    public String deletarFuncionario(Model model, @PathVariable("codigo") int codigo) throws Exception {
        try {
            mensagem = restTemplate.getForObject("http://localhost:8080/deletaArea/"+codigo, String.class);
        } catch (Exception e) {
            mensagem = e.getMessage();
        }
        return "redirect:/listaArea";
    }

    @RequestMapping(value = "/deletaAreaCascata/{codigo}", method=RequestMethod.GET)
    public String deletarFuncionarioCascata(Model model, @PathVariable("codigo") int codigo) {
        mensagem = restTemplate.getForObject("http://localhost:8080/deletaAreaCascata/"+codigo, String.class);
        return "redirect:/listaArea";
    }

}
