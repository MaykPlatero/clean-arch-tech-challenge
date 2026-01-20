package br.com.fiap.clean_arch.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EProfileTest {

    @Test
    void shouldHaveCorrectEnumValues() {
        assertEquals("ADMIN", EProfile.ADMIN.toString());
        assertEquals("CLIENT", EProfile.CLIENT.toString());
        assertEquals("OWNER", EProfile.OWNER.toString());
    }

    @Test
    void shouldReturnCorrectValueOf() {
        assertEquals(EProfile.ADMIN, EProfile.valueOf("ADMIN"));
        assertEquals(EProfile.CLIENT, EProfile.valueOf("CLIENT"));
        assertEquals(EProfile.OWNER, EProfile.valueOf("OWNER"));
    }

    @Test
    void shouldHaveThreeValues() {
        EProfile[] values = EProfile.values();
        assertEquals(3, values.length);
    }
}
