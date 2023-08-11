package com.school.spring.thymeleaf.model;

import javax.persistence.*;

@Entity
@Table(name = "mahasiswa")
public class Mahasiswa {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;


  @Column(nullable = false)
  private String name;



  @Column(length = 128, nullable = false)
  private String nis;

  @Column(nullable = false)
  private String dateOfBirth;

  @Column(nullable = false)
  private String placeOfBirth;

  @Column(length = 15, nullable = false)
  private String mobilePhoneNumber;

  @Column(nullable = false)
  private String email;

  @Column
  private boolean published;

  public Mahasiswa() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNis() {
    return nis;
  }

  public void setNis(String nis) {
    this.nis = nis;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }

  public String getMobilePhoneNumber() {
    return mobilePhoneNumber;
  }

  public void setMobilePhoneNumber(String mobilePhoneNumber) {
    this.mobilePhoneNumber = mobilePhoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }


  public Mahasiswa(String name, String nis, String dateOfBirth, String placeOfBirth, String mobilePhoneNumber, String email) {
    this.name = name;
    this.nis = nis;
    this.dateOfBirth = dateOfBirth;
    this.placeOfBirth = placeOfBirth;
    this.mobilePhoneNumber = mobilePhoneNumber;
    this.email = email;
  }


  @Override
  public String toString() {
    return "Mahasiswa [id=" + id + ", name=" + name + ", nis=" + nis + ", dateOfBirth=" + dateOfBirth +
            ", placeOfBirth= " + placeOfBirth + ", mobilePhoneNumber= " + mobilePhoneNumber +
            ", email= " + email + ", published=" + published + "]";
  }

}
