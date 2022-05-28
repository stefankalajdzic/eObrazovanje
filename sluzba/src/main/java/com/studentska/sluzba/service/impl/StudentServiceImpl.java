package com.studentska.sluzba.service.impl;

import com.studentska.sluzba.dto.predavac.ProfileDtoRes;
import com.studentska.sluzba.dto.student.AzuriranjeProfilaDTOReq;
import com.studentska.sluzba.dto.NovEmailDTOReq;
import com.studentska.sluzba.dto.NovaLozinkaDTOReq;
import com.studentska.sluzba.model.Student;
import com.studentska.sluzba.repository.StudentRepository;
import com.studentska.sluzba.security.SecurityConfiguration;
import com.studentska.sluzba.security.TokenUtils;
import com.studentska.sluzba.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SecurityConfiguration configuration;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void novEmail(String token, NovEmailDTOReq req) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);
        student.setEmail(req.getEmail());
        studentRepository.save(student);
    }

    @Override
    public void novaLozinka(String token, NovaLozinkaDTOReq req) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);
        student.setPass(configuration.passwordEncoder().encode(req.getNovaLozinka()));
        studentRepository.save(student);
    }

    @Override
    public void azuriranjeProfila(String token, AzuriranjeProfilaDTOReq req) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);
        student.setIme(req.getIme());
        student.setPrezime(req.getPrezime());
        student.setBrojIndexa(req.getBrojIndeksa());
        studentRepository.save(student);
    }

    @Override
    public void uverenjeOStudiranju(String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setSubject("Zaboravljena lozinka");
            mimeMessageHelper.setFrom("dummyemail@mail.com");
            mimeMessageHelper.setTo("administracija@mail.com");
            mimeMessageHelper.setText("Zahtev za uverenje o studiranju od " + student.getEmail() +
                    " broj indeksa " + student.getBrojIndexa() +
                    " ime i prezime " + student.getIme() + " " + student.getPrezime());
            new Thread(() -> {
                mailSender.send(mimeMessageHelper.getMimeMessage());
            }).start();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProfileDtoRes getProfile(String token) {
        String username = tokenUtils.getUsernameFromToken(token.split("\\s")[1]);
        Student student = studentRepository.findOneByEmail(username);
        ProfileDtoRes res = new ProfileDtoRes();
        res.setPrezime(student.getPrezime());
        res.setIme(student.getIme());
        res.setBrojIndeksa(student.getBrojIndexa());
        return res;
    }


}
