package Chapter3_Writing_Order_TDD;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addMonth = payData.getPayAmount() / 10_000 == 10 ? 12: payData.getPayAmount() / 10_000;
        if (payData.getPayAmount() / 10_000 > 10) {
            addMonth += (payData.getPayAmount() - 100_000) / 10_000;
        }
        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addMonth);
        } else {
            return payData.getBillingDate().plusMonths(addMonth);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addMonths);
        final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
        final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
        if (payData.getBillingDate().getDayOfMonth() != dayOfFirstBilling) {
            if (dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

}
