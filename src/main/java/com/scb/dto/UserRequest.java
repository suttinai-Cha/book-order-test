package com.scb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class UserRequest {

    @JsonProperty("username")
    @NotBlank @NotNull
    private String username;

    @JsonProperty("password")
    @NotBlank @NotNull
    private String password;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy",timezone = "Asia/Bangkok")
    @Temporal(TemporalType.DATE)
    @NotBlank @NotNull
    private Date birthDate;
}
