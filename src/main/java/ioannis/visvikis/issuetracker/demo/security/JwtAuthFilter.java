package ioannis.visvikis.issuetracker.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import ioannis.visvikis.issuetracker.demo.Commons;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authorizationHeader = httpRequest.getHeader("Authorization");
        if (null == authorizationHeader) {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Autorization header missing");
            return;
        }
        String[] tokenInfo = authorizationHeader.split("\\s+");
        if (tokenInfo.length != 2) {
            httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "invalid token format : Use Bearer [token]");
            return;
        }
        String bearer = tokenInfo[0];
        if (!bearer.equals("Bearer")) {
            httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "invalid token format : Use Bearer [token]");
            return;
        }
        if(getClaims(tokenInfo[1], httpRequest, httpResponse) == null) return; //authentication failed
        chain.doFilter(httpRequest, httpResponse);
    }


    private Claims getClaims(String jwtToken, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {

        Claims tokenClaims = null;
        try {
            tokenClaims = Jwts.parser()
                              .setSigningKey(SecurityEnum.API_SECRET_KEY.getEnumValue())
                              .parseClaimsJws(jwtToken)
                              .getBody();

            //set the username which is hidden (as part of the token) to a request attribute. So I know who's calling and makes modifications
            httpRequest.setAttribute(Commons.USERNAME_KEY.toString(), tokenClaims.get(SecurityEnum.CLAIM_USERNAME.getEnumValue()));

        }
        catch (Exception e) {
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
        }
        return tokenClaims;
    }

}

