package com.revature.goshopping.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.goshopping.dto.Auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public final class JwtUtility {
  private static String signingKey = "TODO we can change this whenever we " +
      "want if we care enough to an env var.";

  /**
   * how many milliseconds a jwt can last before its expired.
   */
  private static long ttlMillis = 7 * 24 * 60 * 60000;

  private static ObjectMapper mapper = new ObjectMapper();

  private static String claimKey = "auth";

  private static SignatureAlgorithm algo = SignatureAlgorithm.HS256;

  /**
   * @param auth will be encoded into the jwt token to be decoded on subsequent
   *     requests
   * @return jwt token with the auth object encoded into it.
   */
  public static String create(Auth auth)
      throws JsonProcessingException {
    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    Date expires = new Date(nowMillis + ttlMillis);
    byte[] jwtKeyBytes = DatatypeConverter.parseBase64Binary(signingKey);
    Key key = new SecretKeySpec(jwtKeyBytes, algo.getJcaName());

    return Jwts.builder()
        .setIssuedAt(now)
        .setExpiration(expires)
        .claim(claimKey, mapper.writeValueAsString(auth))
        .signWith(key, algo)
        .compact();
  }

  /**
   * @param jwt the jwt to decode
   * @return a nullable Auth parsed from the given jwt
   */
  private static Auth decode(String jwt) {
    try {
      String json = Jwts.parserBuilder()
          .setSigningKey(DatatypeConverter.parseBase64Binary(signingKey))
          .build()
          .parseClaimsJws(jwt).getBody().get(claimKey).toString();
      return mapper.readValue(json, Auth.class);
    } catch (Throwable e) {
      return null;
    }
  }

  /**
   * @param headers defined by @RequestHeader in controller method(s)
   * @return nullable Auth. returns null if jwt is not found in the given
   *     headers or returns null if it's invalid.
   */
  public static Auth getAuth(Map<String, String> headers) {
    try {
      String[] tokens = new String[]{};
      String[] headerNames = new String[]{
          "Postman-Auth",
          "Authorization"
      };

      // to make switching auths easy in postman, u can actually create a
      // prerequest script and set a custom header there. you can set the jwt
      // token to a postman environment variable. it wouldn't let me set the
      // Authorization header which is why i have used this custom header here.

      // edit your postman collection and add this to the prerequest script.
      // this assumes you have environment var jwt set to a jwt token.
      // pm.request.headers.add({ key: 'Postman-Auth', value: 'Bearer ' + pm.environment.get('jwt') });

      // of course, if you're not using postman, send the jwt in the
      // standard Authorization header.
      // i check lower because postman lowercases...

      for (String h : headerNames) {
        if (headers.get(h) != null) {
          tokens = headers.get(h).split(" ");
        } else if (headers.get(h.toLowerCase()) != null) {
          tokens = headers.get(h.toLowerCase()).split(" ");
        }
      }

      return decode(tokens[tokens.length - 1]);
    } catch (Throwable e) {
      return null;
    }
  }
}
