package ru.skillbox.pasteboxtestwork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.pasteboxtestwork.api.request.PasteboxRequest;
import ru.skillbox.pasteboxtestwork.api.response.PasteboxResponse;
import ru.skillbox.pasteboxtestwork.api.response.PasteboxUrlResponse;
import ru.skillbox.pasteboxtestwork.service.PasteboxService;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class PasteboxController {

    private final PasteboxService pasteboxService;

    @GetMapping("/")
    public Collection<PasteboxResponse> getPublicPasteList() {
        return pasteboxService.getFirstPublicPasteboxes();
    }

    @GetMapping("/{hash}")
    public PasteboxResponse getByHash(@PathVariable String hash) {
        return pasteboxService.getByHash(hash);
    }

    @PostMapping("/")
    public PasteboxUrlResponse add(@RequestBody PasteboxRequest request) {
        return pasteboxService.create(request);
    }

}
