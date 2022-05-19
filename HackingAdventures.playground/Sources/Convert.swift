import Foundation

public struct Convert {
    // ASCII conversion
    public static func asciiToHex(_ ascii: String) -> String {
        guard ascii != "" else { return "" }
        let data = ascii.data(using: .ascii)!
        return dataToHex(data)
    }
    
    public static func asciiToBase64(_ ascii: String) -> String {
        guard ascii != "" else { return "" }
        let data = Data(ascii.utf8)
        return dataToBase64(data)
    }
    
    // HEX conversion
    public static func hexToAscii(_ hex: String) -> String {
        guard hex != "" else { return "" }
        let data = Data(hex: hex)!
        return dataToAscii(data)
    }
    
    public static func hexToBase64(_ hex: String) -> String {
        guard hex != "" else { return "" }
        let data = Data(hex: hex)!
        return data.base64EncodedString()
    }
    
    // BASE64 conversion
    public static func base64ToAscii(_ base64: String) -> String {
        guard base64 != "" else { return "" }
        let data = Data(base64Encoded: base64)!
        return dataToAscii(data)
    }
    
    public static func base64ToHex(_ base64: String) -> String {
        guard base64 != "" else { return "" }
        let data = Data(base64Encoded: base64)!
        return dataToHex(data)
    }
    
    // Data conversion
    private static func dataToAscii(_ data: Data) -> String {
        return String(data: data, encoding: .utf8)!
    }
    
    private static func dataToHex(_ data: Data) -> String {
        return data.map { String(format: "%02hhx", $0) }.joined()
    }
    
    private static func dataToBase64(_ data: Data) -> String {
        return data.base64EncodedString()
    }
}

private extension Data {
    init?(hex: String) {
        guard hex.count.isMultiple(of: 2) else {
            return nil
        }
        
        let chars = hex.map { $0 }
        let bytes = stride(from: 0, to: chars.count, by: 2)
            .map { String(chars[$0]) + String(chars[$0 + 1]) }
            .compactMap { UInt8($0, radix: 16) }
        
        guard hex.count / bytes.count == 2 else { return nil }
        self.init(bytes)
    }
}
