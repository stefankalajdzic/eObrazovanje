package com.studentska.sluzba.service;

import com.studentska.sluzba.dto.NovEmailDTOReq;
import com.studentska.sluzba.dto.NovaLozinkaDTOReq;
import com.studentska.sluzba.dto.predavac.*;

import java.util.List;

public interface PredavacService {
    void novEmail(String token, NovEmailDTOReq req);

    void novaLozinka(String token, NovaLozinkaDTOReq req);

    void postaviObavestenje(String token, ObavestenjeDTOReq req);

    void azuriranjeProfila(String token, AzuriranjeProfilaDTOReq req);

    void unesiBrojBodova(String token, UnosBodovaDTOReq req);

    void unesiOcenu(String token, UnosOceneDTOReq req);

    void elektronskiPotpis(String token, ElektronskiPotpisDTOReq req);

    void dodajTermin(String token, DodajTerminPolaganjaDTOReq req);

    List<PrijavaDTORes> prijaveZaTermin(String token, int id);
}
