package com.scb.interview.ldap.rest;

import com.scb.interview.ldap.model.EntraUser;
import com.scb.interview.ldap.model.LdapToEntraAdapter;
import com.scb.interview.ldap.model.LdapUser;
import com.scb.interview.ldap.service.CsvExporter;
import com.scb.interview.ldap.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LdapController {

    @Autowired
    private LdapService ldapService;
    @Autowired
    private CsvExporter csvExporter;
    @GetMapping("/download")
    public ResponseEntity<StreamingResponseBody> downloadCsv() {
        List<LdapUser> ldapUsers = ldapService.getAllUsers();

        // Convert using Adapter
        List<EntraUser> entraUsers = ldapUsers.stream()
                .map(LdapToEntraAdapter::adapt)
                .collect(Collectors.toList());

        StreamingResponseBody stream = outputStream -> {
            csvExporter.writeUsersToCsv(entraUsers, outputStream);
        };

        // Set headers for CSV download
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"users.csv\"");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(stream, headers, HttpStatus.OK);


    }

    @GetMapping("/users")
    public List<LdapUser> getUsers() {
        return ldapService.getAllUsers();
    }

//    @GetMapping("/export")
//    public ResponseEntity<StreamingResponseBody> export() {
//        List<User> users= ldapService.getAllUsers();
//        CsvMapper csvMapper = new CsvMapper();
//        CsvSchema schema = csvMapper.schemaFor(User.class).withHeader();
//
//        StreamingResponseBody responseBody = outputStream -> {
//            try (SequenceWriter writer = csvMapper.writer(schema).writeValues(outputStream)) {
//                for (User temp : users) {
//                    writer.write(temp);
//                }
//            }
//        };
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Disposition", "attachment; filename=users.csv");
//        headers.set("Content-Type", "text/csv");
//
//        return ResponseEntity.ok().headers(headers).body(responseBody);
//    }
}

