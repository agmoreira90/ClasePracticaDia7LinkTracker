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
    public Long insertLink(LinkDTO link) {
        if (this.links == null) {
            this.links = new HashMap<>();
            this.idCounter = 0L;
        }
        link.setRedirections(0);
        this.links.put(++this.idCounter, link);
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


//    //metodos urlShortener
//    public URLRepository(Jedis jedis, String idKey, String urlKey) {
//        this.jedis = jedis;
//        this.idKey = idKey;
//        this.urlKey = urlKey;
//    }

    public Long incrementID() {
        if (this.links == null) {
            this.links = new HashMap<>();
            this.idCounter = 0L;
        }
        Long id = ++this.idCounter;
        LOGGER.info("Incrementing ID: {}", id);
        return id;
    }

    public void saveUrl(String key, String longUrl) {
        LOGGER.info("Saving: {} at {}", longUrl, key);
//        jedis.hset(urlKey, key, longUrl);
    }

    public String getUrl(Long id) throws Exception {
        LOGGER.info("Retrieving at {}", id);
//        String url = jedis.hget(urlKey, "url:"+id);
//        if (url == null) {
//            throw new Exception("URL at key" + id + " does not exist");
//        }
//        return jedis.hget(urlKey, "url:"+id);
        return "";
    }
}
