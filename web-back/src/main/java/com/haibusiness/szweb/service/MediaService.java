package com.haibusiness.szweb.service;

import com.haibusiness.szweb.entity.Graduate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MediaService {

    Graduate saveMedia(Graduate media);


    void removeMedia(Long id);


    void removeMediasInBatch(List<Graduate> medias);

    Graduate updateMedia(Graduate media);

    Graduate getMediaById(Long id);


    List<Graduate> listMedias();


    Page<Graduate> listMediasByTitleLike(String title, Pageable pageable);
}
