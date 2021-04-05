package com.tracker.link.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {
    private String url;
    private String urlShort;
    private String urlLocal;
    private String uniqueId;
    private String password;
    private Integer redirections;


    public LinkDTO(String url) {
        this.url = url;
        this.urlShort = "";
        this.urlLocal = "";
        this.uniqueId = "";
        this.password = "";
        this.redirections = 0;
    }

    public LinkDTO(String url, String urlShort, String urlLocal,String uniqueId) {
        this.url = url;
        this.urlShort = urlShort;
        this.urlLocal = urlLocal;
        this.uniqueId = uniqueId;
        this.password = "";
        this.redirections = 0;
    }
}
