package com.travelmaker.yeoga.model;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "ACCOUNT")
public class ACCOUNT {

  @Id
  @Column(name = "UUID", length = 35)
  private String UUID;

  @Column(name = "PASSWORD")
  private String PASSWORD;
  
  @Column(name = "PHONE")
  private String PHONE;
  
  @Column(name = "SEX")
  private String SEX;
  
  @Column(name = "NICKNAME")
  private String NICKNAME;


  public String getUuid() {
    return UUID;
  }

  public void setUuid(String UUID) {
    this.UUID = UUID;
  }


  public String getPASSWORD() {
    return PASSWORD;
  }

  public void setPASSWORD(String PASSWORD) {
    this.PASSWORD = PASSWORD;
  }


  public String getPHONE() {
    return PHONE;
  }

  public void setPHONE(String PHONE) {
    this.PHONE = PHONE;
  }


  public String getSEX() {
    return SEX;
  }

  public void setSEX(String SEX) {
    this.SEX = SEX;
  }


  public String getNICKNAME() {
    return NICKNAME;
  }

  public void setNICKNAME(String NICKNAME) {
    this.NICKNAME = NICKNAME;
  }

}
