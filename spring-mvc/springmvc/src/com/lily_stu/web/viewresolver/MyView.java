package com.lily_stu.web.viewresolver;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("lilyView")
public class MyView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception {

        System.out.println("进入到自己的视图");
        // 这里我们自己来确定到哪个页面去,默认的视图解析机制就无效
        httpServletRequest.getRequestDispatcher("/WEB-INF/pages/my_view.jsp")
                .forward(httpServletRequest, httpServletResponse);
    }
}
