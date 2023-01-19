package ru.netology.i18n;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    private LocalizationService service;

    @BeforeEach
    void setUp() {
        this.service = new LocalizationServiceImpl();
    }

    @Test
    void locale() {
        String russian = this.service.locale(Country.RUSSIA);
        assertEquals("Добро пожаловать", russian);

        String usa = this.service.locale(Country.USA);
        assertEquals("Welcome", usa);

        String otherCountry = service.locale(Country.BRAZIL);
        assertEquals("Welcome", otherCountry);
    }
}