package ru.skillbox.pasteboxtestwork.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteboxUrlResponse {

    private final String url;
}
