package ru.itis.task.filter;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import ru.itis.task.security.details.UserDetailsImpl;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@ApplicationScope
public class CookieFilter implements Filter {

    @Value("${secret-key}")
    String secret;

    private static String encode(String key, String data) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return new String(Hex.encodeHex(sha256_HMAC.doFinal(data.getBytes("UTF-8"))));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        String verify = "";
        String login = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("VERIFY")) {
                    verify = cookie.getValue();
                }
                if (cookie.getName().equals("USER_LOGIN")) {
                    if (cookie.getValue() != null) {
                        login = cookie.getValue();
                    }
                }
            }
        }
        if (login.equals("") || verify.equals("") || verify(verify, login)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletResponse.getWriter().println("Authentication failed");
        }
    }

    private boolean verify(String obtainedSignature, String data) {
        String signature = encode(secret, data);
        return obtainedSignature.equals(signature);
    }
}
