package com.stsh.urlshorter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateLinkResponse {

    private String shortenedLink;

}
