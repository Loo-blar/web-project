package com.haibusiness.szweb.service;

import com.haibusiness.szweb.dao.OverviewDao;
import com.haibusiness.szweb.entity.Overview;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class OverviewServiceImpl implements OverviewService {

    private OverviewDao overviewDao;
    @Override
    public Overview saveOverview(Overview overview) {

        return overviewDao.save(overview);
    }

    @Override
    public void removeOverview(Long id) {

        overviewDao.deleteById(id);
    }

    @Override
    public void removeOverviewsInBatch(List<Overview> overviews) {

        overviewDao.deleteInBatch(overviews);
    }

    @Override
    public Overview updateOverview(Overview overview) {

        return overviewDao.save(overview);
    }

    @Override
    public Overview getOverviewById(Long id) {

        return overviewDao.getOne(id);
    }

    @Override
    public List<Overview> listOverviews() {

        return overviewDao.findAll();
    }

    @Override
    public Page<Overview> listOverviewsByTypeAndTitleLike(String type, String title, Pageable pageable) {
        title = "%" + title + "%";
        return overviewDao.findByTypeAndTitleLikeOrderByUpdateTimeDesc(type, title, pageable);
    }


}
