package bootcamp.transactionmicroservice.infrastructure.configuration.security;

import bootcamp.transactionmicroservice.domain.until.JwtConst;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {
    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        userDetailsService = mock(UserDetailsService.class);
        jwtAuthenticationFilter = new JwtAuthenticationFilter(userDetailsService);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
    }

    @Test
    void doFilterInternal_shouldAuthenticateUser_whenValidJwtToken() throws ServletException, IOException {
        when(request.getHeader(JwtConst.AUTHORIZATION)).thenReturn("Bearer validToken");
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername("validToken")).thenReturn(userDetails);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertEquals(userDetails, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_shouldNotAuthenticateUser_whenNoAuthHeader() throws ServletException, IOException {
        when(request.getHeader(JwtConst.AUTHORIZATION)).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_shouldNotAuthenticateUser_whenAuthHeaderDoesNotStartWithBearer() throws ServletException, IOException {
        when(request.getHeader(JwtConst.AUTHORIZATION)).thenReturn("InvalidHeader");

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterInternal_shouldClearContextAndSendError_whenExceptionOccurs() throws ServletException, IOException {
        when(request.getHeader(JwtConst.AUTHORIZATION)).thenReturn("Bearer invalidToken");
        when(userDetailsService.loadUserByUsername("invalidToken")).thenThrow(new RuntimeException("Invalid token"));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());
        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        verify(filterChain, never()).doFilter(request, response);
    }
}