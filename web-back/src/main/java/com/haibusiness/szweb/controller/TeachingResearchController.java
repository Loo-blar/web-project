package com.haibusiness.szweb.controller;

import com.haibusiness.szweb.entity.TeachingResearch;
import com.haibusiness.szweb.entity.User;
import com.haibusiness.szweb.service.MenuService;
import com.haibusiness.szweb.service.TeachingResearchService;
import com.haibusiness.szweb.vo.Response;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TeachingResearchController {
    private TeachingResearchService teachingResearchService;
    private MenuService menuService;

    @GetMapping("/public/teachingResearch/{type}")
    public ResponseEntity list(
            @PathVariable("type") String type,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "title", required = false, defaultValue = "") String title
    ) {

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<TeachingResearch> page = teachingResearchService.listTeachingResearchsByTypeAndTitleLike(type,title,pageable);
        Map model=new HashMap<>();
        model.put("page", page);
        model.put("name", menuService.getMenus().get(type));
        model.put("type", type);
        model.put("title", menuService.getMenus().get("teachingResearch"));
        return ResponseEntity.ok().body(model);
    }


    @PutMapping(value = "/public/teachingResearch/{id}")
    public ResponseEntity addHit(@PathVariable Long id) {
        try {
            TeachingResearch teachingResearch = teachingResearchService.getTeachingResearchById(id);
            teachingResearch.setHit( teachingResearch.getHit()+1);
            teachingResearch.getType();
            teachingResearchService.saveTeachingResearch(teachingResearch);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "修改浏览次数失败！"));
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    /**
     * 新建
     *
     * @param teachingResearch
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping(value = "/teachingResearch/{type}")
    public ResponseEntity<Response> createDaogou(@PathVariable String type,@RequestBody TeachingResearch teachingResearch) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            teachingResearch.setUpdateUser(user);
            teachingResearch.setType(type);
            teachingResearchService.saveTeachingResearch(teachingResearch);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }

    /**
     * 更新
     *
     * @param teachingResearch
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping(value = "/teachingResearch")
    public ResponseEntity<Response> updateTeachingResearch(@RequestBody TeachingResearch teachingResearch) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            teachingResearch.setUpdateUser(user);
            //更新,对于表单中没有出现的字段要把原来的值付给它们
            TeachingResearch originalTeachingResearch = teachingResearchService.getTeachingResearchById(teachingResearch.getId());
            teachingResearch.setHit(originalTeachingResearch.getHit());
            teachingResearch.setType(originalTeachingResearch.getType());
            teachingResearchService.saveTeachingResearch(teachingResearch);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "更新失败"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功"));
    }

    /**
     * 删除
     *
     * @param id
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @DeleteMapping(value = "/teachingResearch/{id}")
    public ResponseEntity<Response> deleteTeachingResearch(@PathVariable Long id) {
        try {
            teachingResearchService.removeTeachingResearch(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "删除失败"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功"));
    }

}
