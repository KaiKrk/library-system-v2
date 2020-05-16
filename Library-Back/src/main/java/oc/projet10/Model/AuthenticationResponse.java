package oc.projet10.Model;

import oc.projet10.bean.AuthUser;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private final String jwt;

    public AuthenticationResponse(AuthUser authUser) {
        this.jwt = authUser.getToken();
    }

    public String getJwt() {
        return jwt;
    }
}
