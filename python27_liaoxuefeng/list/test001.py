#!/usr/bin/env python
# -*- coding: utf-8 -*-

classmates = ['C', 'C++', 'Python', 'Java']
print classmates
print len(classmates)
print classmates[0]
print classmates[1]
# print classmates[100]
# 最后一个元素
print classmates[-1]
classmates.append('javascript')
print classmates
classmates.insert(3, 'savew')
print classmates
# 删除最后一个元素
classmates.pop()
# 删除指定索引元素
classmates.pop(2)
# 替换一个元素
classmates[1] = 'saovneownevoawosv'
print classmates

L = ['Apple', 12, True]
print L

s = ['python', 'java', ['asp', 'php'], 'scheme']
print s[2][1]
