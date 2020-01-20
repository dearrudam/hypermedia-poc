package com.github.dearrudam.entity;

import com.github.imifou.jsonschema.module.addon.annotation.JSData;
import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "planotrabalhos")
public class PlanoDeTrabalho extends PanacheEntity {

    @JsonSchema(ignore = true)
    public Long getId(){
        return this.id;
    }

    @JsonSchema( metadata = {@JSData(key = "teste",value = "teste")})
    @NotEmpty
    private String setor;

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

}
