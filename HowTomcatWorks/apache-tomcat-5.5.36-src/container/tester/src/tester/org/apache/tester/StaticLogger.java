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

package org.apache.tester;


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 * Logger that uses a static message buffer to facilitate intra-web-app
 * recording and retrieval of log messages.
 *
 * @author Craig R. McClanahan
 * @version $Id: StaticLogger.java 939535 2010-04-30 01:11:10Z kkolinko $
 */

public class StaticLogger {


    // ----------------------------------------------------------- Constructors


    // ------------------------------------------------------- Static Variables


    /**
     * The set of messages that have been logged.
     */
    protected static ArrayList messages = new ArrayList();


    /**
     * The index of the next message that will be retrieved by a read() call.
     */
    protected static int position = 0;


    // --------------------------------------------------------- Public Methods


    /**
     * Return the next message that has been logged, or <code>null</code>
     * if there are no more messages.
     */
    public static String read() {

        synchronized (messages) {
            if (position < messages.size())
                return ((String) messages.get(position++));
            else
                return (null);
        }

    }


    /**
     * Reset the messages buffer and position.
     */
    public static void reset() {

        synchronized (messages) {
            messages.clear();
            position = 0;
        }

    }


    /**
     * Write a new message to the end of the messages buffer.
     *
     * @param message The message to be added
     */
    public static void write(String message) {

        synchronized (messages) {
            messages.add(message);
        }

    }


}
