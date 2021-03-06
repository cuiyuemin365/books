/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.webapp.balancer.rules;

import javax.servlet.http.HttpServletRequest;


/**
 * This rule matches every request
 * passed to it, making it suitable
 * for use as a catch-all or last
 * rule in a chain.
 *
 * @author Yoav Shapira
 */
public class AcceptEverythingRule extends BaseRule {
    /**
     * @see org.apache.webapp.balancer.Rule#matches(HttpServletRequest)
     *
     * This implementation always matches.
     */
    public boolean matches(HttpServletRequest request) {
        return true;
    }
}


// End of file: AcceptEverythingRule.java
