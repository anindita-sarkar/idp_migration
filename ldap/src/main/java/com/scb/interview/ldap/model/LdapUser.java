package com.scb.interview.ldap.model;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Attribute;

import javax.naming.Name;

@Entry(base = "ou=users", objectClasses = { "inetOrgPerson", "organizationalPerson", "person" })
public class LdapUser {

    @Id
    private Name id;

    @Attribute(name = "uid")
    private String uid;

    @Attribute(name = "cn")
    private String cn;

    @Attribute(name = "mail")
    private String mail;

    @Attribute(name = "givenName")

    private String givenName;
    @Attribute(name = "sn")
    private String sn;
    @Attribute(name = "title")
    private String title;
    @Attribute(name = "department")
    private String department;
    @Attribute(name = "l")
    private String city;
    @Attribute(name = "st")
    private String state;
    @Attribute(name = "c")
    private String country;
    @Attribute(name = "postalCode")
    private String postalCode;
    @Attribute(name = "telephoneNumber")
    private String telephoneNumber;
    @Attribute(name = "mobile")
    private String mobile;

    public LdapUser() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
