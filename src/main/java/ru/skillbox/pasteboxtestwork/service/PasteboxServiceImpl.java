package ru.skillbox.pasteboxtestwork.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.skillbox.pasteboxtestwork.api.request.PasteboxRequest;
import ru.skillbox.pasteboxtestwork.api.request.PublicStatus;
import ru.skillbox.pasteboxtestwork.api.response.PasteboxResponse;
import ru.skillbox.pasteboxtestwork.api.response.PasteboxUrlResponse;
import ru.skillbox.pasteboxtestwork.repository.PasteboxEntity;
import ru.skillbox.pasteboxtestwork.repository.PasteboxRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@ConfigurationProperties(prefix = "app")
@Slf4j
public class PasteboxServiceImpl implements PasteboxService {

    private String host = "http://pastebox.skillbox.ru";
    private int publicListSize = 10;
    private final PasteboxRepository pasteboxRepository;
    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public PasteboxResponse getByHash(String hash) {

        PasteboxEntity pasteboxEntity = pasteboxRepository.getByHash(hash);
        log.info("get paste with hash " + hash);
        return new PasteboxResponse(pasteboxEntity.getData(), pasteboxEntity.isPublic());
    }

    @Override
    public List<PasteboxResponse> getFirstPublicPasteboxes() {

        List<PasteboxEntity> list = pasteboxRepository.getListOfPublicAndAlive(publicListSize);
        log.info("get list of paste count " + list.size());
        return list.stream().map(pasteboxEntity ->
                new PasteboxResponse(pasteboxEntity.getData(), pasteboxEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteboxUrlResponse create(PasteboxRequest request) {
        int hash = generateId();
        PasteboxEntity pasteboxEntity = new PasteboxEntity();
        pasteboxEntity.setData(request.getData());
        pasteboxEntity.setId(hash);
        pasteboxEntity.setHash(Integer.toHexString(hash));
        pasteboxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pasteboxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        pasteboxRepository.add(pasteboxEntity);
        log.info("paste with hash " + hash + " added");
        return new PasteboxUrlResponse(host + "/" + pasteboxEntity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }

}
