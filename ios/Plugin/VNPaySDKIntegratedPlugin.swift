import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(VNPaySDKIntegratedPlugin)
public class VNPaySDKIntegratedPlugin: CAPPlugin {
    private let implementation = VNPaySDKIntegrated()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
}
