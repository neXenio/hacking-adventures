import Foundation

public struct ConvertTests {
    public static func run() {
        testAsciiToHex()
        testAsciiToBase64()
        testHexToAscii()
        testHexToBase64()
        testBase64ToAscii()
        testBase64ToHex()
        testAsciiConversionAndBack()
        testHexConversionAndBack()
        testBase64ConversionAndBack()

        print("ConvertTests passed!")
    }
    
    private static func testAsciiToHex() {
        assert(Convert.asciiToHex("Hello World!") == "48656c6c6f20576f726c6421",
               "ASCII to HEX conversion failed")
    }
    
    private static func testAsciiToBase64() {
        assert(Convert.asciiToBase64("Hello World!") == "SGVsbG8gV29ybGQh",
               "ASCII to BASE64 conversion failed")
    }
    
    private static func testHexToAscii() {
        assert(Convert.hexToAscii("48656c6c6f20576f726c6421") == "Hello World!",
               "HEX to ASCII conversion failed")
    }
    
    private static func testHexToBase64() {
        let hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
        let base64 = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
        assert(Convert.hexToBase64(hex) == base64,
               "HEX to BASE64 conversion failed")
    }
    
    private static func testBase64ToAscii() {
        assert(Convert.base64ToAscii("SGVsbG8gV29ybGQh") == "Hello World!",
               "BASE64 to ASCII conversion failed")
    }
    
    private static func testBase64ToHex() {
        let base64 = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
        let hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
        
        assert(Convert.base64ToHex(base64) == hex,
               "BASE64 to HEX conversion failed")
    }
    
    private static func testAsciiConversionAndBack() {
        for string in ["test", "foobar", ""] {
            assert(Convert.hexToAscii(Convert.asciiToHex(string)) == string)
            assert(Convert.base64ToAscii(Convert.asciiToBase64(string)) == string)
        }
    }
    
    private static func testHexConversionAndBack() {
        for string in ["48656c6c6f20576f726c6421", "666f6f626172", ""] {
            assert(Convert.asciiToHex(Convert.hexToAscii(string)) == string)
            assert(Convert.base64ToHex(Convert.hexToBase64(string)) == string)
        }
    }
    
    private static func testBase64ConversionAndBack() {
        for string in ["SGVsbG8gV29ybGQh", "Zml6emJ1eno=", ""] {
            assert(Convert.asciiToBase64(Convert.base64ToAscii(string)) == string)
            assert(Convert.hexToBase64(Convert.base64ToHex(string)) == string)
        }
    }
}
