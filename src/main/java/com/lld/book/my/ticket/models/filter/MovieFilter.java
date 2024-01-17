package com.lld.book.my.ticket.models.filter;

import com.lld.book.my.ticket.models.MovieTheater;

public interface MovieFilter {

    boolean filter(MovieTheater movieTheater);
}
