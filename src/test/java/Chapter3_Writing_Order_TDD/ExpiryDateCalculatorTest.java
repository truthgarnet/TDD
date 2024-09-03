package Chapter3_Writing_Order_TDD;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    @Test
    void pay_10000_won() {
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 3, 1)).payAmount(10_000).build(), LocalDate.of(2019, 4,1));
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 6, 5)).payAmount(10_000).build(), LocalDate.of(2019, 7,5));
    }

    @Test
    void pay_10000_won_not_after_1_month() {
        // 01/31 -> 02/28 딱 30일 뒤는 아니지만, plusMonths는 처리 해준다.
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 1, 31)).payAmount(10_000).build(), LocalDate.of(2019, 2,28));
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019, 5, 31)).payAmount(10_000).build(), LocalDate.of(2019, 6,30));
    }

    @Test
    void first_Billing_different_next_Billing() {
        PayData payData = PayData.builder().firstBillingDate(LocalDate.of(2019, 1, 31)).payAmount(10_000).billingDate(LocalDate.of(2019, 2, 28)).build();
        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

        PayData payData2 = PayData.builder().firstBillingDate(LocalDate.of(2019, 1, 30)).payAmount(10_000).billingDate(LocalDate.of(2019, 2, 28)).build();
        assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

        PayData payData3 = PayData.builder().firstBillingDate(LocalDate.of(2019, 5, 31)).payAmount(10_000).billingDate(LocalDate.of(2019, 6, 30)).build();
        assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
    }

    @Test
    void over_20000_won() {
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019,3,1)).payAmount(20_000).build(), LocalDate.of(2019, 5, 1));
        assertExpiryDate(PayData.builder().billingDate(LocalDate.of(2019,3,1)).payAmount(30_000).build(), LocalDate.of(2019, 6, 1));
        assertExpiryDate(PayData.builder().firstBillingDate(LocalDate.of(2019,1,31)).billingDate(LocalDate.of(2019,2,28)).payAmount(20_000).build(), LocalDate.of(2019, 4, 30));
    }

    @Test
    void pay_100000_won() {
        PayData payData = PayData.builder().payAmount(100_000).billingDate(LocalDate.of(2019, 1, 28)).build();
        assertExpiryDate(payData, LocalDate.of(2020, 1, 28));
    }

    @Test
    void over_100000_won() {
        PayData payData = PayData.builder().payAmount(130_000).billingDate(LocalDate.of(2019, 1, 28)).build();
        assertExpiryDate(payData, LocalDate.of(2020, 5, 28));
    }

    public void assertExpiryDate(PayData payData, LocalDate expiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expiryDate, realExpiryDate);
    }
}
