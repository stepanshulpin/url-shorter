package com.stsh.urlshorter.model;

import lombok.Value;

@Value
public class Link {
    String originalLink;
    String key;
}
