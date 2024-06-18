package id.co.com.transfer_system.util;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class AmountUtil {
    public static final String COMMA_DELIMITER_WITH_DECIMAL = "#,##0.00";
    public static final String COMMA_DELIMITER_WITHOUT_DECIMAL = "#,##0";
    public static final String NO_DELIMITER_WITH_DECIMAL = "#0.00";
    public static final String NO_DELIMITER_WITHOUT_DECIMAL = "#0";

    private AmountUtil() {
    }

    public static String formatAmount(BigDecimal value) {
        return formatAmount(value.doubleValue(), COMMA_DELIMITER_WITH_DECIMAL);
    }

    public static String formatAmount(BigDecimal value, String format) {
        return formatAmount(value.doubleValue(), format);
    }

    public static String formatAmount(Double value) {
        return formatAmount(value, COMMA_DELIMITER_WITH_DECIMAL);
    }

    public static String formatAmount(Double value, String format) {
        if (value == null || StringUtils.isEmpty(format)) {
            return "";
        }

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat(format, dfs);

        return df.format(value);
    }

    public static String formatAmount(Long value) {
        return formatAmount(value, COMMA_DELIMITER_WITH_DECIMAL);
    }

    public static String formatAmountDivideHundred(Long value) {
        if(value == null || value == 0L) return formatAmount(value, COMMA_DELIMITER_WITH_DECIMAL);

        BigDecimal bVal = new BigDecimal(value).divide(new BigDecimal(100));
        bVal = bVal.setScale(2, RoundingMode.HALF_UP);
        return formatAmount(bVal.doubleValue(), COMMA_DELIMITER_WITH_DECIMAL);
    }

    public static String formatAmount(Long value, String format) {
        if (value == null || StringUtils.isEmpty(format)) {
            return "";
        }

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat(format, dfs);

        return df.format(value);
    }

    public static String formatAmount(String value) {
        return formatAmount(Double.valueOf(value), COMMA_DELIMITER_WITH_DECIMAL);
    }

    public static String formatAmount(String value, String format) {
        return formatAmount(Double.valueOf(value), format);
    }

    public static String formatAmountWithCurrency(BigDecimal value, String currency) {
        return currency + " " + formatAmount(value, COMMA_DELIMITER_WITH_DECIMAL);
    }


    public static String formatAmountWithCurrency(BigDecimal value, String format, String currency) {
        return currency + " " + formatAmount(value, format);
    }

    public static String formatAmountWithCurrency(Double value, String currency) {
        return currency + " " + formatAmount(value, COMMA_DELIMITER_WITH_DECIMAL);
    }

    public static String formatAmountWithCurrency(Double value, String format, String currency) {
        return currency + " " + formatAmount(value, format);
    }

    public static String formatAmountWithCurrency(Long value, String currency) {
        return currency + " " + formatAmount(value, COMMA_DELIMITER_WITH_DECIMAL);
    }


    public static String formatAmountWithCurrency(Long value, String format, String currency) {
        return currency + " " + formatAmount(value, format);
    }

    public static String formatAmountWithCurrencyRightPad(Long value, String format, String currency) {
        return formatAmount(value, format) + " " + currency;
    }

    public static String formatAmountWithCurrency(String value, String currency) {
        return currency + " " + formatAmount(value, COMMA_DELIMITER_WITH_DECIMAL);
    }

    public static String formatAmountWithCurrency(String value, String format, String currency) {
        return currency + " " + formatAmount(value, format);
    }

    public static void main(String[] args) {
        System.out.println(formatAmountWithCurrency(10000l, COMMA_DELIMITER_WITHOUT_DECIMAL, "IDR"));
    }
}
