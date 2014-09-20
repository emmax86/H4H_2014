import hashlib
import time


TYPE_USER = 0
TYPE_ADMIN = 1


#TODO: remove random element from seed generation
def gen_key(username, key_type=TYPE_USER):
    current_time = str(int(round(time.time() * 1000)))
    sig = username + str(current_time)
    seed = (int(hashlib.sha512(sig).hexdigest(), 16)) >> 480
    key_bytes = [0, 0, 0, 0]
    key_bytes[0] = gen_key_byte(seed, 9, 3, 20)
    key_bytes[1] = gen_key_byte(seed, 5, 0, 6)
    key_bytes[2] = gen_key_byte(seed, 1, 2, 91)
    key_bytes[3] = gen_key_byte(seed, 7, 1, 100)
    key = hex(seed).replace('0x', '').replace('L', '')
    for i in range(4):
        result = hex(key_bytes[i]).replace('0x', '').replace('L', '')
        while len(result) < 2:
            result = '0' + result
        key += result
    key += checksum(key, key_type)
    i = len(key) - 4
    while i > 1:
        key = key[:i] + '-' + key[i:]
        i -= 4
    return key.upper()


def gen_key_byte(seed, a, b, c):
    a %= 25
    b %= 3
    if a % 2 == 0:
        return ((seed >> a) & 0x000000FF) ^ ((seed >> b) | c)
    else:
        return ((seed >> a) & 0x000000FF) ^ ((seed >> b) & c)


def checksum(key_partial, key_type):
    a = 0
    b = 0
    sum_ = 0
    for i in range(len(key_partial)):
        b += ord(key_partial[i])
        if b > 0x00FF:
            b -= 0x00FF
        a += b
        if a > 0x00FF:
            a -= 0x00FF
    if key_type == TYPE_USER:
        sum_ = (a << 8) + b
    elif key_type == TYPE_ADMIN:
        sum_ = (b << 8) + a
    result = hex(sum_).replace('0x', '')
    while len(result) < 4:
        result = '0' + result
    return result


def check_key(key):
    key = key.lower().replace('-', '')
    if len(key) != 20:
        return [False, None]
    chksm = key[16:]
    key = key[:16]
    if checksum(key, TYPE_USER) == chksm:
        return [True, TYPE_USER]
    elif checksum(key, TYPE_ADMIN) == chksm:
        return [True, TYPE_ADMIN]
    else:
        return [False, None]


def is_key(key):
    if len(key) != 24:
        return False
    if key.count('-') != 4:
        return False
    key = key.lower().replace('-', '')
    valid_chars = '1234567890abcdef'
    for char in key:
        if char not in valid_chars:
            return False
    return True