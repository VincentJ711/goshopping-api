package com.revature.goshopping.config;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Env {
  public final String stripeKey;

  public final String dbUsername;

  public final String dbUrl;

  public final String hbm2DDLAuto;

  public final String dbPassword;

  public final String jwtSigningKey;

  public Env() {
    this.stripeKey = Objects.requireNonNull(getenv("STRIPE_SK"));
    this.dbUsername = Objects.requireNonNull(getenv("DB_USERNAME"));
    this.dbUrl = Objects.requireNonNull(getenv("DB_URL"));
    this.hbm2DDLAuto = Objects.requireNonNull(getenv("HBM2_DDL_AUTO"));
    this.dbPassword = Objects.requireNonNull(getenv("DB_PASSWORD"));
    this.jwtSigningKey = Objects.requireNonNull(getenv("JWT_SIGNING_KEY"));
  }

  private String getenv(String var) {
    String env = System.getenv(var);
    return env != null ? env : System.getProperty(var);
  }
}
