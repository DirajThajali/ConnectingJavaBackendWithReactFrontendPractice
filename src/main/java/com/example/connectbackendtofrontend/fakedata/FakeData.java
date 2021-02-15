package com.example.connectbackendtofrontend.fakedata;

import java.util.Date;
import java.util.Objects;

public class FakeData {

    private long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String pronouns;

    public FakeData(String firstName, String lastName, String nationality, String pronouns) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.pronouns = pronouns;
        this.id = new Date().getTime();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FakeData fakeData = (FakeData) o;
        return id == fakeData.id &&
                Objects.equals(firstName, fakeData.firstName) &&
                Objects.equals(lastName, fakeData.lastName) &&
                Objects.equals(nationality, fakeData.nationality) &&
                Objects.equals(pronouns, fakeData.pronouns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nationality, pronouns);
    }
}
