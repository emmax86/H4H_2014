import argparse
from time import sleep
from os import urandom, path

from keygen import gen_key, TYPE_USER, TYPE_ADMIN


""" Let's add some arguments. """
parser = argparse.ArgumentParser(description='Generate Nero access keys.', prog='jenny')
parser.add_argument('-u', help='Count of user keys', type=int)
parser.add_argument('-a', help='Count of admin keys', type=int)
parser.add_argument('-output', help='File to output to', type=str)
args = parser.parse_args()


""" And now, we generate the keys. """
user_keys = []
admin_keys = []
if args.u is not None:
    print 'Generating {} user keys...'.format(args.u)
    for i in range(args.u):
        name_seed = urandom(16).encode('base-64')
        user_keys.append(gen_key(name_seed, TYPE_USER))
        sleep(.001)

if args.a is not None:
    print 'Generating {} admin keys...'.format(args.a)
    for i in range(args.a):
        name_seed = urandom(16).encode('base-64')
        admin_keys.append(gen_key(name_seed, TYPE_ADMIN))
        sleep(.001)


""" Output to file or console. """
if args.output is not None:
    print 'Outputting to file {}...'.format(args.output)
    output_file = open(args.output, 'w')
    if len(user_keys) > 0:
        output_file.write('------------------------\n'
                          '-------User keys:-------\n'
                          '------------------------\n')
        for key in user_keys:
            output_file.write(key + '\n')
        output_file.write('\n')

    if len(admin_keys) > 0:
        output_file.write('------------------------\n'
                          '-------Admin keys:------\n'
                          '------------------------\n')
        for key in admin_keys:
            output_file.write(key + '\n')

else:
    if len(user_keys) > 0:
        print '------------------------\n' \
              '-------User keys:-------\n' \
              '------------------------'
        for key in user_keys:
            print key
        print ''

    if len(admin_keys) > 0:
        print '------------------------\n' \
              '-------Admin keys:------\n' \
              '------------------------'
        for key in admin_keys:
            print key
        print ''