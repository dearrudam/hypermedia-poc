package com.github.dearrudam.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.imifou.jsonschema.module.addon.AddonModule;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.module.javax.validation.JavaxValidationModule;
import com.github.victools.jsonschema.module.javax.validation.JavaxValidationOption;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;

@ApplicationScoped
public class JsonSchemaUtils {

    private ObjectMapper objectMapper;
    private SchemaGeneratorConfigBuilder configBuilder;
    private SchemaGeneratorConfig config;
    private SchemaGenerator schemaGenerator;

    public JsonSchemaUtils() {
        this.init();
    }

    public JsonObject generateSchema(Class<?> javaClass) {
        JsonNode jsonSchema = getSchemaGenerator().generateSchema(javaClass);
        return Json.createReader(new StringReader(jsonSchema.toString())).readObject();
    }

    private SchemaGenerator getSchemaGenerator() {
        return schemaGenerator;
    }

    private void init() {
        this.objectMapper = new ObjectMapper();
        this.configBuilder = new SchemaGeneratorConfigBuilder(objectMapper, OptionPreset.PLAIN_JSON)
                .with(new AddonModule())
                .with(new JavaxValidationModule(JavaxValidationOption.INCLUDE_PATTERN_EXPRESSIONS,
                        JavaxValidationOption.NOT_NULLABLE_FIELD_IS_REQUIRED,
                        JavaxValidationOption.NOT_NULLABLE_METHOD_IS_REQUIRED))
        ;
        this.config = configBuilder.build();
        this.schemaGenerator = new SchemaGenerator(config);
    }
}
