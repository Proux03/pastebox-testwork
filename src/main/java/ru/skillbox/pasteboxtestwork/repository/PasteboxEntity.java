package ru.skillbox.pasteboxtestwork.repository;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PasteboxEntity {

    private int id;

    private String data;

    private String hash;

    LocalDateTime lifetime;

    private boolean isPublic;
}
