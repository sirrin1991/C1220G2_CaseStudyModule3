package model.service.common;

import java.util.regex.Pattern;

public class Validation {
    private static final String NAME_OR_ADDRESS = "^[A-Z][a-z]*(\\ [A-Z][a-z]*)*$";
    private static final String CUSTOMER_CODE = "^(KH-)\\d{4}$";
    private static final String SERVICE_CODE = "^(DV-)\\d{4}$";
    private static final String PHONE = "^(090|091|\\(\\+84\\)90|\\(\\+84\\)91)\\d{7}$";
    private static final String ID_CARD = "^(\\d{9}|\\d{12})$";
    private static final String EMAIL = "^[a-z]+([\\_\\.]?[a-z\\d]+)*@[a-z]{3,7}\\.[a-z]{3}$";
    private static final String FLOOR_OR_PEOPLE = "^[1-9]+\\d*$";
    private static final String ALL_MONEY = "^(([1-9]+\\d*)|0)\\.?\\d{1,2}$";

    public static boolean validateNameOrAddress(String string) {
        return Pattern.matches(NAME_OR_ADDRESS, string);
    }
    public static boolean validateCustomerCode(String string) {
        return Pattern.matches(CUSTOMER_CODE, string);
    }
    public static boolean validateServiceCode(String string) {
        return Pattern.matches(SERVICE_CODE, string);
    }
    public static boolean validatePhone(String string) {
        return Pattern.matches(PHONE, string);
    }
    public static boolean validateIdCard(String string) {
        return Pattern.matches(ID_CARD, string);
    }
    public static boolean validateEmail(String string) {
        return Pattern.matches(EMAIL, string);
    }
    public static boolean validateFloorOrPeople(String string) {
        return Pattern.matches(FLOOR_OR_PEOPLE, string);
    }
    public static boolean validateAllMoney(String string) {
        return Pattern.matches(ALL_MONEY, string);
    }

}
