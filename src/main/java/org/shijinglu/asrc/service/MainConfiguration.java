package org.shijinglu.asrc.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class MainConfiguration extends Configuration {

    @NotEmpty private String template;

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty @NotEmpty private String formulaDir;

    public String getFormulaDir() {
        return formulaDir;
    }
}
