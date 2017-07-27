package br.com.ceuma.controller;

import br.com.ceuma.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marcus on 26/07/17.
 */
@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @RequestMapping(value = "/teste",produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Response> teste(){
        return new ResponseEntity<>(new Response(200,"OK"), HttpStatus.OK);
    }
}