#!/usr/bin/perl

# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.


require "subparseform.lib";
&Parse_Form;

$counter = $formdata{'counter'};

$counter +=1;

print "Content-type: text/html\n\n";
print "<FONT SIZE=+2>If you add one to your number, the result is <B>$counter</B>";

$counter +=5;
print "<P>If you add 5 to that, the result is <B>$counter</B></FONT>";