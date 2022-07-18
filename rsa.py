from primes import generate_prime_number
from conversion import message_to_ints, dec_to_hex, hex_to_dec, hex_to_ascii
import sys


class RsaCipherText:
    def __init__(self, parts: [str]):
        self.parts = parts

    def __str__(self):
        return ' '.join(self.parts)


class RsaPublicKey:
    def __init__(self, e, n):
        self.e = e
        self.n = n

    def encrypt(self, plaintext: str):
        parts = message_to_ints(plaintext, self.n)
        encrypted_dec = [pow(part, self.e, self.n) for part in parts]
        encrypted_hex = [dec_to_hex(dec) for dec in encrypted_dec]

        return RsaCipherText(encrypted_hex)


class RsaSecretKey:
    def __init__(self, d, n):
        self.d = d
        self.n = n

    def decrypt(self, ciphertext: RsaCipherText):
        ciphertext_parts_hex = ciphertext.parts
        ciphertext_parts_dec = [hex_to_dec(hex) for hex in ciphertext_parts_hex]
        decrypted_parts_dec = [pow(enc, self.d, self.n) for enc in ciphertext_parts_dec]
        decrypted_parts_hex = [dec_to_hex(dec) for dec in decrypted_parts_dec]

        try:
            decrypted_parts_ascii = [hex_to_ascii(hex) for hex in decrypted_parts_hex]

            return ''.join(decrypted_parts_ascii)
        except Exception as error:
            print('An exception occurred, probably due to bad key: {}'.format(error), file=sys.stderr)
            return None


def generate_rsa_keypair(length=64):
    """ Generate an RSA key pair
        Args:
            length -- int -- RSA key size (1024 or higher recommended, lower values only for educational purposes)
        return key pair
    """
    prime_p = generate_prime_number(length=length//2)  # discarded
    prime_q = generate_prime_number(length=length//2)  # discarded
    euler_totient = (prime_p - 1) * (prime_q - 1)  # discarded

    modulus_n = prime_p * prime_q  # public
    pk_exponent_e = 65537  # public
    sk_exponent_d = pow(pk_exponent_e, -1, euler_totient)  # secret

    sk = RsaSecretKey(sk_exponent_d, modulus_n)
    pk = RsaPublicKey(pk_exponent_e, modulus_n)

    return sk, pk
