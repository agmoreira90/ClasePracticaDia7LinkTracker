package com.tracker.link.services;

import com.tracker.link.dto.LinkDTO;
import com.tracker.link.exceptionhandler.LinkInvalidURL;
import com.tracker.link.exceptionhandler.LinkNotFound;
import com.tracker.link.repositories.LinkRepository;
import org.springframework.stereotype.Service;

@Service
public interface LinkService {

    Integer createLink(LinkDTO linkDTO) throws LinkInvalidURL;

    String getRedirection(Integer linkId) throws LinkNotFound;

    Integer getStats(Integer linkId) throws LinkNotFound;

    void invalidateLink(Integer linkId) throws LinkNotFound;

}
