package com.haibusiness.szweb.service;

import com.haibusiness.szweb.entity.Overview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OverviewService {
    Overview saveOverview(Overview overview);


    void removeOverview(Long id);


    void removeOverviewsInBatch(List<Overview> overviews);

    Overview updateOverview(Overview overview);

    Overview getOverviewById(Long id);


    List<Overview> listOverviews();


    Page<Overview> listOverviewsByTypeAndTitleLike(String type, String title, Pageable pageable);
}
