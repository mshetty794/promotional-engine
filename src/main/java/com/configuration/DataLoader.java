package com.configuration;

import com.dto.ProductCatalogue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class DataLoader {

    public static Configuration loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(new File("src/main/resources/configuration.yaml"), Configuration.class);
    }

    public static ProductCatalogue loadProductCatalogueData() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(new File("src/main/resources/configuration.yaml"), ProductCatalogue.class);
    }
}
