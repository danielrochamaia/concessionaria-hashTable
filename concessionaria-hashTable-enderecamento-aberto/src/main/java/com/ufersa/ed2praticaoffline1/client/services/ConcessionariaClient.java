package com.ufersa.ed2praticaoffline1.client.services;

import com.ufersa.ed2praticaoffline1.model.ArvoreAVL.No;
import com.ufersa.ed2praticaoffline1.model.Protocolos.AutomovelResponse;
import com.ufersa.ed2praticaoffline1.model.entities.Automovel;
import com.ufersa.ed2praticaoffline1.model.entities.Condutor;
import com.ufersa.ed2praticaoffline1.model.Protocolos.AutomovelProtocolo;
import com.ufersa.ed2praticaoffline1.client.interfaces.IConcessionariaClient;
import com.ufersa.ed2praticaoffline1.servidor.interfaces.IConcessioanariaServidor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcessionariaClient implements IConcessionariaClient {

    private IConcessioanariaServidor concessioanariaServidor;

    public ConcessionariaClient(IConcessioanariaServidor concessioanariaServidor){
        this.concessioanariaServidor = concessioanariaServidor;

    }

    public String addAutomovel(List<AutomovelProtocolo> automovelProtocolos) {
        int repetidos = 0;
        for(int i = 0; i <= automovelProtocolos.size()-1;i++){
            var automovel = new Automovel();
            automovel = setDados(automovel, automovelProtocolos.get(i));
            repetidos = concessioanariaServidor.addAutomovel(automovel);
        }
        return automovelProtocolos.size() - repetidos + " automóveis adicionados!";
    }

    public String editarAutomovel(AutomovelProtocolo automovelProtocolo, String chave) {
        var obj = concessioanariaServidor.buscarPorChave(chave);
        setDados(obj, automovelProtocolo);
        return obj.toString();
    }

    public List<AutomovelResponse> buscarAutomoveis() {
        return concessioanariaServidor.buscarTodos();
    }

    public AutomovelResponse buscarAutomovel(String chave) {
        var automovel = concessioanariaServidor.buscarPorChave(chave);
        return new AutomovelResponse(chave, automovel);
    }

    public String deletarAutomovel(String chave) {
        return concessioanariaServidor.removerAutomovel(chave);
    }

    private Automovel setDados(Automovel automovel, AutomovelProtocolo automovelProtocolo){
        automovel.setCondutor(new Condutor(automovelProtocolo.nomeCondutor, automovelProtocolo.cpfCondutor));
        automovel.setRenavam(automovelProtocolo.renavam);
        automovel.setModelo(automovelProtocolo.modelo);
        automovel.setPlaca(automovelProtocolo.placa);
        automovel.setDataFabricacao(automovelProtocolo.dataFabricacao);
        return automovel;
    }
}