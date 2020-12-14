package ua.nure.kulychenko.practice7.util;

import java.util.Comparator;
import java.util.List;

import ua.nure.kulychenko.practice7.entity.Parameter;
import ua.nure.kulychenko.practice7.entity.Price;
import ua.nure.kulychenko.practice7.entity.Tariff;


/**
 * Contains static methods for sorting entities.
 *
 * @author M.Veres
 */
public final class Sorter {

    private Sorter() {}

    /**
     * Compares tariffs by their name texts.
     */
    public static final Comparator<Tariff> SORT_TARIFFS_BY_NAME_TEXT = Comparator.comparing(Tariff::getName);

    /**
     * Compares tariffs by their operator names texts.
     */
    public static final Comparator<Tariff> SORT_TARIFFS_BY_OPERATOR_NAME_TEXT =
            Comparator.comparing(Tariff::getOperatorName);

    /**
     * Compares tariffs by their payrolls.
     */
    public static final Comparator<Tariff> SORT_TARIFFS_BY_PAYROLL = Comparator.comparing(Tariff::getPayroll);

    /**
     * Compares prices by their payments.
     */
    public static final Comparator<Price> SORT_PRICES_BY_PAYMENT = Comparator.comparing(Price::getPayment);

    /**
     * Compares prices by their type texts.
     */
    public static final Comparator<Price> SORT_PRICES_BY_TYPE_TEXT = Comparator.comparing(Price::getType);

    /**
     * Compares prices by their net border texts.
     */
    public static final Comparator<Price> SORT_PRICES_BY_NET_BORDER_TEXT = Comparator.comparing(Price::getNetBorder);

    /**
     * Compares parameters by their value texts.
     */
    public static final Comparator<Parameter> SORT_PARAMETERS_BY_VALUE_TEXT = Comparator.comparing(Parameter::getValue);

    /**
     * Compares parameters by their unit texts.
     */
    public static final Comparator<Parameter> SORT_PARAMETERS_BY_UNIT_TEXT = Comparator.comparing(Parameter::getUnit);

    /**
     * Compares parameters by their type texts.
     */
    public static final Comparator<Parameter> SORT_PARAMETERS_BY_TYPE_TEXT = Comparator.comparing(Parameter::getType);

    // //////////////////////////////////////////////////////////
    // these methods take Tariff object or List<Tariff> object and sort it
    // with according comparator
    // //////////////////////////////////////////////////////////

    public static void sortTariffsByNameText(List<Tariff> tariffs) {
        tariffs.sort(SORT_TARIFFS_BY_NAME_TEXT);
    }

    public static void sortTariffsByOperatorNameText(List<Tariff> tariffs) {
        tariffs.sort(SORT_TARIFFS_BY_OPERATOR_NAME_TEXT);
    }

    public static void sortTariffsByPayroll(List<Tariff> tariffs) {
        tariffs.sort(SORT_TARIFFS_BY_PAYROLL);
    }

    public static void sortPricesByPayment(List<Price> prices) {
        prices.sort(SORT_PRICES_BY_PAYMENT);
    }

    public static void sortPricesByTypeText(List<Price> prices) {
        prices.sort(SORT_PRICES_BY_TYPE_TEXT);
    }

    public static void sortPricesByNetBorderText(List<Price> prices) {
        prices.sort(SORT_PRICES_BY_NET_BORDER_TEXT);
    }

    public static void sortCallPricesByPayment(Tariff tariff) {
        tariff.getCallPrices().sort(SORT_PRICES_BY_PAYMENT);
    }

    public static void sortSmsPricesByPayment(Tariff tariff) {
        tariff.getSmsPrices().sort(SORT_PRICES_BY_PAYMENT);
    }

    public static void sortCallPricesByTypeText(Tariff tariff) {
        tariff.getCallPrices().sort(SORT_PRICES_BY_TYPE_TEXT);
    }

    public static void sortSmsPricesByTypeText(Tariff tariff) {
        tariff.getSmsPrices().sort(SORT_PRICES_BY_TYPE_TEXT);
    }

    public static void sortCallPricesByNetBorderText(Tariff tariff) {
        tariff.getCallPrices().sort(SORT_PRICES_BY_NET_BORDER_TEXT);
    }

    public static void sortSmsPricesByNetBorderText(Tariff tariff) {
        tariff.getSmsPrices().sort(SORT_PRICES_BY_NET_BORDER_TEXT);
    }

    public static void sortParametersByValueText(List<Parameter> parameters) {
        parameters.sort(SORT_PARAMETERS_BY_VALUE_TEXT);
    }

    public static void sortParametersByValueText(Tariff tariff) {
        tariff.getParameters().sort(SORT_PARAMETERS_BY_VALUE_TEXT);
    }

    public static void sortParametersByUnitText(List<Parameter> parameters) {
        parameters.sort(SORT_PARAMETERS_BY_UNIT_TEXT);
    }

    public static void sortParametersByUnitText(Tariff tariff) {
        tariff.getParameters().sort(SORT_PARAMETERS_BY_UNIT_TEXT);
    }

    public static void sortParametersByTypeText(List<Parameter> parameters) {
        parameters.sort(SORT_PARAMETERS_BY_TYPE_TEXT);
    }

    public static void sortParametersByTypeText(Tariff tariff) {
        tariff.getParameters().sort(SORT_PARAMETERS_BY_TYPE_TEXT);
    }

}
