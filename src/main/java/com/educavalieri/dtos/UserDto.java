package com.educavalieri.dtos;

import com.educavalieri.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto extends User {

    private Long id;
    private String name;
    private Integer age;

}
