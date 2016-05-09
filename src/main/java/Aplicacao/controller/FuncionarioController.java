package Aplicacao.controller;

import Aplicacao.model.Funcionario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
public class FuncionarioController {

    RestTemplate restTemplate = new RestTemplate();
    String mensagem = "";

    @RequestMapping(value = "/cadastroFuncionario")
    public String cadastrarFuncionario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("listaArea", restTemplate.getForObject("http://localhost:8080/listaArea", List.class));
        return "CadastroFuncionario";
    }

    @RequestMapping(value = "/adicionaFuncionario", method = RequestMethod.POST)
    public String adicionaFuncionario(Funcionario funcionario) {
        if (!funcionario.getNome().equals("") && funcionario.getSalarioBase() >= 0) {
            mensagem = restTemplate.getForObject("http://localhost:8080/adicionaFuncionario/"+
                    funcionario.getNome()+"/"+
                    funcionario.getSalarioBase()+"/"+
                    funcionario.getArea(), String.class);
        } else {
            mensagem = "Digite valores válidos.";
        }
        return "redirect:/cadastroFuncionario";
    }

    @RequestMapping(value = "/listaFuncionario", method=RequestMethod.GET)
    public String listarFuncionario(Model model) {
        List<Map<Integer,Funcionario>> funcionario = restTemplate.getForObject("http://localhost:8080/listaFuncionario", List.class);
        List<Map<Integer,Funcionario>> area = restTemplate.getForObject("http://localhost:8080/listaArea", List.class);
        model.addAttribute("listaFuncionario", funcionario);
        model.addAttribute("listaArea", area);
        model.addAttribute("mensagem", mensagem);
        return "ListaFuncionarios";
    }

    @RequestMapping(value = "/deletaFuncionario/{codigo}", method=RequestMethod.GET)
    public String deletarFuncionario(Model model, @PathVariable("codigo") int codigo) {
        mensagem = restTemplate.getForObject("http://localhost:8080/deletaFuncionario/"+codigo, String.class);
        return "redirect:/listaFuncionario";
    }

}