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

function shiftBuffer(buffer, stepSize) {
  for (let i = 0; i<buffer.length; i++) {
    buffer[i] = (buffer[i] + stepSize) % 128; 
  }
  return buffer;
}

function xorWithWrapAround(buffer, hexValue) {
  const buf1 = Buffer.from(buffer, 'hex');
  const buf2 = Buffer.from(hexValue, 'hex');
  const bufResult = buf1.map((b, i) => b ^ buf2[i % buf2.length]);
  return bufferToHex(bufResult);
}

function solutionScore(asciiString) {
  const matchAll = asciiString.matchAll(/[a-zA-Z ]/g)
  let matches = Array.from(matchAll,(matchArray) => matchArray[0]);
  return matches.length;
}

// Ciphertexts
// adapted from https://cryptopals.com/sets/1/challenges/3
// value is hex encoded
const CHALLENGE_1_A = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"

// adapted from https://id0-rsa.pub/problem/32/
// value is ASCII encoded
const CHALLENGE_1_B = "ZNKIGKYGXIOVNKXOYGXKGRREURJIOVNKXCNOINOYXKGRRECKGQOSTUZYAXKNUCURJHKIGAYKOSZUURGFEZURUUQGZZNKCOQOVGMKGZZNKSUSKTZHAZOLOMAXKOZYMUZZUHKGZRKGYZROQKLOLZEEKGXYURJUXCNGZKBKXBGPJADLIVBAYKZNUYKRGYZZKTINGXGIZKXYGYZNKYURAZOUT"


// console.log(asciiToHex("SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"))


//////////////////
// Challenge 1A //
//////////////////
let results1A = [];
for (let i = 1; i < 128; i++) {
  results1A.push(hexToAscii(xorWithWrapAround(CHALLENGE_1_A, ""+i)));
}
results1A = results1A.sort((a,b) => solutionScore(a) - solutionScore(b));
results1A.forEach((string) => console.log(`Score: ${solutionScore(string)}, Value: ${string}`));
//console.log("Challenge 1A:", hexToAscii(xorWithWrapAround(CHALLENGE_1_A, asciiToHex("X"))));

//////////////////
// Challenge 1B //
//////////////////
let results1B = [];
for (let i = 1; i < 128; i++) {
  results1B.push(bufferToAscii(shiftBuffer(asciiToBuffer(CHALLENGE_1_B), i)));
}
results1B = results1B.sort((a,b) => solutionScore(a) - solutionScore(b));
results1B.forEach((string) => console.log(`Score: ${solutionScore(string)}, Value: ${string}`));


//console.log("Challenge 1B:", bufferToAscii(shiftBuffer(asciiToBuffer(CHALLENGE_1_B), 122)).replaceAll("?", "Y").replaceAll(";", "U").replaceAll("=", "W").replaceAll("@", "Z").replaceAll("<", "V"));
// Solution including replacements: VAJDU>FCPV
// Solution without replacements: <AJD;>FCP<

