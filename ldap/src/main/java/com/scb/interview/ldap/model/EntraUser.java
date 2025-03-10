package com.scb.interview.ldap.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "displayName", "userPrincipalName", "initialPassword", "accountEnabled",
        "givenName", "surname", "jobTitle", "department", "usageLocation", "streetAddress",
         "state", "country", "physicalDeliveryOfficeName","city","postalCode", "telephoneNumber", "mobile"
})
public class EntraUser {

    @JsonProperty("Name [displayName] Required")
    private String displayName;

    @JsonProperty("User name [userPrincipalName] Required")
    private String userPrincipalName;

    @JsonProperty("Initial password [passwordProfile] Required")
    private String initialPassword;

    @JsonProperty("Block sign in (Yes/No) [accountEnabled] Required")
    private String accountEnabled;



    @JsonProperty("First name [givenName]")
    private String givenName;

    @JsonProperty("Last name [surname]")
    private String surname;

    @JsonProperty("Job title [jobTitle]")
    private String jobTitle;

    @JsonProperty("Department [department]")
    private String department;

    @JsonProperty("Usage location [usageLocation]")
    private String usageLocation;

    @JsonProperty("Street address [streetAddress]")
    private String streetAddress;

    @JsonProperty("State or province [state]")
    private String state;

    @JsonProperty("Country or region [country]")
    private String country;

    @JsonProperty("Office [physicalDeliveryOfficeName]")
    private String physicalDeliveryOfficeName;

    @JsonProperty("City [city]")
    private String city;
    @JsonProperty("ZIP or postal code [postalCode]")
    private String postalCode;

    @JsonProperty("Office phone [telephoneNumber]")
    private String telephoneNumber;

    @JsonProperty("Mobile phone [mobile]")
    private String mobile;

    public EntraUser() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getInitialPassword() {
        return initialPassword;
    }

    public void setInitialPassword(String initialPassword) {
        this.initialPassword = initialPassword;
    }


    public String getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(boolean accountEnabled) {
        this.accountEnabled = accountEnabled?"Yes":"No";
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsageLocation() {
        return usageLocation;
    }

    public void setUsageLocation(String usageLocation) {
        this.usageLocation = usageLocation;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
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

    public String getPhysicalDeliveryOfficeName() {
        return physicalDeliveryOfficeName;
    }

    public void setPhysicalDeliveryOfficeName(String physicalDeliveryOfficeName) {
        this.physicalDeliveryOfficeName = physicalDeliveryOfficeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
