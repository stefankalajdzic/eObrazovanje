package com.studentska.sluzba.service;

import com.studentska.sluzba.dto.predavac.ProfileDtoRes;
import com.studentska.sluzba.dto.student.AzuriranjeProfilaDTOReq;
import com.studentska.sluzba.dto.NovEmailDTOReq;
import com.studentska.sluzba.dto.NovaLozinkaDTOReq;
import com.studentska.sluzba.dto.student.DetaljiPredmetaDTORes;

public interface StudentService {
    void novEmail(String token, NovEmailDTOReq req);

    void novaLozinka(String token, NovaLozinkaDTOReq req);


    void azuriranjeProfila(String token, AzuriranjeProfilaDTOReq req);

    void uverenjeOStudiranju(String token);

    ProfileDtoRes getProfile(String token);
}
