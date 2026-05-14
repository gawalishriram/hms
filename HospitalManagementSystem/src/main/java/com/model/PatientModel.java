package com.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientModel {
	private long id;
    private String name;
    private int age;
    private String gender;
    private long mobile;
    private String disease;
    private String password;
    private String email;
}
