# ascii
def ascii_to_hex(ascii: str): return bytes_to_hex(ascii_to_bytes(ascii))
def ascii_to_bytes(ascii: str): return str.encode(ascii)
# hex
def hex_to_ascii(hex: str): return bytes_to_ascii(hex_to_bytes(hex))
def hex_to_bytes(hex: str): return bytes.fromhex(hex)
# decimal
def hex_to_dec(hex: str): return int(hex, 16)
def dec_to_hex(dec: int): return format(dec, 'x')
# bytes
def bytes_to_ascii(bytes: bytes): return bytes.decode()
def bytes_to_hex(bytes: bytes): return bytes.hex()


def message_to_ints(message: str, modulus: int):
    block_length = len(dec_to_hex(modulus)) // 4
    message_parts = [message[i:i+block_length] for i in range(0, len(message), block_length)]
    return [hex_to_dec(ascii_to_hex(message_part)) for message_part in message_parts]
