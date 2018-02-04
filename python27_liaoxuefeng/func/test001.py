#!/usr/bin/env python
# -*- coding: utf-8 -*-

i = 17
print hex(i)


def cal(*nums):
    sum = 0
    for num in nums:
        sum += num
    return sum


nums = [1, 4, 567, 773, 2435, 234]
print cal(*nums)
