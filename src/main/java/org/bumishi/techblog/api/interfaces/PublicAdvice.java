package org.bumishi.techblog.api.interfaces;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.bumishi.techblog.api.application.CatalogService;
import org.bumishi.techblog.api.application.NavService;
import org.bumishi.toolbox.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author qiang.xie
 * @date 2016/9/27
 */
@ControllerAdvice
public class PublicAdvice {

    @Autowired
    private NavService navService;

    @Autowired
    private CatalogService catalogService;


    @ExceptionHandler
    public void handleControllerException(HttpServletRequest request, HttpServletResponse response, Throwable ex) throws IOException {
        ex.printStackTrace();
        String ajax = request.getHeader("X-Requested-With");
        response.setCharacterEncoding("utf-8");
        if (StringUtils.isBlank(ajax)) {
            response.sendRedirect("/error");
        } else {
            response.setContentType("application/json");

            response.getWriter().println(JSON.toJSON(RestResponse.fail(ex.getMessage())));
        }

    }

    @ModelAttribute
    public void addCommonModel(Model model, HttpServletRequest request) {
        model.addAttribute("navs", navService.listWithTree(false));
        model.addAttribute("catalogs", catalogService.listWithTree(false));
    }


}
