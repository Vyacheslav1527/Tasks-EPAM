package ua.nure.kulychenko.practice7.entity;

import java.util.Objects;

/**
 * Implements the Payment entity.
 *
 * @author M.Veres
 */
public class Payment implements Comparable<Payment> {
    private String scale;
    private String currency;
    private double value;

    public Payment() {
    }

    public Payment(String scale, String currency, double value) {
        this.scale = scale;
        this.currency = currency;
        this.value = value;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "scale='" + scale + '\'' +
                ", currency='" + currency + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Payment o) {
        return ("" + currency + value).compareTo("" + o.currency + o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Payment payment = (Payment) o;
        boolean comparedEquals = Double.compare(payment.value, value) == 0;
        boolean scaleEquals = Objects.equals(scale, payment.scale);
        boolean currencyEquals = Objects.equals(currency, payment.currency);
        return comparedEquals && scaleEquals && currencyEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scale, currency, value);
    }
}
