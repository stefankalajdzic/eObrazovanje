package com.studentska.sluzba.service.impl;

import com.studentska.sluzba.dto.NovEmailDTOReq;
import com.studentska.sluzba.dto.NovaLozinkaDTOReq;
import com.studentska.sluzba.dto.predavac.*;
import com.studentska.sluzba.model.*;
import com.studentska.sluzba.repository.*;
import com.studentska.sluzba.security.SecurityConfiguration;
import com.studentska.sluzba.security.TokenUtils;
import com.studentska.sluzba.service.PredavacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PredavacServiceImpl implements PredavacService {

    @Autowired
    PredavacRepository predavacRepository;

    @Autowired
    PredmetRepository predmetRepository;

    @Autowired
    ObavestenjeRepository  obavestenjeRepository;

    @Autowired
    SlusaPredmetRepository slusaPredmetRepository;

    @Autowired
    PolaganjeRepository polaganjeRepository;

    @Autowired
    PravilaPolaganjaRepository pravilaPolaganjaRepository;

    @Autowired
    TerminPolaganjaRepository terminPolaganjaRepository;

    @Autowired
    SecurityConfiguration configuration;

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public void novEmail(String token, NovEmailDTOReq req) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Predavac predavac = predavacRepository.findOneByEmail(username);
        predavac.setEmail(req.getEmail());
        predavacRepository.save(predavac);
    }

    @Override
    public void novaLozinka(String token, NovaLozinkaDTOReq req) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Predavac predavac = predavacRepository.findOneByEmail(username);
        predavac.setPass(configuration.passwordEncoder().encode(req.getNovaLozinka()));
        predavacRepository.save(predavac);
    }

    @Override
    public void postaviObavestenje(String token, ObavestenjeDTOReq req) {
        Obavestenje obavestenje = new Obavestenje();
        obavestenje.setPredmet(predmetRepository.getById(req.getIdPredmet()));
        obavestenje.setTekst(req.getTekst());
        obavestenje.setVremeObjave(new Date());
        obavestenjeRepository.save(obavestenje);
    }

    @Override
    public void azuriranjeProfila(String token, AzuriranjeProfilaDTOReq req) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Predavac predavac = predavacRepository.findOneByEmail(username);
        predavac.setIme(req.getIme());
        predavac.setPrezime(req.getPrezime());
        predavacRepository.save(predavac);
    }

    @Override
    public void unesiBrojBodova(String token, UnosBodovaDTOReq req) {
        Polaganje polaganje = polaganjeRepository.getById(req.getIdPolaganje());
        polaganje.setOstvarenBrojBodova(req.getOstvarenBrojBodova());
        polaganjeRepository.save(polaganje);
    }

    @Override
    public void unesiOcenu(String token, UnosOceneDTOReq req) {
        SlusaPredmet sp = slusaPredmetRepository.getById(req.getIdSlusaPredmet());
        sp.setOcena(req.getOcena());
        slusaPredmetRepository.save(sp);
    }

    @Override
    public void elektronskiPotpis(String token, ElektronskiPotpisDTOReq req) {
        SlusaPredmet sp = slusaPredmetRepository.getById(req.getIdSlusaPredmet());
        sp.setElektronskiPotpis((byte) 1);
        slusaPredmetRepository.save(sp);
    }

    @Override
    @Transactional
    public void dodajTermin(String token, DodajTerminPolaganjaDTOReq req) {
        PravilaPolaganjaPK pk = new PravilaPolaganjaPK();
        pk.setPredmetId(req.getPredmetId());
        pk.setTipPolaganjaId(req.getTipPolaganjaId());
        PravilaPolaganja pravilaPolaganja = new PravilaPolaganja();;
        pravilaPolaganja.setId(pk);
        pravilaPolaganjaRepository.saveAndFlush(pravilaPolaganja);

        TerminPolaganja tp = new TerminPolaganja();
        tp.setNapomena(req.getNapomena());
        tp.setNazivRoka(req.getNazivRoka());
        tp.setPravilaPolaganja(pravilaPolaganja);
        terminPolaganjaRepository.save(tp);
    }

    @Override
    public List<PrijavaDTORes> prijaveZaTermin(String token, int id) {
        List<PrijavaDTORes> res = new ArrayList<>();
        TerminPolaganja tp = terminPolaganjaRepository.getById(id);
        for(Polaganje p:tp.getPolaganjes()){
            PrijavaDTORes tmp = new PrijavaDTORes();
            tmp.setIdPredmet(p.getSlusaPredmet().getPredmet().getId());
            tmp.setFinalnaOcena(p.getSlusaPredmet().getOcena());
            tmp.setIdStudent(p.getSlusaPredmet().getStudent().getId());
            tmp.setId(p.getId());
            tmp.setVremePrijave(p.getVremePrijave());
            tmp.setImeStudenta(p.getSlusaPredmet().getStudent().getIme());
            tmp.setNazivPredmeta(p.getSlusaPredmet().getPredmet().getNaziv());
            tmp.setPrezimeStudenta(p.getSlusaPredmet().getStudent().getPrezime());
            tmp.setOstvarenBrojBodova(p.getOstvarenBrojBodova());
            res.add(tmp);
        }
        return res;
    }
}
