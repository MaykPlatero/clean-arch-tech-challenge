package br.com.fiap.clean_arch.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EProfileTest {

    @Test
    void shouldHaveCorrectEnumValues() {
        assertEquals("admin", EProfile.admin.toString());
        assertEquals("client", EProfile.client.toString());
        assertEquals("owner", EProfile.owner.toString());
    }

    @Test
    void shouldReturnCorrectValueOf() {
        assertEquals(EProfile.admin, EProfile.valueOf("admin"));
        assertEquals(EProfile.client, EProfile.valueOf("client"));
        assertEquals(EProfile.owner, EProfile.valueOf("owner"));
    }

    @Test
    void shouldHaveThreeValues() {
        EProfile[] values = EProfile.values();
        assertEquals(3, values.length);
    }
}
