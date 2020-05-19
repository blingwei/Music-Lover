package com.blingwei.musicService.filter;

import com.blingwei.musicService.pojo.AdminPermission;
import com.blingwei.musicService.service.AdminPermService;
import com.blingwei.musicService.utils.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class URLPathMatchingFilter extends PathMatchingFilter {

    AdminPermService adminPermService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (HttpMethod.OPTIONS.toString().equals((httpServletRequest).getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        if (null == adminPermService) {
            adminPermService = SpringContextUtils.getContext().getBean(AdminPermService.class);
        }

        String requestAPI = getPathWithinApplication(request);

        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            System.out.println("需要登录");
            return false;
        }
        if (!adminPermService.needFilter(requestAPI)) {
            System.out.println("接口：" + requestAPI + "无需权限");
            return true;
        } else {
            boolean hasPermission = false;
            List<AdminPermission> permissions = adminPermService.findPermsByUserName(subject.getPrincipal().toString());
            for (AdminPermission adminPermission : permissions) {
                if (requestAPI.startsWith(adminPermission.getUrl())) {
                    hasPermission = true;
                    break;
                }
            }

            if (hasPermission) {
                System.out.println("访问权限：" + requestAPI + "验证成功");
                return true;
            } else {
                System.out.println("当前用户没有访问接口" + requestAPI + "的权限");
                return false;
            }
        }

    }
}
