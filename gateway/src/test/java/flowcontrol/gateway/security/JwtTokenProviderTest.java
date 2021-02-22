package flowcontrol.gateway.security;

import flowcontrol.gateway.model.entity.CustomUserDetails;
import flowcontrol.gateway.model.entity.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JwtTokenProviderTest {

    private static final String jwtSecret = "testSecret";
    private static final long jwtExpiryInMs = 2500;

    private JwtTokenProvider tokenProvider;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.tokenProvider = new JwtTokenProvider(jwtSecret, jwtExpiryInMs);
    }

    @Test
    public void testGetUserIdFromJWT(){
        String token = tokenProvider.generateToken(stubCustomUser());
        assertEquals(100, tokenProvider.getUserIdFromJwt(token).longValue());
    }

    @Test
    public void testGetTokenExpiryFromJWT(){
        String token = tokenProvider.generateTokenFromUserId(120L);
        assertNotNull(tokenProvider.getTokenExpiryFromJwt(token));
    }

    @Test
    public void testGetExpiryDuration(){
        assertEquals(jwtExpiryInMs, tokenProvider.getJwtExpirationInMs());
    }

    private CustomUserDetails stubCustomUser(){
        User user = new User();
        user.setId((long) 100);
        return new CustomUserDetails(user);
    }
}
