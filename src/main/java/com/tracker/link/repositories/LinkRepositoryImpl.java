package com.tracker.link.repositories;

import com.tracker.link.dto.LinkDTO;
import com.tracker.link.exceptionhandler.LinkInvalidURL;
import com.tracker.link.exceptionhandler.LinkNotFound;
import com.tracker.link.services.URLConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LinkRepositoryImpl implements LinkRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(URLConverterService.class);
    private Long idCounter;
    private HashMap<Long, LinkDTO> links;

    @Override
    public LinkDTO selectLink(Long idLink) throws LinkNotFound {
        if (this.links != null) {
            LinkDTO linkDTO = null;
            for (Map.Entry<Long, LinkDTO> link : this.links.entrySet()) {
                if (idLink.equals(link.getKey())) {
                    linkDTO = new LinkDTO();
                    linkDTO.setUrl(link.getValue().getUrl());
                    linkDTO.setPassword(link.getValue().getPassword());
                    linkDTO.setRedirections(link.getValue().getRedirections());
                    break;
                }
            }
            if (linkDTO == null) {
                throw new LinkNotFound(idLink.toString());
            } else {
                return linkDTO;
            }
        } else {
            throw new LinkNotFound(idLink.toString());
        }
    }

    @Override
    public Long insertLink(LinkDTO link,Long idLink) {
        if (this.links == null) {
            this.links = new HashMap<>();
        }
        if (idLink == 0) {
            this.idCounter = 0L;
            idLink = incrementID();
        }
        link.setRedirections(0);
        this.links.put(idLink, link);
        return this.idCounter;
    }

    @Override
    public void removeLink(Long idLink) throws LinkNotFound {
        for (Map.Entry<Long, LinkDTO> link : this.links.entrySet()) {
            if (idLink == link.getKey()) {
                this.links.remove(idLink);
                break;
            }
        }
    }

    @Override
    public void updateLink(Long idLink, LinkDTO link) throws LinkNotFound {
        this.links.put(idLink, link);
    }


   public Long incrementID() {
        if (this.links == null) {
            this.links = new HashMap<>();
            this.idCounter = 19901005L;
        }
        Long id = ++this.idCounter;
        LOGGER.info("Incrementing ID: {}", id);
        return id;
    }

    public void saveUrl(String key, String longUrl) {
        LOGGER.info("Saving: {} at {}", longUrl, key);
    }

    public String getUrl(Long id) throws Exception {
        LOGGER.info("Retrieving at {}", id);
        return this.links.get(id).getUrl();
    }
}
