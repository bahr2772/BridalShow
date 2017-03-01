package com.totallysound.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    private String phoneNumber;

    private boolean checkedIn = false;

    private boolean preRegistered = false;

    private String howDidYouHear;

    private String numberOfGuest;

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

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getHowDidYouHear() { return howDidYouHear; }

    public void setHowDidYouHear(String howDidYouHear) { this.howDidYouHear = howDidYouHear; }

    public String getNumberOfGuest() {
        return numberOfGuest;
    }

    public void setNumberOfGuest(String numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }

    public boolean isPreRegistered() { return preRegistered; }

    public void setPreRegistered(boolean preRegistered) { this.preRegistered = preRegistered; }

    @Override
    public String toString() {
        return "Bride{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", weddingDate='" + weddingDate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", checkedIn=" + checkedIn +
                ", howDidYouHear='" + howDidYouHear + '\'' +
                ", numberOfGuest='" + numberOfGuest + '\'' +
                ", preRegistered=" + preRegistered + '\'' +
                '}';
    }
}
