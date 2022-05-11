package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.PredavacPredmet;
import com.studentska.sluzba.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredavacPredmetRepository extends JpaRepository<PredavacPredmet, Integer> {
    PredavacPredmet findOneByPredmetAndUloga(Predmet predmet, String uloga);
}
