package ru.skillbox.pasteboxtestwork.api.request;

import lombok.Data;

@Data
public class PasteBoxRequest {
    private String data;
    private Long expirationTimeSeconds;
    private PublicStatus publicStatus;
}
