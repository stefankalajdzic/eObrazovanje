package com.studentska.sluzba.dto.administrator;

import java.util.List;

public class PostaviUloguNaPredmetDTOReq {
    private int idProfesor;
    private int idPredavac;
    private String uloga;



    private List<Integer> tipoviPolaganja;

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdPredavac() {
        return idPredavac;
    }

    public void setIdPredavac(int idPredavac) {
        this.idPredavac = idPredavac;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public List<Integer> getTipoviPolaganja() {
        return tipoviPolaganja;
    }

    public void setTipoviPolaganja(List<Integer> tipoviPolaganja) {
        this.tipoviPolaganja = tipoviPolaganja;
    }


}
