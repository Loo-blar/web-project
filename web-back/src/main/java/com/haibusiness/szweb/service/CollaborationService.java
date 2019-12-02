package com.haibusiness.szweb.service;

import com.haibusiness.szweb.entity.Collaboration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollaborationService {
    Collaboration saveCollaboration(Collaboration collaboration);


    void removeCollaboration(Long id);


    void removeCollaborationsInBatch(List<Collaboration> collaborations);

    Collaboration updateCollaboration(Collaboration collaboration);

    Collaboration getCollaborationById(Long id);


    List<Collaboration> listCollaborations();


    Page<Collaboration> listCollaborationsByTypeAndTitleLike(String type, String title, Pageable pageable);
}
