package com.studentska.sluzba.dto.administrator;

import java.util.Date;

public class EvidencijaUplataDTOReq {
    private double iznos;
    private String svrha;
    private String vremeUplate;
    private int idStudent;

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public String getVremeUplate() {
        return vremeUplate;
    }

    public void setVremeUplate(String vremeUplate) {
        this.vremeUplate = vremeUplate;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }
}
