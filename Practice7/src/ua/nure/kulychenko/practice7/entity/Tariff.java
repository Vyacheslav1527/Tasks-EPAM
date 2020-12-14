package ua.nure.kulychenko.practice7.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Tariff entity.
 *
 * @author M.Veres
 */
public class Tariff {
    private String name;
    private String operatorName;
    private Payment payroll;
    private List<Price> callPrices;
    private List<Price> smsPrices;
    private List<Parameter> parameters;

    public Tariff() {
        callPrices = new ArrayList<>();
        smsPrices = new ArrayList<>();
        parameters = new ArrayList<>();
    }

    public Tariff(String name, String operatorName,
                  Payment payroll, List<Price> callPrices,
                  List<Price> smsPrices, List<Parameter> parameters) {
        this.name = name;
        this.operatorName = operatorName;
        this.payroll = payroll;
        this.callPrices = callPrices;
        this.smsPrices = smsPrices;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Payment getPayroll() {
        return payroll;
    }

    public void setPayroll(Payment payroll) {
        this.payroll = payroll;
    }

    public List<Price> getCallPrices() {
        return callPrices;
    }

    public void setCallPrices(List<Price> callPrices) {
        this.callPrices = callPrices;
    }

    public List<Price> getSmsPrices() {
        return smsPrices;
    }

    public void setSmsPrices(List<Price> smsPrices) {
        this.smsPrices = smsPrices;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", payroll=" + payroll +
                ", callPrices=" + callPrices +
                ", smsPrices=" + smsPrices +
                ", parameters=" + parameters +
                '}';
    }
}
