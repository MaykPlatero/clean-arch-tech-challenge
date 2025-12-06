package br.com.fiap.clean_arch.domain.entities;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
public class OpeningHours {
    DayOfWeek dayOfWeek;
    LocalTime openTime;
    LocalTime closeTime;

    public OpeningHours(DayOfWeek dayOfWeek, LocalTime openTime, LocalTime closeTime) {
        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
