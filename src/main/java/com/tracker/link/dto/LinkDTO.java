package com.tracker.link.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {
    private String url;
    private String password;
    private Integer redirections;

    public LinkDTO(String url) {
        this.url = url;
        this.password = "";
        this.redirections = 0;
    }
}
