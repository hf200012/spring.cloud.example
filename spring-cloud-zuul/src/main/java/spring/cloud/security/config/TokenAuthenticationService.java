package spring.cloud.security.config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * token认证服务，token时限默认一天
 *
 */
class TokenAuthenticationService {
    //token过期时间，默认是一天
    static final long EXPIRATIONTIME = 1000 * 60 * 60 * 24 ; // 1 days
    //秘钥
    static final String SECRET = "9a232d97-f6e9-473f-9fbe-3703e77db5f8";
    //token前缀
    static final String TOKEN_PREFIX = "jldata";
    //http header 认真信息header名称
    static final String HEADER_STRING = "Authorization";

    /**
     * 生成token信息并添加到header中返回
     * @param res
     * @param username
     */
    static void addAuthentication(HttpServletResponse res, String username) {
        //根据私钥、加密算法、前缀，过期时间生成token
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    /**
     * 对token进行解析认证
     * @param request
     * @return
     */
    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            //对token进行解析验证
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
}