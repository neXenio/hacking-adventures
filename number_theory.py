import math
import gmpy2
from gmpy2 import root
from sympy.ntheory import factorint
from numpy import prod
gmpy2.get_context().precision = 4096


def factorize(n: int):
    return factorint(n)


def gcd(a: int, b: int):
    return math.gcd(a, b)


# adapted from https://rosettacode.org/wiki/Chinese_remainder_theorem#Python
def chinese_remainder_theorem(a_items, n_items):
    assert len(a_items) == len(n_items)

    n_prod = prod(n_items)

    result = 0
    for a, n in zip(a_items, n_items):
        m = n_prod // n
        result += a * pow(m, -1, n) * m

    return result % n_prod


def cube_root(n: int):
    return int(root(n, 3))
