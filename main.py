from challenge import ciphertexts, rsa_keys
from rsa import generate_rsa_keypair, RsaSecretKey


def demo():
    test_phrase = 'Lorem ipsum dolor sit amet'
    sk, pk = generate_rsa_keypair(128)

    print('generated key pair')
    print('----')
    print('public key (used for encryption):')
    print('exponent e =', pk.e)
    print('modulus  n =', pk.n)
    print('private key (used for decryption):')
    print('exponent d =', sk.d)
    print('modulus  n =', sk.n)
    print('----')

    ciphertext = pk.encrypt(test_phrase)
    print('encrypted  "', test_phrase, '" to "', ciphertext, '"')

    deciphered = sk.decrypt(ciphertext)
    print('deciphered "', ciphertext, '" to "', deciphered, '"')


def rsa_cracking_attempt():
    ciphertext_64_1 = ciphertexts[64][0]
    public_key_64_1 = rsa_keys[64][0]

    # TODO: figure out d
    d = 1337
    secret_key_64_1 = RsaSecretKey(d=d, n=public_key_64_1.n)

    deciphered = secret_key_64_1.decrypt(ciphertext_64_1)
    if deciphered is not None:
        print('deciphered "', ciphertext_64_1, '" to "', deciphered)
    else:
        print('d =', d, 'is not the right key')


if __name__ == '__main__':
    demo()

