package com.haibusiness.szweb.controller;

import com.haibusiness.szweb.entity.Overview;
import com.haibusiness.szweb.entity.User;
import com.haibusiness.szweb.service.MenuService;
import com.haibusiness.szweb.service.OverviewService;
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
public class OverviewController {
    private OverviewService overviewService;
    private MenuService menuService;

    @GetMapping("/public/overview/{type}")
    public ResponseEntity list(
            @PathVariable("type") String type,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "title", required = false, defaultValue = "") String title
    ) {

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<Overview> page = overviewService.listOverviewsByTypeAndTitleLike(type,title,pageable);
        Map model=new HashMap<>();
        model.put("page", page);
        model.put("name", menuService.getMenus().get(type));
        model.put("type", type);
        model.put("title", menuService.getMenus().get("overview"));
        return ResponseEntity.ok().body(model);
    }


    /**
     * 新建
     *
     * @param overview
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PostMapping(value = "/overview/{type}")
    public ResponseEntity<Response> createDaogou(@PathVariable String type,@RequestBody Overview overview) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            overview.setUpdateUser(user);
            overview.setType(type);
            overviewService.saveOverview(overview);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功", null));
    }
    @PutMapping(value = "/public/overview/{id}")
    public ResponseEntity addHit(@PathVariable Long id) {
        try {
            Overview overview = overviewService.getOverviewById(id);
            overview.setHit( overview.getHit()+1);
            overviewService.saveOverview(overview);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "修改浏览次数失败！"));
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
    /**
     * 更新
     *
     * @param overview
     * @return ResponseEntity
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @PutMapping(value = "/overview")
    public ResponseEntity<Response> updateOverview(@RequestBody Overview overview) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            overview.setUpdateUser(user);
            //更新,对于表单中没有出现的字段要把原来的值付给它们
            Overview originalOverview = overviewService.getOverviewById(overview.getId());
            overview.setType(originalOverview.getType());
            overview.setHit(originalOverview.getHit());
            overviewService.saveOverview(overview);
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
    @DeleteMapping(value = "/overview/{id}")
    public ResponseEntity<Response> deleteOverview(@PathVariable Long id) {
        try {
            overviewService.removeOverview(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, "删除失败"));
        }
        return ResponseEntity.ok().body(new Response(true, "处理成功"));
    }
}
