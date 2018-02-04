#!/usr/bin/env python
# -*- coding: utf-8 -*-


def prod(l):
    def muf(x, y):
        return x * y

    return reduce(muf, l)


print prod(range(1, 5))
