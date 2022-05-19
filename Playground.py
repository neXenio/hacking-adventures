import base64


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


if __name__ == '__main__':
    print('Hello World!')
