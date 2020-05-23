package oc.projet10.Controller;

import oc.projet10.Model.AuthenticationRequest;
import oc.projet10.Security.MyUserDetailsService;
import oc.projet10.Service.MemberService;
import oc.projet10.bean.AuthUser;
import oc.projet10.bean.MemberDto;
import oc.projet10.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MemberService memberService;


    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("auth called");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        MemberDto member = memberService.getMemberDto(authenticationRequest.getUsername());
        AuthUser authUser= new AuthUser();
        authUser.setId(member.getMemberId());
        authUser.setEmail(member.getEmail());
        authUser.setName(member.getName());
        authUser.setSurname(member.getSurname());
        authUser.setToken(jwt);
        authUser.setAdmin(member.isAdmin());
        return ResponseEntity.ok(authUser);
    }
}
