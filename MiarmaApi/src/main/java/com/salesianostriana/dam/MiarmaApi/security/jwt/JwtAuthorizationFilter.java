package com.salesianostriana.dam.MiarmaApi.security.jwt;

import com.salesianostriana.dam.MiarmaApi.users.models.Usuario;
import com.salesianostriana.dam.MiarmaApi.users.services.UsuarioServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UsuarioServices usuarioServices;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getJwtFromRequest(request);

        try {
            if (StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
                UUID userId = jwtProvider.getUserIdFromJwt(token);

                Optional<Usuario> userEntity = usuarioServices.findById(userId);

                if (userEntity.isPresent()) {
                    Usuario usuario = userEntity.get();
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    usuario,
                                    usuario.getRole(),
                                    usuario.getAuthorities()
                            );
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
        } catch (Exception e) {
            log.info("No se ha podido establecer el contexto de seguridad (" +e.getMessage() +")");
        }
        filterChain.doFilter(request,response);
    }

    private String getJwtFromRequest (HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtProvider.TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProvider.TOKEN_PREFIX)) {
            bearerToken.substring(JwtProvider.TOKEN_PREFIX.length());
        }
        return null;
    }
}
