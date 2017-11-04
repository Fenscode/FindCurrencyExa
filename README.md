![](https://i.imgur.com/iyRpn4p.jpg)

# FindCurrencyExa
### Search currency information by Country, Country code,ISO3 Country code and Currency code
Gets the set of available currencies. The returned set of currencies contains all of the available currencies, which may include currencies that represent obsolete ISO 4217 codes

### Click on below link for see live demo:
https://goo.gl/cYD1Gm

###### Java provide a Currency utility class, which is returns list of currency information of all countries.

e.g.

```java
import java.util.Currency;

*******************************

Set<Currency> availableCurrenciesSet;
List<Currency> currencyList;

availableCurrenciesSet = Currency.getAvailableCurrencies();
currencyList = new ArrayList<>(availableCurrenciesSet);

  for (int i = 0; i < currencyList.size(); i++) {
      if (currencyList.get(i).getCurrencyCode().equalsIgnoreCase(currencyCode)) {
          
           tv_currencySymbol.setText(currencyList.get(i).getSymbol());
           tv_currencyDisplayName.setText("Display Name: ".concat(currencyList.get(i).getDisplayName()));
           tv_currencyCode.setText("Currency Code: ".concat(currencyList.get(i).getCurrencyCode()));
           tv_fractionDigits.setText("FractionDigits:".concat(String.valueOf(currencyList.get(i).getDefaultFractionDigits())));
           tv_numericCode.setText("Numeric Code: ".concat(String.valueOf(currencyList.get(i).getNumericCode())));
       }
}
************************
```
