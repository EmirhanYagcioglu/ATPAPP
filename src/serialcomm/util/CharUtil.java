package serialcomm.util;

public class CharUtil {
    public static byte hexString_to_byte(String hexString) {
        char first_hex_digit = hexString.charAt(0);
        char second_hex_digit = hexString.charAt(1);
        return (byte) (get_char_value(first_hex_digit) * 16 + get_char_value(second_hex_digit));
    }

    public static byte get_char_value(char ch) {
        byte by = (byte) ch;
        switch (by){
            case 48:
                by = 0;
                break;
            case 49:
                by = 1;
                break;
            case 50:
                by = 2;
                break;
            case 51:
                by = 3;
                break;
            case 52:
                by = 4;
                break;
            case 53:
                by = 5;
                break;
            case 54:
                by = 6;
                break;
            case 55:
                by = 7;
                break;
            case 56:
                by = 8;
                break;
            case 57:
                by = 9;
                break;
            case 65, 97:
                by = 10;
                break;
            case 66, 98:
                by = 11;
                break;
            case 67, 99:
                by = 12;
                break;
            case 68, 100:
                by = 13;
                break;
            case 69, 101:
                by = 14;
                break;
            case 70, 102:
                by = 15;
                break;
        }
        return by;
    }
}
