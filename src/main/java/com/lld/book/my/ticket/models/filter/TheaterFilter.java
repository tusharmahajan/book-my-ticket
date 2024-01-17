package com.lld.book.my.ticket.models.filter;

import com.lld.book.my.ticket.models.MovieTheater;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TheaterFilter implements MovieFilter {

    private String name;

    @Override
    public boolean filter(MovieTheater movieTheater) {
        return movieTheater.getName().equals(name);
    }
}
