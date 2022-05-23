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


// XOR
function xor(hex1, hex2) {
  const buf1 = Buffer.from(hex1, 'hex');
  const buf2 = Buffer.from(hex2, 'hex');
  const bufResult = buf1.map((b, i) => b ^ buf2[i]);
  return bufferToHex(bufResult);
}

// Ciphertexts
// adapted from https://cryptopals.com/sets/1/challenges/3
const CHALLENGE_1_A = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
// adapted from https://id0-rsa.pub/problem/32/
const CHALLENGE_1_B = "ZNKIGKYGXIOVNKXOYGXKGRREURJIOVNKXCNOINOYXKGRRECKGQOSTUZYAXKNUCURJHKIGAYKOSZUURGFEZURUUQGZZNKCOQOVGMKGZZNKSUSKTZHAZOLOMAXKOZYMUZZUHKGZRKGYZROQKLOLZEEKGXYURJUXCNGZKBKXBGPJADLIVBAYKZNUYKRGYZZKTINGXGIZKXYGYZNKYURAZOUT"


console.log(asciiToHex("SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"))
