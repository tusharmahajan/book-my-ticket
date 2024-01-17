package com.lld.book.my.ticket.models.filter;

import com.lld.book.my.ticket.models.MovieTheater;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CityMovieFilter implements MovieFilter {

    private String city;

    @Override
    public boolean filter(MovieTheater movieTheater) {
        return movieTheater.getCity().equals(city);
    }
}
