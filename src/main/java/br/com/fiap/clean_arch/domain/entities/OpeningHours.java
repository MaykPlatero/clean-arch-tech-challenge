package br.com.fiap.clean_arch.domain.entities;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
public class OpeningHours {
    Long id;
    Long restaurantId;
    DayOfWeek dayOfWeek;
    LocalTime openTime;
    LocalTime closeTime;

    public OpeningHours(Long id, Long restaurantId, DayOfWeek dayOfWeek, LocalTime openTime, LocalTime closeTime) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
