package com.ufersa.ed2praticaoffline1.client.interfaces;

import com.ufersa.ed2praticaoffline1.model.ArvoreAVL.No;
import com.ufersa.ed2praticaoffline1.model.Protocolos.AutomovelProtocolo;
import com.ufersa.ed2praticaoffline1.model.Protocolos.AutomovelResponse;
import com.ufersa.ed2praticaoffline1.model.entities.Automovel;

import java.util.List;

public interface IConcessionariaClient {
    String addAutomovel(List<AutomovelProtocolo> automovelProtocolos);

    String editarAutomovel(AutomovelProtocolo automovelProtocolo, String chave);

    List<AutomovelResponse> buscarAutomoveis();

    AutomovelResponse buscarAutomovel(String chave);

    String deletarAutomovel(String chave);

}
