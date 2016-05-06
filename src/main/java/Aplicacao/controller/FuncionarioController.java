package Aplicacao.controller;

import Aplicacao.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class FuncionarioController {

    RestTemplate restTemplate = new RestTemplate();



//    @Autowired
//    private FuncionarioDAO funcionarioDAO;
//
//    @Autowired
//    private AreaDao areaDao;
//
//    @Autowired
//    DataSource dataSource;

//    @RequestMapping(value = "/cadastroFuncionario")
//    public String cadastrarFuncionario(Model model) {
//        model.addAttribute("funcionario", new Funcionario());
//        areaDao.setDataSource(dataSource);
//        model.addAttribute("listaArea", areaDao.listar());
//        return "CadastroFuncionario";
//    }
//
//    @RequestMapping(value = "/adicionaFuncionario", method = RequestMethod.POST)
//    public String adicionaFuncionario(Funcionario funcionario) {
//        if (!funcionario.getNome().equals("") && funcionario.getSalarioBase() >= 0) {
//            funcionarioDAO.setDataSource(dataSource);
//            funcionarioDAO.salvar(funcionario);
//        }
//        return "redirect:/cadastroFuncionario";
//    }

    @RequestMapping(value = "/listaFuncionario", method=RequestMethod.GET)
    public String listarFuncionario(Model model) {
        List<Map<Integer,Funcionario>> funcionario = restTemplate.getForObject("http://localhost:8080/listaFuncionario", List.class);
//        model.addAttribute("listaFuncionario", funcionario);
//        model.addAttribute("listaArea", areaDao.listar());
//        return "ListaFuncionarios";
        System.out.println(funcionario.get(0).get("nome"));
        return String.valueOf(funcionario.get(0).get("nome"));
    }

//    @RequestMapping(value = "/deletarFuncionario/{codigo}", method=RequestMethod.GET)
//    public String deletarFuncionario(Model model, @PathVariable("codigo") int codigo) {
//        funcionarioDAO.deletar(codigo);
//        return "redirect:/listaFuncionario";
//    }

}