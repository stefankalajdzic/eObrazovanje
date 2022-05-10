package com.studentska.sluzba.controllers;

import com.studentska.sluzba.dto.ErrorDto;
import com.studentska.sluzba.dto.administrator.*;
import com.studentska.sluzba.service.PredmetService;
import com.studentska.sluzba.service.SemestarService;
import com.studentska.sluzba.service.UplataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    UplataService uplataService;

    @Autowired
    PredmetService predmetService;

    @Autowired
    SemestarService semestarService;

    @GetMapping("/uplate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> uplate() {
        List<EvidencijaUplataDTORes> response = null;
        try {
            response = uplataService.uplate();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/evidentirajUplatu")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> evidentirajUplatu(@RequestHeader("Authorization") String token, @RequestBody EvidencijaUplataDTOReq req) {
        try {
            uplataService.evidentirajUplatu(token, req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/postaviUloguNaPredmet")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> postaviUloguNaPredmet(@RequestBody PostaviUloguNaPredmetDTOReq req) {
        try {
            predmetService.postaviUloguNaPredmet(req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dodajPredmet")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> dodajPredmet(@RequestBody KreirajPredmetDTOReq req) {
        try {
            predmetService.dodajPredmet(req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/obrisi")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> obrisi(@RequestParam int id) {
        try {
            predmetService.obrisi(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/overiSemestar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> overiSemestar(@RequestBody OveriSemestarDTOReq req) {
        try {
            semestarService.overiSemestar(req);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
