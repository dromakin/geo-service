package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {

    private GeoService geoService;

    @BeforeEach
    void setUp() {
        this.geoService = new GeoServiceImpl();
    }

    @Test
    void byIp() {
        // russian
        Location location = this.geoService.byIp("172.0.32.11");
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
        // usa
        location = this.geoService.byIp("96.44.183.149");
        Assertions.assertEquals(Country.USA, location.getCountry());
    }

    @Test
    void byCoordinates() {
        RuntimeException thrown = Assertions.assertThrows(
                RuntimeException.class,
                () -> this.geoService.byCoordinates(1.0, 10.0)
        );

        Assertions.assertTrue(thrown.getMessage().contentEquals("Not implemented"));
    }
}