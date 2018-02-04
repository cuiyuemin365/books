#!/usr/bin/python
import re
import requests

response = requests.get('http://jobs.51job.com/beijing-hdq/98091713.html?s=01&t=0')
response.encoding = response.apparent_encoding
content = response.text
print 'jobName:%s' % re.search('id="tHeader_mk".*?<h1 title="(.*?)">', content, re.M | re.S).group(1)
print 'jobLocation:%s' % re.search('<span class="lname">(.*?)</span>', content, re.M | re.S).group(1)
print 'jobSlary:%s' % re.search('<span class="lname">.*?<strong>(.*?)</strong>', content, re.S).group(1)
companyMatch = re.search('<p class="cname">.*?<a href="(.*?)".*?title="(.*?)">', content, re.S)
print 'companyUrl:%s' % companyMatch.group(1)
print 'companyName:%s' % companyMatch.group(2)
ltypeMatch = re.search('<p class="msg ltype">(.*?)</p>', content, re.S)
ltypeString = re.sub('\s|&nbsp;', '', ltypeMatch.group(1)).split('|')
print 'companyType:%s' % ltypeString[0]
print 'companyGuimo:%s' % ltypeString[1]
print 'companyHangye:%s' % ltypeString[2]
dasfvaw = re.search('<p class="msg ltype">\s+(.*?)\s+(.*?)\s+(.*?)\s+</p>', content, re.S)
print 'companyType:%s' % dasfvaw.group(1)
print 'companyGuimo:%s' % dasfvaw.group(2).replace('|', '').replace('&nbsp;', '')
print 'companyHangye:%s' % dasfvaw.group(3).replace('|', '').replace('&nbsp;', '')
