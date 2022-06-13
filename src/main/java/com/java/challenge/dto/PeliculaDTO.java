package com.java.challenge.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PeliculaDTO {
    private Integer id;
    private String imagen;
    private String titulo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCreacion;
}