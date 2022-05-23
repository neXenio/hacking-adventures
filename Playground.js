// ASCII
function asciiToHex(ascii) { return bufferToHex(asciiToBuffer(ascii)) }
function asciiToBase64(ascii) { return bufferToBase64(asciiToBuffer(ascii)) }
function asciiToBuffer(ascii) { return Buffer.from(ascii, 'ascii') }

// HEX
function hexToAscii(hex) { return bufferToAscii(hexToBuffer(hex)) }
function hexToBase64(hex) { return bufferToBase64(hexToBuffer(hex)) }
function hexToBuffer(hex) { return Buffer.from(hex, 'hex') }

// BASE64
function base64ToAscii(base64) { return bufferToAscii(base64ToBuffer(base64))}
function base64ToHex(base64) { return bufferToHex(base64ToBuffer(base64))}
function base64ToBuffer(base64) { return Buffer.from(base64, 'base64'); }

// Bytes
function bufferToAscii(buffer) { return buffer.toString('ascii') }
function bufferToHex(buffer) { return buffer.toString('hex') }
function bufferToBase64(buffer) { return buffer.toString('base64') }


console.log(asciiToHex("SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"))
