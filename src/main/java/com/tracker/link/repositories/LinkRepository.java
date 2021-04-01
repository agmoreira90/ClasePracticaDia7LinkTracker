package com.tracker.link.repositories;

import com.tracker.link.dto.LinkDTO;
import com.tracker.link.exceptionhandler.LinkNotFound;

import java.util.HashMap;

public interface LinkRepository {
    LinkDTO selectLink(Long idLink) throws LinkNotFound;

    Long insertLink(LinkDTO link);

    void removeLink(Long idLink) throws LinkNotFound;

    void updateLink(Long idLink, LinkDTO link) throws LinkNotFound;

    Long incrementID();

    void saveUrl(String key, String longUrl);

    String getUrl(Long id) throws Exception;
}
