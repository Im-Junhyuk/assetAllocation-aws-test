package graduationproject.assetallocation.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
public class SecurityUtil {

    private SecurityUtil(){}

    public static Optional<String> getCurrentLoginId(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null){
            log.info("There is no authentication info in security context.");
            return Optional.empty();
        }

        String loginId = null;
        if (authentication.getPrincipal() instanceof UserDetails){
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            loginId = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String){
            loginId = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(loginId);
    }
}
