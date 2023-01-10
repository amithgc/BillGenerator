package com.amithgc.fuelBill;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FuelPrices {
    private static Map<String, Object> prices = new HashMap<String, Object>();

    public static double getFuelPrice(String date) {
        if (prices.size() == 0) {
            fetchFuelPrices();
        }

        String val = (String) prices.get(date);
        if (val == null) {
            return AddContentToPDF.DEFAULT_FUEL_PRICE;
        }
        return Double.parseDouble((String) prices.get(date));
    }

    public static void fetchFuelPrices() {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://www.bankbazaar.com/fuel/fetchCommodityPriceHistory.html?landingPageNamespace=fuel/petrol-price-bangalore&daysCount=90")
                    .method("GET", null)
                    .addHeader("authority", "www.bankbazaar.com")
                    .addHeader("accept", "*/*")
                    .addHeader("accept-language", "en-IN,en-GB;q=0.9,en-US;q=0.8,en;q=0.7")
                    .addHeader("cookie", "f_vdate=\"2022-07-28 16:54:10\"; device=desktop; _gcl_au=1.1.588610695.1659007450; _ga=GA1.2.719481522.1659007451; ROUTE=\"2c956a72db0321bc\"; AKA_A2=A; JSESSIONID=32EA92F4C19C102647D189E1EEF55207.mp-prod-mfkc2; ubid=24d87b0c-218a-4039-b5ea-4bf09cb535ad; WT.sd_id=\"\"; WT_sd_id=\"\"; trckr=9a90863718527b85059d15b7a9a6974e; visitdate=2022.09.01-16.40.32; WT.mc_id=SEOGoogle; WT_mc_id=SEOGoogle; RT=\"z=1&dm=bankbazaar.com&si=i9m3li2k2o&ss=l7iy4k2y&sl=0&tt=0\"; _gid=GA1.2.248868485.1662030634; _dc_gtm_UA-5152316-1=1; _gat_UA-5152316-1=1; _we_wk_ss_lsf_=true; sessionWebEvent=sent; __gads=ID=9e72b71486576e05:T=1662030633:S=ALNI_MbRAgxLSfkpSCz-tke2Yuc_YZiNyw; __gpi=UID=0000095b266aeaf2:T=1662030633:RT=1662030633:S=ALNI_MZo-6O-gy5rbzBipjJI3q6d3Qg0-g; g_state={\"i_p\":1662037838375,\"i_l\":1}; l_vdate=\"2022-09-01 16:40:52\"; X-CSRF-TOKEN=e4f58303-7c83-4c78-a630-e6ff89f1e15e; notification_subscription=false; WT.mc_id=SEOGoogle; WT.sd_id=\"\"; WT_mc_id=SEOGoogle; WT_sd_id=\"\"; X-CSRF-TOKEN=1a72181b-ead7-4ef8-a880-c0b74bb6b651; l_vdate=\"2022-09-01 16:43:16\"; trckr=9a90863718527b85059d15b7a9a6974e; ubid=24d87b0c-218a-4039-b5ea-4bf09cb535ad")
                    .addHeader("newrelic", "eyJ2IjpbMCwxXSwiZCI6eyJ0eSI6IkJyb3dzZXIiLCJhYyI6Ijk3MDA3MyIsImFwIjoiMTgzNDgxOTYzMyIsImlkIjoiMDg4MzY0YjQ5Nzc1MzU4NSIsInRyIjoiNzBiNjVhNzcwYTYwZTU0YmVkYzFkMmFiNzIwOTU1NDYiLCJ0aSI6MTY2MjAzMDY2NDgyOH19")
                    .addHeader("referer", "https://www.bankbazaar.com/fuel/petrol-price-bangalore.html")
                    .addHeader("sec-ch-ua", "\"Chromium\";v=\"104\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"104\"")
                    .addHeader("sec-ch-ua-mobile", "?0")
                    .addHeader("sec-ch-ua-platform", "\"macOS\"")
                    .addHeader("sec-fetch-dest", "empty")
                    .addHeader("sec-fetch-mode", "cors")
                    .addHeader("sec-fetch-site", "same-origin")
                    .addHeader("traceparent", "00-70b65a770a60e54bedc1d2ab72095546-088364b497753585-01")
                    .addHeader("tracestate", "970073@nr=0-1-970073-1834819633-088364b497753585----1662030664828")
                    .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36")
                    .addHeader("x-newrelic-id", "XQEHUVFQGwIIV1VUBQQAVVM=")
                    .addHeader("x-requested-with", "XMLHttpRequest")
                    .build();
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            JSONObject obj = new JSONObject(data);
            prices = ((JSONObject) obj.get("Petrol")).toMap();


            System.out.println("Making API call to get Fuel Prices");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
