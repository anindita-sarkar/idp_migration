package com.scb.interview.ldap.model;

public class LdapToEntraAdapter {

    public static EntraUser adapt(LdapUser ldapUser) {
        EntraUser entraUser= new EntraUser();
        entraUser.setDisplayName(ldapUser.getCn());
        entraUser.setUserPrincipalName(ldapUser.getUid()+"@cyberakahotmail.onmicrosoft.com");
        entraUser.setInitialPassword("Temp@123"); // Default password
        entraUser.setAccountEnabled(true); // Account enabled
        entraUser.setGivenName(ldapUser.getGivenName());
        entraUser.setSurname(ldapUser.getSn());
        entraUser.setJobTitle(ldapUser.getTitle());
        entraUser.setDepartment(ldapUser.getDepartment());
        entraUser.setUsageLocation(ldapUser.getCountry());
        entraUser.setCity(ldapUser.getCity());
        entraUser.setState(ldapUser.getState());
        entraUser.setCountry(ldapUser.getCountry());
        entraUser.setPostalCode(ldapUser.getPostalCode());
        entraUser.setTelephoneNumber(ldapUser.getTelephoneNumber());
        entraUser.setMobile(ldapUser.getMobile());
        return entraUser;
    }
}