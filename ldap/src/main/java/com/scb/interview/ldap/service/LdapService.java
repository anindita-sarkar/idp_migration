package com.scb.interview.ldap.service;


import com.scb.interview.ldap.model.LdapUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

@Service
public class LdapService {

    @Autowired
    private LdapTemplate ldapTemplate;


    public List<LdapUser> getAllUsers() {
        LdapQuery query = LdapQueryBuilder.query()
                .where("objectClass").is("person");
        return ldapTemplate.search(query, (AttributesMapper<LdapUser>) this::mapToLdapUser);
    }

    private LdapUser mapToLdapUser(Attributes attributes) throws NamingException {
        String uid = getAttribute(attributes, "uid");
        String cn = getAttribute(attributes, "cn"); // Common name
        String givenName = getAttribute(attributes, "givenName");
        String sn = getAttribute(attributes, "sn"); // Surname
        String mail = getAttribute(attributes, "mail");
        String title = getAttribute(attributes, "title");
        String department = getAttribute(attributes, "department");
        String city = getAttribute(attributes, "l");
        String state = getAttribute(attributes, "st");
        String country = getAttribute(attributes, "c");
        String postalCode = getAttribute(attributes, "postalCode");
        String telephoneNumber = getAttribute(attributes, "telephoneNumber");
        String mobile = getAttribute(attributes, "mobile");
        LdapUser ldapUser = new LdapUser();
        ldapUser.setUid(uid);
        ldapUser.setCn(cn);
        ldapUser.setGivenName(givenName);
        ldapUser.setSn(sn);
        ldapUser.setMail(mail);
        ldapUser.setTitle(title);
        ldapUser.setDepartment(department);
        ldapUser.setCity(city);
        ldapUser.setState(state);
        ldapUser.setCountry(country);
        ldapUser.setPostalCode(postalCode);
        ldapUser.setTelephoneNumber(telephoneNumber);
        ldapUser.setMobile(mobile);
        return ldapUser;
    }

    private String getAttribute(Attributes attributes, String attributeName) throws NamingException {
        return attributes.get(attributeName) != null ? attributes.get(attributeName).get().toString() : "";
    }
}
