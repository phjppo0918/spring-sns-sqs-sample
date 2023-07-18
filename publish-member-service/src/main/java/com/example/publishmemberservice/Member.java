package com.example.publishmemberservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member {
    @Id @GeneratedValue
    Long id;
    String name;
    String password;

    public Member(final String name, final String password) {
        this.name = name;
        this.password = password;
    }
}
