package bootcamp.transactionmicroservice.application.jpa.adapter;

import bootcamp.transactionmicroservice.infrastructure.configuration.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JwtJpaAdapterTest {

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private JwtJpaAdapter jwtJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserIdWithValidJwt() {
        String jwt = "valid.jwt.token";
        Long expectedUserId = 123L;

        when(jwtService.extractUserId(jwt)).thenReturn(expectedUserId);

        Long actualUserId = jwtJpaAdapter.getUserId(jwt);

        assertEquals(expectedUserId, actualUserId);
        verify(jwtService, times(1)).extractUserId(jwt);
    }

    @Test
    void getUserIdWithNullJwt() {
        String jwt = null;

        when(jwtService.extractUserId(jwt)).thenReturn(null);

        Long actualUserId = jwtJpaAdapter.getUserId(jwt);

        assertEquals(null, actualUserId);
        verify(jwtService, times(1)).extractUserId(jwt);
    }

    @Test
    void getUserNameWithValidJwt() {
        String jwt = "valid.jwt.token";
        String expectedUserName = "testuser";

        when(jwtService.extractUsername(jwt)).thenReturn(expectedUserName);

        String actualUserName = jwtJpaAdapter.getUserName(jwt);

        assertEquals(expectedUserName, actualUserName);
        verify(jwtService, times(1)).extractUsername(jwt);
    }

    @Test
    void getUserNameWithNullJwt() {
        String jwt = null;

        when(jwtService.extractUsername(jwt)).thenReturn(null);

        String actualUserName = jwtJpaAdapter.getUserName(jwt);

        assertEquals(null, actualUserName);
        verify(jwtService, times(1)).extractUsername(jwt);
    }
}