package com.example.mind.findcurrencyexa;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    MainActivity mActivity;
    Button btn_find;
    EditText et_location_name, et_country_code, et_countyISO, et_currencyCode;
    TextView tv_currencySymbol, tv_currencyDisplayName, tv_currencyCode, tv_fractionDigits, tv_numericCode, tv_noDataFound;
    FrameLayout fl_bottomContents;

    Set<Currency> availableCurrenciesSet;
    List<Currency> currencyList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        // retain this fragment
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_find = (Button) view.findViewById(R.id.btn_find);
        et_location_name = (EditText) view.findViewById(R.id.et_location_name);
        et_country_code = (EditText) view.findViewById(R.id.et_country_code);
        et_countyISO = (EditText) view.findViewById(R.id.et_countyISO);
        et_currencyCode = (EditText) view.findViewById(R.id.et_currencyCode);
        tv_currencySymbol = (TextView) view.findViewById(R.id.tv_currencySymbol);
        tv_currencyDisplayName = (TextView) view.findViewById(R.id.tv_currencyDisplayName);
        tv_currencyCode = (TextView) view.findViewById(R.id.tv_currencyCode);
        tv_fractionDigits = (TextView) view.findViewById(R.id.tv_fractionDigits);
        tv_numericCode = (TextView) view.findViewById(R.id.tv_numericCode);
        tv_noDataFound = (TextView) view.findViewById(R.id.tv_noDataFound);
        fl_bottomContents = (FrameLayout) view.findViewById(R.id.fl_bottomContents);

        availableCurrenciesSet = Currency.getAvailableCurrencies();
        currencyList = new ArrayList<>(availableCurrenciesSet);
        btn_find.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_find:
                String loc = et_location_name.getText().toString().trim();
                String countryCode = et_country_code.getText().toString().trim();
                String countryISOCode = et_countyISO.getText().toString().trim();
                String currencyCode = et_currencyCode.getText().toString().trim();

                if (!loc.trim().equalsIgnoreCase("") || !countryCode.trim().equalsIgnoreCase("") || !countryISOCode.trim().equalsIgnoreCase("")) {
                    Locale[] locs = Locale.getAvailableLocales();
                    for (Locale locale : locs) {
                        try {
                            if (locale.getDisplayCountry().equalsIgnoreCase(loc) || locale.getCountry().equalsIgnoreCase(countryCode) ||
                                    locale.getISO3Country().equalsIgnoreCase(countryISOCode)) {
                                //hideNoDataFound();
                                tv_currencySymbol.setText(Currency.getInstance(locale).getSymbol());
                                tv_currencyDisplayName.setText("Display Name: ".concat(Currency.getInstance(locale).getDisplayName()));
                                tv_currencyCode.setText("Currency Code: ".concat(Currency.getInstance(locale).getCurrencyCode()));
                                tv_fractionDigits.setText("Fraction Digits: ".concat(String.valueOf(Currency.getInstance(locale).getDefaultFractionDigits())));
                                tv_numericCode.setText("Numeric Code: ".concat(String.valueOf(Currency.getInstance(locale).getNumericCode())));
                            } else {
                                //showNoDataFound();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (!currencyCode.equalsIgnoreCase(null) || !currencyCode.equalsIgnoreCase("")) {
                    for (int i = 0; i < currencyList.size(); i++) {
                        if (currencyList.get(i).getCurrencyCode().equalsIgnoreCase(currencyCode)) {
                            //hideNoDataFound();
                            tv_currencySymbol.setText(currencyList.get(i).getSymbol());
                            tv_currencyDisplayName.setText("Display Name: ".concat(currencyList.get(i).getDisplayName()));
                            tv_currencyCode.setText("Currency Code: ".concat(currencyList.get(i).getCurrencyCode()));
                            tv_fractionDigits.setText("Fraction Digits: ".concat(String.valueOf(currencyList.get(i).getDefaultFractionDigits())));
                            tv_numericCode.setText("Numeric Code: ".concat(String.valueOf(currencyList.get(i).getNumericCode())));
                        } else {
                            //showNoDataFound();
                        }
                    }
                }
                break;
        }
    }

    private void showNoDataFound() {
        tv_noDataFound.setVisibility(View.VISIBLE);
        // fl_bottomContents.setVisibility(View.GONE);
    }

    private void hideNoDataFound() {
        tv_noDataFound.setVisibility(View.GONE);
        //fl_bottomContents.setVisibility(View.VISIBLE);
    }
}
