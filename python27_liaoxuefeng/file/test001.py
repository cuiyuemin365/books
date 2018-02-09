#!/usr/bin/env python
# -*- coding: utf-8 -*-

f = open('/Users/cuiyuemin/Desktop/51_city_function.properties', 'r')
to = open('/Users/cuiyuemin/Desktop/51', 'w')
for line in f.readlines():
    to.write("'" + line.replace('\n','') + "',\n")
f.close()
to.flush()
to.close()
