package ru.netology.sender;

import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {

    private GeoService geoService;
    private LocalizationService localizationService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.geoService = Mockito.mock(GeoServiceImpl.class);
        this.localizationService = Mockito.mock(LocalizationServiceImpl.class);
    }

    @org.junit.jupiter.api.Test
    void sendRussianMessage() {

        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Mockito.when(geoService.byIp(Mockito.startsWith("172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.35.140");
        String message = messageSender.send(headers);

        assertEquals("Добро пожаловать", message);
    }

    @org.junit.jupiter.api.Test
    void sendEnglishMessage() {

        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Mockito.when(geoService.byIp(Mockito.startsWith("96.")))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.45.190.80");
        String message = messageSender.send(headers);

        assertEquals("Welcome", message);
    }
}