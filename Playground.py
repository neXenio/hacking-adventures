import base64
from enum import Enum


# ascii
def ascii_to_hex(ascii: str): return bytes_to_hex(ascii_to_bytes(ascii))
def ascii_to_base64(ascii: str): return bytes_to_base64(ascii_to_bytes(ascii))
def ascii_to_bytes(ascii: str): return str.encode(ascii)
# hex
def hex_to_ascii(hex: str): return bytes_to_ascii(hex_to_bytes(hex))
def hex_to_base64(hex: str): return bytes_to_base64(hex_to_bytes(hex))
def hex_to_bytes(hex: str): return bytes.fromhex(hex)
# base64
def base64_to_ascii(b64: str): return bytes_to_ascii(base64_to_bytes(b64))
def base64_to_hex(b64: str): return bytes_to_hex(base64_to_bytes(b64))
def base64_to_bytes(b64: str): return base64.b64decode(b64)
# bytes
def bytes_to_ascii(bytes: bytes): return bytes.decode()
def bytes_to_hex(bytes: bytes): return bytes.hex()
def bytes_to_base64(bytes: bytes): return base64.b64encode(bytes).decode()


def xor_hex(a: str, b: str):
    bytes_a = hex_to_bytes(a)
    bytes_b = hex_to_bytes(b)
    bytes_c = bytes(a ^ b for (a, b) in zip(bytes_a, bytes_b))
    return bytes_to_hex(bytes_c)


class Ciphertexts(Enum):
    # adapted from https://cryptopals.com/sets/1/challenges/3
    CHALLENGE_1_A = '1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736'
    # adapted from https://id0-rsa.pub/problem/32/
    CHALLENGE_1_B = 'ZNKIGKYGXIOVNKXOYGXKGRREURJIOVNKXCNOINOYXKGRRECKGQOSTUZYAXKNUCURJHKIGAYKOSZUURGFEZURUUQGZZNKCOQOVGMKGZZNKSUSKTZHAZOLOMAXKOZYMUZZUHKGZRKGYZROQKLOLZEEKGXYURJUXCNGZKBKXBGPJADLIVBAYKZNUYKRGYZZKTINGXGIZKXYGYZNKYURAZOUT'


if __name__ == '__main__':
    print('Hello World!')
