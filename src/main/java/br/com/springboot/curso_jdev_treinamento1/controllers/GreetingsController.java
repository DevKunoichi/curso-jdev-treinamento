package br.com.springboot.curso_jdev_treinamento1.controllers;

import br.com.springboot.curso_jdev_treinamento1.model.Usuario;
import br.com.springboot.curso_jdev_treinamento1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreetingsController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "salvar")
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        Usuario user = usuarioRepository.save(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "deletar")
    @ResponseBody
    public ResponseEntity<String> deletar(@RequestParam Long idUser) {
       usuarioRepository.deleteById(idUser);
        return new ResponseEntity<String> ("Usuário deletado com sucesso.", HttpStatus.OK);
    }

    @GetMapping(value = "buscarUserId")
    @ResponseBody
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(name = "idUser") Long idUser) {
        Usuario usuario = usuarioRepository.findById(idUser).get();
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @GetMapping(value = "listarTodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuario(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome){
        List<Usuario> usuario = usuarioRepository.buscarPorNome(nome);
        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }

    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<Usuario> atualizar (@RequestBody Usuario usuario){
        Usuario user = usuarioRepository.saveAndFlush(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String nome) {
        return "Curso Spring Boot Rest API com: " + nome + ".";
    }
    
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaHello(@PathVariable String name) {
        return "Hello, " + name + "! How are you?";
    }
}
