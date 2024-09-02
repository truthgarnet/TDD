package Chapter2_Start_TDD;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String meter) {
        if (meter == null || meter.isEmpty()) {
            return PasswordStrength.INVALID;
        }

        int metCounts = getMetCriteriaCounts(meter);

        if (metCounts <= 1) {
            return PasswordStrength.WEAK;
        } else if (metCounts == 2) {
            return PasswordStrength.NORMAL;
        } else {
            return PasswordStrength.STRONG;
        }
    }

    private int getMetCriteriaCounts(String meter) {
        int metCounts = 0;

        if (meter.length() >= 8) metCounts++;
        if (meetsContainingUppercase(meter)) metCounts++;
        if (meetsContainingNumberCriteria(meter)) metCounts++;

        return metCounts;
    }

    public boolean meetsContainingNumberCriteria(String meter) {
        boolean isNumber = false;

        for (char ch : meter.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                isNumber = true;
                break;
            }
        }
        return isNumber;
    }

    public boolean meetsContainingUppercase(String meter) {
        boolean isUppercase = false;

        for (char ch : meter.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                isUppercase = true;
                break;
            }
        }
        return isUppercase;
    }
}
