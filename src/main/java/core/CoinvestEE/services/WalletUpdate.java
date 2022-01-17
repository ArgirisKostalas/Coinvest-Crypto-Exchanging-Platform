package core.CoinvestEE.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.session.FindByIndexNameSessionRepository;

import javax.servlet.http.HttpSession;

public interface WalletUpdate {
    static String resolvePrincipal(HttpSession session){
        String principalName = (String) session.getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME);
        if (principalName != null){
            return principalName;
        }
        SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContext != null && securityContext.getAuthentication() != null){
            return securityContext.getAuthentication().getName();
        }
        return "";
    }
    void refreshRates() throws JsonProcessingException;
    double calculateTotalBalance(Long ID);

}
