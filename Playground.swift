import Foundation

precedencegroup ForwardApplication {
    associativity: left
}

infix operator |>: ForwardApplication

func |> <A, B>(x: A, f: (A) -> B) -> B {
    return f(x)
}

struct Convert {
    // ASCII conversion
    static func asciiToHex(_ ascii: String) -> String {
        guard ascii != "" else { return "" }
        return ascii |> asciiToData |> dataToHex
    }
    
    static func asciiToBase64(_ ascii: String) -> String {
        guard ascii != "" else { return "" }
        return ascii |> asciiToData |> dataToBase64
    }
    
    static func asciiToData(_ ascii: String) -> Data {
        return Data(ascii.utf8)
    }
    
    // HEX conversion
    static func hexToAscii(_ hex: String) -> String {
        guard hex != "" else { return "" }
        return hex |> hexToData |> dataToAscii
    }
    
    static func hexToBase64(_ hex: String) -> String {
        guard hex != "" else { return "" }
        return hex |> hexToData |> dataToBase64
    }
    
    static func hexToData(_ hex: String) -> Data {
        return Data(hex: hex)!
    }
    
    // BASE64 conversion
    static func base64ToAscii(_ base64: String) -> String {
        guard base64 != "" else { return "" }
        return base64 |> base64ToData |> dataToAscii
    }
    
    static func base64ToHex(_ base64: String) -> String {
        guard base64 != "" else { return "" }
        return base64 |> base64ToData |> dataToHex
    }
    
    static func base64ToData(_ base64: String) -> Data {
        return Data(base64Encoded: base64)!
    }
    
    // Data conversion
    static func dataToAscii(_ data: Data) -> String {
        return String(data: data, encoding: .utf8)!
    }
    
    static func dataToHex(_ data: Data) -> String {
        return data.map { String(format: "%02hhx", $0) }.joined()
    }
    
    static func dataToBase64(_ data: Data) -> String {
        return data.base64EncodedString()
    }
}

private extension Data {
    init?(hex: String) {
        guard hex.count.isMultiple(of: 2) else { return nil }
        
        let chars = hex.map { $0 }
        let bytes = stride(from: 0, to: chars.count, by: 2)
            .map { String(chars[$0]) + String(chars[$0 + 1]) }
            .compactMap { UInt8($0, radix: 16) }
        
        guard hex.count / bytes.count == 2 else { return nil }
        self.init(bytes)
    }
}
