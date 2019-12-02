package com.haibusiness.szweb.controller;

import com.haibusiness.szweb.entity.Collaboration;
import com.haibusiness.szweb.entity.User;
import com.haibusiness.szweb.service.CollaborationService;
import com.haibusiness.szweb.service.MenuService;
import com.haibusiness.szweb.vo.Response;
import lombok.AllArgsConstructor;

import org.hibernate.type.SetType;
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
public class CollaborationController {
    private CollaborationService collaborationService;
    private MenuService menuService;

    @GetMapping("/public/collaboration/{type}")
    public ResponseEntity list(
            @PathVariable("type") String type,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "title", required = false, defaultValue = "") String title
           ) {

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Collaboration> page = collaborationService.listCollaborationsByTypeAndTitleLike(type,title,pageable);
        Map model=new HashMap<>();
        model.put("page", page);
        model.put("name", menuService.getMenus().get(type));
        model.put("type", type);
        model.put("title", menuService.getMenus().get("collaboration"));
        return ResponseEntity.ok().body(model);
    }


    @PutMapping(value = "/public/collaboration/{id}")
    public ResponseEntity addHit(@PathVariable Long id) {
        try {
            Collaboration collaboration = collaborationService.getCollaborationById(id);
            collaboration.setHit( collaboration.getHit()+1);
            collaboration.setType(collaboration.getType());
            collaborationService.saveCollaboration(collaboration);
            
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "修改浏览次数失败！"));
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    /**
     * 新建
     *
     * @param collaboration
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping(value = "/collaboration/{type}")
    public ResponseEntity<Response> createDaogou(@PathVariable String type,@RequestBody Collaboration collaboration) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            collaboration.setUpdateUser(user);
            collaboration.setType(type);
            collaborationService.saveCollaboration(collaboration);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }

    /**
     * 更新
     *
     * @param collaboration
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping(value = "/collaboration")
    public ResponseEntity<Response> updateCollaboration(@RequestBody Collaboration collaboration) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            collaboration.setUpdateUser(user);
            //更新,对于表单中没有出现的字段要把原来的值付给它们
            Collaboration originalCollaboration = collaborationService.getCollaborationById(collaboration.getId());
            collaboration.setHit(originalCollaboration.getHit());
            collaboration.setType(originalCollaboration.getType());
            collaborationService.saveCollaboration(collaboration);
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
    @DeleteMapping(value = "/collaboration/{id}")
    public ResponseEntity<Response> deleteCollaboration(@PathVariable Long id) {
        try {
            collaborationService.removeCollaboration(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "删除失败"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功"));
    }

}
