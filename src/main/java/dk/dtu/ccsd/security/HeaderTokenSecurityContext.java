package dk.dtu.ccsd.security;

/**
 * Created by xiuli on 3/2/15.
 */
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;

public class HeaderTokenSecurityContext implements SecurityContext {
    private static final long serialVersionUID = 1L;

    private Authentication authentication;

    public HeaderTokenSecurityContext(UserDetails userDetails) {
    }

    @Override
    public Authentication getAuthentication() {
        return authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        throw new UnsupportedOperationException("Should not be called by the code path");
    }

}