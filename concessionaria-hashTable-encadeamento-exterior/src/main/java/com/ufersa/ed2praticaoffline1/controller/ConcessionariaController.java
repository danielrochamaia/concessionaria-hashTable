package com.ufersa.ed2praticaoffline1.controller;
import com.ufersa.ed2praticaoffline1.model.ArvoreAVL.No;
import com.ufersa.ed2praticaoffline1.model.Protocolos.AutomovelResponse;
import com.ufersa.ed2praticaoffline1.model.entities.Automovel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ufersa.ed2praticaoffline1.model.Protocolos.AutomovelProtocolo;
import com.ufersa.ed2praticaoffline1.client.interfaces.IConcessionariaClient;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConcessionariaController {

    private IConcessionariaClient concessionariaClient;

    public ConcessionariaController(IConcessionariaClient concessionariaClient){
        this.concessionariaClient = concessionariaClient;
    }

    @PostMapping("/automovel")
    public ResponseEntity<String> AddAutomovel(@RequestBody List<AutomovelProtocolo> automovelProtocolos) {
        var response = concessionariaClient.addAutomovel(automovelProtocolos);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/automovel")
    public ResponseEntity<List<AutomovelResponse>> BuscarAutomoveis() {
        var response = concessionariaClient.buscarAutomoveis();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/automovel/quantidade")
    public ResponseEntity<String> QuantidadeAutomoveis() {
        var response = concessionariaClient.buscarAutomoveis().size();
        return new ResponseEntity<>(response + " automéveis.", HttpStatus.OK);
    }

    @GetMapping("/automovel/{chave}")
    public ResponseEntity<AutomovelResponse> BuscarAutomovelPorChave(@PathVariable String chave) {
        var response = concessionariaClient.buscarAutomovel(chave);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/automovel/{chave}")
    public ResponseEntity<String> RemoverAutomovel(@PathVariable String chave) {
        var response = concessionariaClient.deletarAutomovel(chave);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/automovel/{chave}")
    public ResponseEntity<String> EditarAutomovel(@RequestBody AutomovelProtocolo automovelProtocolo, @PathVariable String chave){
        var response = concessionariaClient.editarAutomovel(automovelProtocolo, chave);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
