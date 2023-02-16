package com.mycompany.plugins.example;

import android.content.Intent;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.NativePlugin;
import com.vnpay.authentication.VNP_AuthenticationActivity;
import com.vnpay.authentication.VNP_SdkCompletedCallback;
//import com.getcapacitor.annotation.CapacitorPlugin;

//@CapacitorPlugin(name = "VNPaySDKIntegrated")
@NativePlugin(
        requestCodes = {123123}
)
public class VNPaySDKIntegratedPlugin extends Plugin {

    private VNPaySDKIntegrated implementation = new VNPaySDKIntegrated();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");
        openSdk();
        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    public void openSdk() {
        Intent intent = new Intent(this.getContext(), VNP_AuthenticationActivity.class);
        intent.putExtra("url", "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?vnp_Amount=100000000&vnp_BankCode=VNBANK&vnp_Command=pay&vnp_CreateDate=20230216155512&vnp_CurrCode=VND&vnp_ExpireDate=20230216161012&vnp_IpAddr=172.18.0.1&vnp_Locale=vn&vnp_OrderInfo=Thanh+toan+GD%3A4742&vnp_OrderType=other&vnp_ReturnUrl=http%3A%2F%2F127.0.0.1%2Fvnpay_php%2Fvnpay_return.php&vnp_TmnCode=CATHAYAP&vnp_TxnRef=4742&vnp_Version=2.1.0&vnp_SecureHash=e3b6cf9952f6e3011a06639a8e4c90bb3755da293c604dee6a44dcf7b7eb90a0f5373d6b748ec70e5ee795c4758e3b0512d9fbb3afae120f762752d41034e2e7"); //bắt buộc, VNPAY cung cấp
        intent.putExtra("tmn_code", "FAHASA03"); //bắt buộc, VNPAY cung cấp
        intent.putExtra("scheme", "resultactivity"); //bắt buộc, scheme để mở lại app khi có kết quả thanh toán từ mobile banking
        intent.putExtra("is_sandbox", false); //bắt buộc, true <=> môi trường test, true <=> môi trường live
        VNP_AuthenticationActivity.setSdkCompletedCallback(new VNP_SdkCompletedCallback() {
            @Override
            public void sdkAction(String action) {
                Log.wtf("SplashActivity", "action: " + action);
                //action == AppBackAction
                //Người dùng nhấn back từ sdk để quay lại

                //action == CallMobileBankingApp
                //Người dùng nhấn chọn thanh toán qua app thanh toán (Mobile Banking, Ví...)
                //lúc này app tích hợp sẽ cần lưu lại cái PNR, khi nào người dùng mở lại app tích hợp thì sẽ gọi kiểm tra trạng thái thanh toán của PNR Đó xem đã thanh toán hay chưa.

                //action == WebBackAction
                //Người dùng nhấn back từ trang thanh toán thành công khi thanh toán qua thẻ khi url có chứa: cancel.sdk.merchantbackapp

                //action == FaildBackAction
                //giao dịch thanh toán bị failed

                //action == SuccessBackAction
                //thanh toán thành công trên webview
            }
        });
        this.getContext().startActivity(intent);
    }
}
