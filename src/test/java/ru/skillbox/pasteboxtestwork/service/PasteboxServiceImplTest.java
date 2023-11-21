package ru.skillbox.pasteboxtestwork.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.skillbox.pasteboxtestwork.api.response.PasteboxResponse;
import ru.skillbox.pasteboxtestwork.exception.NotFoundEntityException;
import ru.skillbox.pasteboxtestwork.model.PasteboxEntity;
import ru.skillbox.pasteboxtestwork.repository.PasteboxRepositoryDB;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class PasteboxServiceImplTest {

    @Autowired
    private PasteboxService pasteboxService;

    @MockBean
    private PasteboxRepositoryDB pasteboxRepository;

    @Test
    void getByHashWhenNotExists() {
        when(pasteboxRepository.findByHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class, () -> pasteboxService.getByHash("eurgfeurn"));
    }

    @Test
    void getByHashWhenExists() {
        PasteboxEntity pasteboxEntity = new PasteboxEntity();
        pasteboxEntity.setHash("1");
        pasteboxEntity.setData("paste");
        pasteboxEntity.setPublic(true);

        when(pasteboxRepository.findByHash("1")).thenReturn(pasteboxEntity);

        PasteboxResponse expected = new PasteboxResponse("paste", true);
        PasteboxResponse actual = pasteboxService.getByHash("1");

        assertEquals(expected, actual);

    }

    @Test
    void getFirstPublicPasteboxes() {
    }

    @Test
    void create() {
    }
}