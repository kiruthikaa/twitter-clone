package com.intuit.craftdemotwitter.utils.mapper;

public interface Mapper<S,T> {
    T transform(S source);

    S transformBack(T source);
}
