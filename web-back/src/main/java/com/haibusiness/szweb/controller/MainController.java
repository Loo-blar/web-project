package com.haibusiness.szweb.controller;

import com.haibusiness.szweb.entity.*;
import com.haibusiness.szweb.service.*;
import com.haibusiness.szweb.vo.HomeItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Slf4j
@RestController
public class MainController {
    private TeachingResearchService teachingResearchService;

    private DynamicService dynamicService;

    private MediaService mediaService;

    private NoticeService noticeService;

    private MenuService menuService;

    private CarouselService carouselService;

    private CollaborationService collaborationService;


    @GetMapping("/public/home")
    public ResponseEntity index() {
        Map<String,String> menuMap=menuService.getMenus();
        List<HomeItem> items=new ArrayList<>();
        List<HomeItem> subItems=new ArrayList<>();

        HomeItem temp=new HomeItem();
        temp.setName(menuMap.get("notice"));
        temp.setUrl("notice");
        temp.setItems(this.getNoticeList());
        subItems.add(temp);

        temp=new HomeItem();
        temp.setName(menuMap.get("dynamic"));
        temp.setUrl("dynamic");
        temp.setItems(this.getDynamicList());
        subItems.add(temp);

        temp=new HomeItem();
        temp.setName(menuMap.get("teaching"));
        temp.setType("teaching");
        temp.setUrl("teachingResearch");
        temp.setItems(this.getTeachingList());
        subItems.add(temp);

        temp=new HomeItem();
        temp.setItems(subItems);
        items.add(temp);

        temp=new HomeItem();
        temp.setName(menuMap.get("media"));
        temp.setUrl("media");
        temp.setItems(this.getMediaList());
        items.add(temp);

        temp=new HomeItem();
        temp.setName(menuMap.get("carousel"));
        temp.setUrl("carousel");
        temp.setItems(this.getCarouselList());
        items.add(temp);

        temp=new HomeItem();
        temp.setName(menuMap.get("collaboration"));
        temp.setUrl("collaboration");
        temp.setItems(this.getCollaborationList());
        items.add(temp);

        return ResponseEntity.ok().body(items);
    }

    private List<Collaboration> getCollaborationList() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Collaboration> page = collaborationService.listCollaborationsByTypeAndTitleLike("enterprise","", pageable);
        List<Collaboration> list = page.getContent(); 
        return list;
    }

    private List<TeachingResearch> getTeachingList() {
        Pageable pageable = PageRequest.of(0,10);
        Page<TeachingResearch> page = teachingResearchService.listTeachingResearchsByTypeAndTitleLike("teaching", "", pageable);
        List<TeachingResearch> list = page.getContent();    // 当前所在页面数据列表
        return list;
    }
    private List<Dynamic> getDynamicList() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Dynamic> page = dynamicService.listDynamicsByTitleLike("", pageable);
        List<Dynamic> list = page.getContent();    // 当前所在页面数据列表

        return list;
    }
    private List<Notice> getNoticeList() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Notice> page = noticeService.listNoticesByTitleLike("", pageable);
        List<Notice> list = page.getContent();    // 当前所在页面数据列表

        return list;
    }
    private List<Graduate> getMediaList() {
        Pageable pageable = PageRequest.of(0, 4);
        Page<Graduate> page = mediaService.listMediasByTitleLike("", pageable);
        List<Graduate> list = page.getContent();    // 当前所在页面数据列表

        return list;
    }
    private List<Carousel> getCarouselList() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Carousel> page =carouselService.listCarouselsByTitleLike("", pageable);
        List<Carousel> list = page.getContent();    // 当前所在页面数据列表

        return list;
    }
}
