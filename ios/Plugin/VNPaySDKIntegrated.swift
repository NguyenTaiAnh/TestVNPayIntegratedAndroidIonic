import Foundation

@objc public class VNPaySDKIntegrated: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
