package com.tracker.link.services;

import com.tracker.link.dto.LinkDTO;
import com.tracker.link.exceptionhandler.LinkInvalidURL;
import com.tracker.link.exceptionhandler.LinkNotFound;
import com.tracker.link.repositories.LinkRepository;
import org.springframework.stereotype.Service;

@Service
public interface LinkService {

    Long createLink(LinkDTO linkDTO) throws LinkInvalidURL;

    String getRedirection(Long linkId) throws LinkNotFound;

    Integer getStats(Long linkId) throws LinkNotFound;

    void invalidateLink(Long linkId) throws LinkNotFound;

}
