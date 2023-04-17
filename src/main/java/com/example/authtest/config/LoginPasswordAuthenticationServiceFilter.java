package com.example.authtest.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPasswordAuthenticationServiceFilter extends UsernamePasswordAuthenticationFilter {
    /*public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "j_password";
    public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";

    private String usernameParameter = "j_username";
    private String passwordParameter = "j_password";

    // custom fields for ai security login
    private String companyParameter = "company";


    private boolean postOnly = true;

    public LoginPasswordAuthenticationServiceFilter() {
        super("/j_spring_security_check");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException,
            IOException, ServletException {

        if ((this.postOnly) && (!(request.getMethod().equals("POST")))) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String userName = obtainUserID(request).trim();
        String password = obtainPassword(request).trim();
        String company = obtainCompany(request).trim();

        *//*if (userName) ||
                AppUtil.isNullOrEmpty(password) ||
                AppUtil.isNullOrEmpty(company)) {

            throw new BadCredentialsException("Invalid username/password");
        }*//*

        userName = userName.trim();

        //Using custom authentication token to include extra parameter company. Instead you can use UsernamePasswordAuthenticationToken
        LoginPasswordAuthenticationServiceToken authRequest = new LoginPasswordAuthenticationServiceToken(userName, password);

        // Place the last username attempted into HttpSession for views
        HttpSession session = request.getSession(false);
        if ((session != null) || (getAllowSessionCreation())) {
            session = request.getSession();
            session.setAttribute("SPRING_SECURITY_LAST_USERNAME", TextEscapeUtils.escapeEntities(userName));
        }

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        //calling authentication manager
        LoginPasswordAuthenticationServiceToken authentication = (LoginPasswordAuthenticationServiceToken) super.getAuthenticationManager().authenticate(authRequest);

        Object user = (Object) authentication.getPrincipal();
        session.setAttribute("USER", user);

        //if password expired
        if (!(new Date(System.currentTimeMillis()).before(new Date()))) {

            //changing the defaultTargetUrl to force user to change password.
            request.setAttribute("const", "/changePassword");
        }


        return authentication;
    }

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(this.passwordParameter);
    }

    protected String obtainUserID(HttpServletRequest request) {
        return request.getParameter(this.usernameParameter);
    }

    protected String obtainCompany(HttpServletRequest request) {
        return request.getParameter(this.companyParameter);
    }

    protected void setDetails(HttpServletRequest request, LoginPasswordAuthenticationServiceToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }*/


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginPasswordAuthenticationServiceToken authRequest = getAuthRequest(request);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private LoginPasswordAuthenticationServiceToken getAuthRequest(HttpServletRequest request) {
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        return new LoginPasswordAuthenticationServiceToken(username, password);
    }
}
