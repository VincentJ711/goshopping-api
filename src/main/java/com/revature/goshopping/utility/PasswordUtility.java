package com.revature.goshopping.utility;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtility {
  public static String hash(String plainTextPass) {
    return BCrypt.hashpw(plainTextPass, BCrypt.gensalt(12));
  }

  public static boolean doesMatch(String plainTextPass, String hashedPass) {
    return BCrypt.checkpw(plainTextPass, hashedPass);
  }
}
