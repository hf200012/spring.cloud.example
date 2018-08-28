package spring.cloud.exapmle.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

//@Component
public class AuthFilter extends ZuulFilter {
    private static Logger log = Logger.getLogger(AuthFilter.class.toString());

    /**
     * 过滤器类型：pre、routing、post、error
     * pre	在路由之前执行过滤
     * routing	在路由时执行过滤
     * post	在路由之后执行过滤
     * error	在发生错误时执行过滤
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器顺序，用于指定过滤器执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否要过滤，可以根据当前请求信息判断是否过滤，也可以默认返回true
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("header-token:%s,param-token:%s", request.getHeader("token"), request.getParameter("token")));
        String token_header = request.getHeader("token") == null ? "" : request.getHeader("token");
        String token_param = request.getParameter("token") == null ? "" : request.getParameter("token");
        if (token_header.equals("") && token_param.equals("")) {
            try {
                ctx.setSendZuulResponse(false);
                ctx.getResponse().getWriter().write("{\"code\": 9999,\"message\": \"token is empty.\"}");
            } catch (Exception e) {
                log.warning("system error");
            }
        } else if (!token_header.equals("")) {
            log.warning(String.format("token is %s", token_header));
        } else if (!token_param.equals("")) {
            log.warning(String.format("token is %s", token_param));
        }
        return null;
    }
}
