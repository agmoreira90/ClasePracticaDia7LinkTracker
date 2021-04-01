package com.tracker.link.services;

import com.tracker.link.dto.LinkDTO;
import com.tracker.link.exceptionhandler.LinkInvalidURL;
import com.tracker.link.exceptionhandler.LinkNotFound;
import com.tracker.link.repositories.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public Long createLink(LinkDTO linkDTO) throws LinkInvalidURL {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(linkDTO.getUrl());
        if (matcher.matches()) {
            return this.linkRepository.insertLink(linkDTO);
        } else {
            throw new LinkInvalidURL(linkDTO.getUrl());
        }
    }

    @Override
    public String getRedirection(Long linkId) throws LinkNotFound {
        LinkDTO linkDTO = this.linkRepository.selectLink(linkId);
        linkDTO.setRedirections(linkDTO.getRedirections() + 1);
        this.linkRepository.updateLink(linkId, linkDTO);
        return linkDTO.getUrl();
    }

    @Override
    public Integer getStats(Long linkId) throws LinkNotFound {
        LinkDTO linkDTO = this.linkRepository.selectLink(linkId);
        return linkDTO.getRedirections();
    }

    @Override
    public void invalidateLink(Long linkId) throws LinkNotFound {
        this.linkRepository.removeLink(linkId);
    }


}
