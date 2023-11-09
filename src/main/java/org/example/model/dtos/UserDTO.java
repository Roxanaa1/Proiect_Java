package org.example.model;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
@Getter
@AllArgsConstructor
@ToString
@Setter
public class UserDto
{
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private int number;
    private int age;

}
