package Chapter2_Start_TDD;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    public void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("abcdefg!@A", PasswordStrength.NORMAL);
        assertStrength("abaa!Ad@#!", PasswordStrength.NORMAL);
    }

    @Test
    void meetsAllCriteria_then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("aa2!Ad", PasswordStrength.NORMAL);
    }

    @Test
    void meetsAllCriteria_then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("aab12!Add", PasswordStrength.STRONG);
    }

    @Test
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void meetsOtherCriteria_excpet_for_Uppercase_Then_Normoal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyNumberCriteria_Then_Weak() {
        assertStrength("012345", PasswordStrength.WEAK);
    }

    @Test
    void meetsOnlyUppercaseCriteria_Then_Weak() {
        assertStrength("ASVB", PasswordStrength.WEAK);
    }

    @Test
    void meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }

    @Test
    void emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

}
