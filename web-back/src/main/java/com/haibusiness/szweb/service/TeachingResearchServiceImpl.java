package com.haibusiness.szweb.service;

import com.haibusiness.szweb.dao.TeachingResearchDao;
import com.haibusiness.szweb.entity.TeachingResearch;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor

public class TeachingResearchServiceImpl  implements TeachingResearchService {


    private TeachingResearchDao teachingResearchDao;
    @Override
    public TeachingResearch saveTeachingResearch(TeachingResearch teachingResearch) {
        return teachingResearchDao.save(teachingResearch);
    }

    @Override
    public void removeTeachingResearch(Long id) {
        teachingResearchDao.deleteById(id);
    }

    @Override
    public void removeTeachingResearchsInBatch(List<TeachingResearch> teachingResearchs) {
        teachingResearchDao.deleteInBatch(teachingResearchs);
    }

    @Override
    public TeachingResearch updateTeachingResearch(TeachingResearch teachingResearch) {
        return teachingResearchDao.save(teachingResearch);
    }

    @Override
    public TeachingResearch getTeachingResearchById(Long id) {
        return teachingResearchDao.getOne(id);
    }

    @Override
    public List<TeachingResearch> listTeachingResearchs() {
        return teachingResearchDao.findAll();
    }

    @Override
    public Page<TeachingResearch> listTeachingResearchsByTypeAndTitleLike(String type,String title, Pageable pageable) {

        title = "%" + title + "%";
        return teachingResearchDao.findByTypeAndTitleLikeOrderByUpdateTimeDesc(type, title, pageable);
    }

    @Override
    public long count(){
        return teachingResearchDao.count();
    }
}
