#!/usr/bin/env python
# -*- coding: utf-8 -*-

# 位置参数
def power(x):
    return x * x


print power(8)


def power(x, n):
    s = 1
    while n > 0:
        n = n - 1
        s = s * x
    return s


print power(2, 4)


# 默认参数
def power(x, n=2):
    s = 1
    while n > 0:
        n = n - 1
        s = s * x
    return s


print power(7)
print power(7, 5)


# 可变参数
def calc(*numbers):
    sum = 0
    for n in numbers:
        sum = sum + n * n
    return sum


print calc(1, 2, 3, 7)
numbers = [1, 2, 3, 7]
print calc(*numbers)


# 关键字参数 字典参数
def person(name, age, **kw):
    print('name:', name, 'age:', age, 'other:', kw)


person('cuiyuemin', 23)
person('cuiyuemin', 23, scool='hafuo')
person('cuiyuemin', 23, scool='hafuo', work='java project')

extra = {'city': 'Beijing', 'job': 'Engineer'}
person('Jack', 24, **extra)


# 参数定义的顺序：位置参数，默认参数，可变参数（列表参数），关键字参数（字典参数）
def func(a, b, c=0, *args, **kw):
    print 'a =', a, 'b =', b, 'c =', c, 'args =', args, 'kw =', kw
