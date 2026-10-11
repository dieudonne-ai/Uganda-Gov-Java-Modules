package gov.ug.ura.internal;

public class TaxCalculator {
    public static long payeTax(long monthlyIncome) {
        if (monthlyIncome <= 235000) return 0;
        if (monthlyIncome <= 335000) return (monthlyIncome - 235000) * 10 / 100;
        if (monthlyIncome <= 410000) return 10000 + (monthlyIncome - 335000) * 20 / 100;
        return 25000 + (monthlyIncome - 410000) * 30 / 100;
    }
}
