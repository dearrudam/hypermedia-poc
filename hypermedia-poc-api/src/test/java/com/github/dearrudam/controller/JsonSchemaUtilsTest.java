package com.github.dearrudam.controller;

import com.github.dearrudam.entity.PlanoDeTrabalho;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;

import static org.junit.jupiter.api.Assertions.*;

class JsonSchemaUtilsTest {

    JsonSchemaUtils instance;

    @BeforeEach
    public void before(){
        this.instance=new JsonSchemaUtils();
    }

    @Test
    public void test_(){

        JsonObject schema = instance.generateSchema(PlanoDeTrabalho.class);

        assertNotNull(schema);

        System.out.println(schema);
    }

}