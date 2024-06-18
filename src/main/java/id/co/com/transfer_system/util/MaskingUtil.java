package id.co.com.transfer_system.util;

public class MaskingUtil {

    public static String maskAccount(String account){
        if (account.length() < 3) return "***";

        String ars = "*";
        return ars.repeat(account.length()-3) + account.substring(account.length()-3);
    }

}
