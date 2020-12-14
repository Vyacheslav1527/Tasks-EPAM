package ua.nure.kulychenko.practice7.entity;

/**
 * Implements the Price entity.
 *
 * @author M.Veres
 */
public class Price {
    private String type;
    private String netBorder;
    private Payment payment;

    public Price() {}

    public Price(String type, String netBorder, Payment payment) {
        this.type = type;
        this.netBorder = netBorder;
        this.payment = payment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNetBorder() {
        return netBorder;
    }

    public void setNetBorder(String netBorder) {
        this.netBorder = netBorder;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Price{" +
                "type='" + type + '\'' +
                ", netBorder='" + netBorder + '\'' +
                ", payment=" + payment +
                '}';
    }

}
