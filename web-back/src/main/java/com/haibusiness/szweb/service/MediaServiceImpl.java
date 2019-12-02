package com.haibusiness.szweb.service;

import com.haibusiness.szweb.dao.MediaDao;
import com.haibusiness.szweb.entity.Graduate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MediaServiceImpl implements MediaService {

    private MediaDao mediaDao;
    @Override
    public Graduate saveMedia(Graduate media) {

        return mediaDao.save(media);
    }

    @Override
    public void removeMedia(Long id) {

        mediaDao.deleteById(id);
    }

    @Override
    public void removeMediasInBatch(List<Graduate> medias) {

        mediaDao.deleteInBatch(medias);
    }

    @Override
    public Graduate updateMedia(Graduate media) {

        return mediaDao.save(media);
    }

    @Override
    public Graduate getMediaById(Long id) {

        return mediaDao.getOne(id);
    }

    @Override
    public List<Graduate> listMedias() {

        return mediaDao.findAll();
    }

    @Override
    public Page<Graduate> listMediasByTitleLike(String title, Pageable pageable) {

        title = "%" + title + "%";
        Page<Graduate> medias = mediaDao.findByTitleLikeOrderByUpdateTimeDesc(title, pageable);
        return medias;
    }
}
