package pucmm.edu.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import javalinjwt.JWTProvider;
import pucmm.edu.encapsulaciones.Usuario;
import javalinjwt.JWTGenerator;

public class ProviderExample {
    public static JWTProvider<Usuario> createHMAC512() {
        JWTGenerator<Usuario> generator = (user, alg) -> {
            JWTCreator.Builder token = JWT.create()
                    .withClaim("name", user.getUsuario())
                    .withClaim("level", user.getClave());
            return token.sign(alg);
        };

        Algorithm algorithm = Algorithm.HMAC256("very_secret");
        JWTVerifier verifier = JWT.require(algorithm).build();

        return new JWTProvider<>(algorithm, generator, verifier);
    }
}