package flowcontrol.gateway.security;

import flowcontrol.gateway.event.OnUserLogOutSuccessEvent;
import flowcontrol.gateway.security.chache.LoggedOutJwtTokenCache;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.Instant;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoggedOutJwtTokenCacheTest {

    @Mock
    private JwtTokenProvider mockTokenProvider;

    private LoggedOutJwtTokenCache cache;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.cache = new LoggedOutJwtTokenCache(10, mockTokenProvider);
    }

    @Test
    public void testMarkLogoutEventInsertsOnlyOnce(){
        OnUserLogOutSuccessEvent event = stubLogOutEvent("U1", "T1");
        when(mockTokenProvider.getTokenExpiryFromJwt("T1")).thenReturn(Date.from(Instant.now().plusSeconds(100)));

        cache.markLogOutEventForToken(event);
        cache.markLogOutEventForToken(event);
        cache.markLogOutEventForToken(event);

        verify(mockTokenProvider, times(1)).getTokenExpiryFromJwt("T1");
    }


    @Test
    public void getLogoutEventForToken() {
        OnUserLogOutSuccessEvent event = stubLogOutEvent("U2", "T2");
        when(mockTokenProvider.getTokenExpiryFromJwt("T2")).thenReturn(Date.from(Instant.now().plusSeconds(10)));

        cache.markLogOutEventForToken(event);
        assertNull(cache.getLogoutEventForToken("T1"));
        assertNotNull(cache.getLogoutEventForToken("T2"));
    }

    private OnUserLogOutSuccessEvent stubLogOutEvent(String email, String token){
        return new OnUserLogOutSuccessEvent(email, token, null);
    }
}
