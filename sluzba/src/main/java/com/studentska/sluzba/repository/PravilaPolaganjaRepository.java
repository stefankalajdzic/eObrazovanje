package com.studentska.sluzba.repository;

import com.studentska.sluzba.model.PravilaPolaganja;
import com.studentska.sluzba.model.PravilaPolaganjaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PravilaPolaganjaRepository extends JpaRepository<PravilaPolaganja, PravilaPolaganjaPK> {
}
