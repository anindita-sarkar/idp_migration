package com.scb.interview.ldap.service;


import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.scb.interview.ldap.model.EntraUser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
@Service
public class CsvExporter {

    private static final String CSV_VERSION = "v1.0";

    public void writeUsersToCsv(List<EntraUser> users, OutputStream outputStream) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(EntraUser.class).withHeader();

        try (Writer writer = new OutputStreamWriter(outputStream)) {
            // Write version row

            writer.write("version:" + CSV_VERSION + "\n");

            // Write headers and data using Jackson
            csvMapper.writer(schema).writeValues(writer).writeAll(users);
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException("Failed to write CSV", e);
        }
    }
}