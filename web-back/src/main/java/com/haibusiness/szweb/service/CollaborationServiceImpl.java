package com.haibusiness.szweb.service;

import com.haibusiness.szweb.dao.CollaborationDao;
import com.haibusiness.szweb.entity.Collaboration;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CollaborationServiceImpl implements CollaborationService {

    private CollaborationDao collaborationDao;
    @Override
    public Collaboration saveCollaboration(Collaboration collaboration) {

        return collaborationDao.save(collaboration);
    }

    @Override
    public void removeCollaboration(Long id) {

        collaborationDao.deleteById(id);
    }

    @Override
    public void removeCollaborationsInBatch(List<Collaboration> collaborations) {

        collaborationDao.deleteInBatch(collaborations);
    }

    @Override
    public Collaboration updateCollaboration(Collaboration collaboration) {

        return collaborationDao.save(collaboration);
    }

    @Override
    public Collaboration getCollaborationById(Long id) {

        return collaborationDao.getOne(id);
    }

    @Override
    public List<Collaboration> listCollaborations() {

        return collaborationDao.findAll();
    }
    @Override
    public Page<Collaboration> listCollaborationsByTypeAndTitleLike(String type, String title, Pageable pageable) {
        title = "%" + title + "%";
        return collaborationDao.findByTypeAndTitleLikeOrderByUpdateTimeDesc(type, title, pageable);
    }


}
