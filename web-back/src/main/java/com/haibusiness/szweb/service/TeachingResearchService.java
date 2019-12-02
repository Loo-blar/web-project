package com.haibusiness.szweb.service;

import com.haibusiness.szweb.entity.TeachingResearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeachingResearchService{
    TeachingResearch saveTeachingResearch(TeachingResearch teachingResearch);


    void removeTeachingResearch(Long id);


    void removeTeachingResearchsInBatch(List<TeachingResearch> teachingResearchs);

    TeachingResearch updateTeachingResearch(TeachingResearch teachingResearch);

    TeachingResearch getTeachingResearchById(Long id);


    List<TeachingResearch> listTeachingResearchs();


    Page<TeachingResearch> listTeachingResearchsByTypeAndTitleLike(String type, String title, Pageable pageable);
    long count();
}
