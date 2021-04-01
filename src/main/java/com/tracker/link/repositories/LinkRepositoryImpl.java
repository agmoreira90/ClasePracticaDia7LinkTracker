package com.tracker.link.repositories;

import com.tracker.link.dto.LinkDTO;
import com.tracker.link.exceptionhandler.LinkInvalidURL;
import com.tracker.link.exceptionhandler.LinkNotFound;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LinkRepositoryImpl implements LinkRepository {

    private Integer idCounter;
    private HashMap<Integer, LinkDTO> links;

    @Override
    public LinkDTO selectLink(Integer idLink) throws LinkNotFound {
        if (this.links != null) {
            LinkDTO linkDTO = null;
            for (Map.Entry<Integer, LinkDTO> link : this.links.entrySet()) {
                if (idLink == link.getKey()) {
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
    public Integer insertLink(LinkDTO link) {
        if (this.links == null) {
            this.links = new HashMap<>();
            this.idCounter = 0;
        }
        link.setRedirections(0);
        this.links.put(++this.idCounter, link);
        return this.idCounter;
    }

    @Override
    public void removeLink(Integer idLink) throws LinkNotFound {
        for (Map.Entry<Integer, LinkDTO> link : this.links.entrySet()) {
            if (idLink == link.getKey()) {
                this.links.remove(idLink);
                break;
            }
        }
    }

    @Override
    public void updateLink(Integer idLink, LinkDTO link) throws LinkNotFound {
        this.links.put(idLink, link);
    }
}
