package com.baltinfo.radius.db.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 * DTO для работы с датами. Если дата не задана, то возвращает null
 * </p>
 *
 * @author Lapenok Igor
 * @since 16.08.2018
 */
public class DateDto {
    private final Date date;

    public DateDto(Date date) {
        this.date = date;
    }

    public DateDto(Date date, Integer hour, Integer minute) {
        if (date == null || hour == null || minute == null) {
            this.date = date;
        } else {
            Instant instant = date.toInstant();
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime localTime = LocalTime.of(hour, minute);
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
            this.date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
    }

    public DateDto(Instant datetime) {
        if (datetime == null) {
            this.date = null;
        } else {
            this.date = Date.from(datetime);
        }
    }

    public Date getDate() {
        return this.date;
    }
}
