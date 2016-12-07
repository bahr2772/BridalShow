package com.totallysound.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

import static javax.print.attribute.standard.MediaPrintableArea.MM;

/**
 * Created by bahr2772 on 12/5/16.
 */

@Entity
@Table(name = "bride")
public class Bride {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;
    @NotNull
    private String email;

    @NotNull
    private String weddingDate;

    private boolean checkedIn = false;



    public Bride(){ }

    public Bride(long id){this.id = id; }

    public Bride(String email, String name){
        this.email = email;
        this.name = name;
    }


    public String getWeddingDate() { return weddingDate; }

    public void setWeddingDate(String weddingDate) { this.weddingDate = weddingDate; }

    public boolean isCheckedIn() { return checkedIn; }

    public void setCheckedIn(boolean checkedIn) { this.checkedIn = checkedIn; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    @Override
    public String toString(){
        return "Bride: Name: " + name + ", Email: " + email + ", Wedding Date: " ;
    }



}
