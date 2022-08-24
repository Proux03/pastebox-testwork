package ru.skillbox.pasteboxtestwork.service;

import ru.skillbox.pasteboxtestwork.api.request.PasteboxRequest;
import ru.skillbox.pasteboxtestwork.api.response.PasteboxResponse;
import ru.skillbox.pasteboxtestwork.api.response.PasteboxUrlResponse;

import java.util.List;

public interface PasteboxService {

    PasteboxResponse getByHash(String hash);

    List<PasteboxResponse> getFirstPublicPasteboxes();

    PasteboxUrlResponse create(PasteboxRequest request);
}
