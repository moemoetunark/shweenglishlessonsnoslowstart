package siam.moemoetun.com.shwedailyenglish.constant;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class AppConstant {
    private static final String PREF_COIN_BALANCE = "coin_balance";
    private static SharedPreferences sharedPreferences;

    // Observer pattern
    private static List<CoinBalanceObserver> observers = new ArrayList<>();

    public static void initialize(Context context) {
        sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
    }

    public static int getCoinBalance() {
        return sharedPreferences.getInt(PREF_COIN_BALANCE, 0);
    }

    public static void setCoinBalance(int coinBalance) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREF_COIN_BALANCE, coinBalance);
        editor.apply();

        // Notify all registered observers about the balance change
        for (CoinBalanceObserver observer : observers) {
            observer.onCoinBalanceChanged(coinBalance);
        }
    }

    // Observer pattern
    public static void registerCoinBalanceObserver(CoinBalanceObserver observer) {
        observers.add(observer);
    }

    public static void unregisterCoinBalanceObserver(CoinBalanceObserver observer) {
        observers.remove(observer);
    }

    // Observer pattern interface
    public interface CoinBalanceObserver {
        void onCoinBalanceChanged(int newBalance);
    }
}
