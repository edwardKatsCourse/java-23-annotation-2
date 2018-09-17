package com.telran.java23.annotation.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Admin {

    private String name;
    private String password;
}
