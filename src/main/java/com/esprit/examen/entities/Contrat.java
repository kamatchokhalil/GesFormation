package com.esprit.examen.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Contrat {
    @JsonProperty("CDI")
    CDI,
    @JsonProperty("CIVP")
    CIVP,
    @JsonProperty("EXPERT")
    EXPERT,
    @JsonProperty("FREELANCE")
    FREELANCE
}
