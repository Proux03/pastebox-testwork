package ru.skillbox.pasteboxtestwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.pasteboxtestwork.model.PasteboxEntity;

import java.util.List;

public interface PasteboxRepositoryDB extends JpaRepository<PasteboxEntity, Long> {

    PasteboxEntity findByHash(String hash);

}
