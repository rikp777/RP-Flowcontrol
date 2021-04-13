package flowcontrol.gateway.security;

import flowcontrol.gateway.event.OnUserLogOutSuccessEvent;
import flowcontrol.gateway.exception.TokenInvalidRequestException;
import flowcontrol.gateway.security.chache.LoggedOutJwtTokenCache;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JwtTokenValidatorTest {

    private static final String jwtSecret = "testSecret";
    private static final long jwtExpiryInMs = 2500;

    @Mock
    private LoggedOutJwtTokenCache loggedOutJwtTokenCache;
    private JwtTokenProvider tokenProvider;
    private JwtTokenValidator tokenValidator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.tokenProvider = new JwtTokenProvider(jwtSecret, jwtExpiryInMs);
        this.tokenValidator = new JwtTokenValidator(jwtSecret, loggedOutJwtTokenCache);
    }

    @Test
    public void testValidateTokenThrowsExceptionWhenTokenIsDamaged(){
        String token = tokenProvider.generateTokenFromUserId(100L);
        OnUserLogOutSuccessEvent logOutSuccessEvent = stubLogOutEvent("U1", token);
        when(loggedOutJwtTokenCache.getLogoutEventForToken(token)).thenReturn(logOutSuccessEvent);

        thrown.expect(TokenInvalidRequestException.class);
        thrown.expectMessage("Incorrect signature");
        tokenValidator.validateToken(token + "-Damage");
    }

    @Test
    public void testValidateTokenThrowsExceptionWhenTokenIsExpired() throws InterruptedException {
        String token = tokenProvider.generateTokenFromUserId(123L);
        TimeUnit.MILLISECONDS.sleep(jwtExpiryInMs);
        OnUserLogOutSuccessEvent logoutEvent = stubLogOutEvent("U1", token);
        when(loggedOutJwtTokenCache.getLogoutEventForToken(token)).thenReturn(logoutEvent);

        thrown.expect(TokenInvalidRequestException.class);
        thrown.expectMessage("Token expired. Refresh required");
        tokenValidator.validateToken(token);
    }

    @Test
    public void testValidateTokenThrowsExceptionWhenItIsPresentInTokenCache() {
        String token = tokenProvider.generateTokenFromUserId(124L);
        OnUserLogOutSuccessEvent logoutEvent = stubLogOutEvent("U2", token);
        when(loggedOutJwtTokenCache.getLogoutEventForToken(token)).thenReturn(logoutEvent);

        thrown.expect(TokenInvalidRequestException.class);
        thrown.expectMessage("Token corresponds to an already logged out user [U2]");
        tokenValidator.validateToken(token);
    }

    @Test
    public void testValidateTokenWorksWhenItIsNotPresentInTokenCache() {
        String token = tokenProvider.generateTokenFromUserId(100L);
        tokenValidator.validateToken(token);
        verify(loggedOutJwtTokenCache, times(1)).getLogoutEventForToken(token);
    }

    private OnUserLogOutSuccessEvent stubLogOutEvent(String email, String token){
        return new OnUserLogOutSuccessEvent(email, token, null);
    }
}
