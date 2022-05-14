package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.TerminPolaganja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminPolaganjaRepository extends JpaRepository<TerminPolaganja, Integer> {

}
