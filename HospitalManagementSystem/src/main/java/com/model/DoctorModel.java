package com.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorModel {
    private int id;
    private String name;
    private String specialization;
    private int experience;
    private long mobile;
    private String password;

}
