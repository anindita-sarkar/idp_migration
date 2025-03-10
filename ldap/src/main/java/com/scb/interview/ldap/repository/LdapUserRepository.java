package com.scb.interview.ldap.repository;


import com.scb.interview.ldap.model.LdapUser;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LdapUserRepository extends LdapRepository<LdapUser> {
}
