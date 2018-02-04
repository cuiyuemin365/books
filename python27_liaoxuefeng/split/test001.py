#!/usr/bin/env python
# -*- coding: utf-8 -*-
L = [1, 2, 3, 4, 5, 6]

# 前闭后开
print L[0:3]
print L[:3]
print L[1:3]
print L[-2:-1]
print L[-2:]

K = range(100)
# 所有数列每5个选一个
print K[::5]

# tuple 也可以分片
print (0, 1, 2, 3, 4, 5)[:3]

# 字符串也可以分片
print 'ABCDEFG'[:3]

L = ['Hello', 'World', 18, 'Apple', None]
print [s.lower() if isinstance(s, str) else s for s in L]

print [x * x if x % 2 == 0 else x for x in range(1, 11)]
