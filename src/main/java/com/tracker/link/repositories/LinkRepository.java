package com.tracker.link.repositories;

import com.tracker.link.dto.LinkDTO;
import com.tracker.link.exceptionhandler.LinkNotFound;

import java.util.HashMap;

public interface LinkRepository {
    LinkDTO selectLink(Integer idLink) throws LinkNotFound;

    Integer insertLink(LinkDTO link);

    void removeLink(Integer idLink) throws LinkNotFound;

    void updateLink(Integer idLink, LinkDTO link) throws LinkNotFound;
}
