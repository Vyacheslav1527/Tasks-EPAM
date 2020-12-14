package ua.nure.kulychenko.practice7.entity;

/**
 * Implements the Parameter entity.
 *
 * @author M.Veres
 */
public class Parameter {
    private String type;
    private String unit;
    private String value;

    public Parameter() {}

    public Parameter(String type, String unit, String value) {
        this.type = type;
        this.unit = unit;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "type='" + type + '\'' +
                ", unit='" + unit + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
