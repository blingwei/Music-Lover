package com.blingwei.musicService.enums;

import org.elasticsearch.common.util.set.Sets;

import java.util.Set;

public interface BaseEnum<E extends Enum<E>, T> {
    T getValue();

    String getMessage();

}
