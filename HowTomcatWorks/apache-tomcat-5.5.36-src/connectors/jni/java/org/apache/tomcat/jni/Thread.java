/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.tomcat.jni;

/** Thread
 *
 * @author Mladen Turk
 * @version $Id: Thread.java 939522 2010-04-30 00:26:15Z kkolinko $
 * @deprecated This class is not used by Tomcat itself and does no
 * longer exist in Tomcat 6 and beyond.
 */

public class Thread {
    
    /**
     * Get the current thread ID handle.
     */
    public static native long current();    

}
