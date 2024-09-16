package bootcamp.transactionMicroservice.infrastructure.configuration.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class HttpSecurityConfigurationTest {

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private AuthenticationProvider authenticationProvider;

    private HttpSecurityConfiguration httpSecurityConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        httpSecurityConfiguration = new HttpSecurityConfiguration(jwtAuthenticationFilter, authenticationProvider);
    }

    @Test
    void securityFilterChain_shouldReturnNonNullSecurityFilterChain() throws Exception {
        HttpSecurity httpSecurity = mock(HttpSecurity.class);
        SecurityFilterChain mockSecurityFilterChain = mock(SecurityFilterChain.class);

        when(httpSecurity.csrf(any())).thenReturn(httpSecurity);
        when(httpSecurity.sessionManagement(any())).thenReturn(httpSecurity);
        when(httpSecurity.authenticationProvider(any())).thenReturn(httpSecurity);
        when(httpSecurity.addFilterBefore(any(), any())).thenReturn(httpSecurity);
        when(httpSecurity.build()).thenReturn(mock(DefaultSecurityFilterChain.class));

        SecurityFilterChain securityFilterChain = httpSecurityConfiguration.securityFilterChain(httpSecurity);

        assertNotNull(securityFilterChain);
    }

    @Test
    void securityFilterChain_shouldDisableCsrf() throws Exception {
        HttpSecurity httpSecurity = mock(HttpSecurity.class);
        when(httpSecurity.csrf(any())).thenReturn(httpSecurity);
        when(httpSecurity.sessionManagement(any())).thenReturn(httpSecurity);
        when(httpSecurity.authenticationProvider(any())).thenReturn(httpSecurity);
        when(httpSecurity.addFilterBefore(any(), any())).thenReturn(httpSecurity);

        httpSecurityConfiguration.securityFilterChain(httpSecurity);

        verify(httpSecurity).csrf(any());
    }

    @Test
    void securityFilterChain_shouldSetSessionCreationPolicyToStateless() throws Exception {
        HttpSecurity httpSecurity = mock(HttpSecurity.class);
        when(httpSecurity.csrf(any())).thenReturn(httpSecurity);
        when(httpSecurity.sessionManagement(any())).thenReturn(httpSecurity);
        when(httpSecurity.authenticationProvider(any())).thenReturn(httpSecurity);
        when(httpSecurity.addFilterBefore(any(), any())).thenReturn(httpSecurity);

        httpSecurityConfiguration.securityFilterChain(httpSecurity);

        verify(httpSecurity).sessionManagement(any());
    }

    @Test
    void securityFilterChain_shouldAddJwtAuthenticationFilterBeforeUsernamePasswordAuthenticationFilter() throws Exception {
        HttpSecurity httpSecurity = mock(HttpSecurity.class);
        when(httpSecurity.csrf(any())).thenReturn(httpSecurity);
        when(httpSecurity.sessionManagement(any())).thenReturn(httpSecurity);
        when(httpSecurity.authenticationProvider(any())).thenReturn(httpSecurity);
        when(httpSecurity.addFilterBefore(any(), any())).thenReturn(httpSecurity);

        httpSecurityConfiguration.securityFilterChain(httpSecurity);

        verify(httpSecurity).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Test
    void securityFilterChain_shouldSetAuthenticationProvider() throws Exception {
        HttpSecurity httpSecurity = mock(HttpSecurity.class);
        when(httpSecurity.csrf(any())).thenReturn(httpSecurity);
        when(httpSecurity.sessionManagement(any())).thenReturn(httpSecurity);
        when(httpSecurity.authenticationProvider(any())).thenReturn(httpSecurity);
        when(httpSecurity.addFilterBefore(any(), any())).thenReturn(httpSecurity);

        httpSecurityConfiguration.securityFilterChain(httpSecurity);

        verify(httpSecurity).authenticationProvider(authenticationProvider);
    }
}