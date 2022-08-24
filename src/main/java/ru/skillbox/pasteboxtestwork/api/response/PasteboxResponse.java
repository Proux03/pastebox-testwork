package ru.skillbox.pasteboxtestwork.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.skillbox.pasteboxtestwork.api.request.PublicStatus;

@Data
@RequiredArgsConstructor
public class PasteboxResponse {

    private final String data;

    private final boolean isPublic;
}
