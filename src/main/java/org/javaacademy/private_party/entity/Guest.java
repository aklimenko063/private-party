package org.javaacademy.private_party.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Guest {
    private final String name;
    private final String email;
    private final String phone;
}
