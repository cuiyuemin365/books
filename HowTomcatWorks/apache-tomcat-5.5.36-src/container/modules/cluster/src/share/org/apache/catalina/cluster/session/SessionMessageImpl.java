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
package org.apache.catalina.cluster.session;


import org.apache.catalina.cluster.ClusterMessage;
import org.apache.catalina.cluster.Member;

/**
 * Session cluster message
 * 
 * @author Filip Hanik
 * @author Peter Rossbach
 * 
 * @version $Id: SessionMessageImpl.java 958491 2010-06-28 07:41:58Z kfujino $
 */
public class SessionMessageImpl implements SessionMessage, java.io.Serializable {
    
    public SessionMessageImpl() {
    }
    
    
    /*

     * Private serializable variables to keep the messages state
     */
    private int mEvtType = -1;
    private byte[] mSession;
    private String mSessionID;
    private Member mSrc;
    private String mContextName;
    private long serializationTimestamp;
    private boolean timestampSet = false ;
    private String uniqueId;
    private int resend = ClusterMessage.FLAG_DEFAULT ;
    private int compress = ClusterMessage.FLAG_DEFAULT ;


    private SessionMessageImpl( String contextName,
                           int eventtype,
                           byte[] session,
                           String sessionID)
    {
        mEvtType = eventtype;
        mSession = session;
        mSessionID = sessionID;
        mContextName = contextName;
        uniqueId = sessionID;
    }

    /**
     * Creates a session message. Depending on what event type you want this
     * message to represent, you populate the different parameters in the constructor<BR>
      * The following rules apply dependent on what event type argument you use:<BR>
     * <B>EVT_SESSION_CREATED</B><BR>
     *    The parameters: session, sessionID must be set.<BR>
     * <B>EVT_SESSION_EXPIRED</B><BR>
     *    The parameters: sessionID must be set.<BR>
     * <B>EVT_SESSION_ACCESSED</B><BR>
     *    The parameters: sessionID must be set.<BR>
     * <B>EVT_GET_ALL_SESSIONS</B><BR>
     *    get all sessions from from one of the nodes.<BR>
     * <B>EVT_SESSION_DELTA</B><BR>
     *    Send attribute delta (add,update,remove attribute or principal, ...).<BR>
     * <B>EVT_ALL_SESSION_DATA</B><BR>
     *    Send complete serializes session list<BR>
     * <B>EVT_ALL_SESSION_TRANSFERCOMPLETE</B><BR>
     *    send that all session state information are transfered
     *    after GET_ALL_SESSION received from this sender.<BR>
     * <B>EVT_CHANGE_SESSION_ID</B><BR>
     *    send original sessionID and new sessionID.<BR>
     * @param contextName - the name of the context (application
     * @param eventtype - one of the 8 event type defined in this class
     * @param session - the serialized byte array of the session itself
     * @param sessionID - the id that identifies this session
     * @param uniqueID - the id that identifies this message
     */
    public SessionMessageImpl( String contextName,
                           int eventtype,
                           byte[] session,
                           String sessionID,
                           String uniqueID)
    {
        this(contextName,eventtype,session,sessionID);
        uniqueId = uniqueID;
    }

    /**
     * returns the event type
     * @return one of the event types EVT_XXXX
     */
    public int getEventType() { return mEvtType; }

    /**
     * @return the serialized data for the session
     */
    public byte[] getSession() { return mSession;}

    /**
     * @return the session ID for the session
     */
    public String getSessionID(){ return mSessionID; }
    
    /**
     * set message send time but only the first setting works (one shot)
     */
    public void setTimestamp(long time) {
        synchronized(this) {
            if(!timestampSet) {
                serializationTimestamp=time;
                timestampSet = true ;
            }
        }
    }
    
    public long getTimestamp() { return serializationTimestamp;}
    
    /**
     * clear text event type name (for logging purpose only) 
     * @return the event type in a string representating, useful for debugging
     */
    public String getEventTypeString()
    {
        switch (mEvtType)
        {
            case EVT_SESSION_CREATED : return "SESSION-MODIFIED";
            case EVT_SESSION_EXPIRED : return "SESSION-EXPIRED";
            case EVT_SESSION_ACCESSED : return "SESSION-ACCESSED";
            case EVT_GET_ALL_SESSIONS : return "SESSION-GET-ALL";
            case EVT_SESSION_DELTA : return "SESSION-DELTA";
            case EVT_ALL_SESSION_DATA : return "ALL-SESSION-DATA";
            case EVT_ALL_SESSION_TRANSFERCOMPLETE : return "SESSION-STATE-TRANSFERED";
            case EVT_CHANGE_SESSION_ID : return "SESSION-ID-CHANGED";
            default : return "UNKNOWN-EVENT-TYPE";
        }
    }

    /**
     * Get the address that this message originated from.  This would be set
     * if the message was being relayed from a host other than the one
     * that originally sent it.
     */
    public Member getAddress()
    {
        return this.mSrc;
    }

    /**
     * Use this method to set the address that this message originated from.
     * This can be used when re-sending the EVT_GET_ALL_SESSIONS message to
     * another machine in the group.
     */
    public void setAddress(Member src)
    {
        this.mSrc = src;
    }

    public String getContextName() {
       return mContextName;
    }
    public String getUniqueId() {
        return uniqueId;
    }
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return Returns the compress.
     * @since 5.5.10 
     */
    public int getCompress() {
        return compress;
    }
    /**
     * @param compress The compress to set.
     * @since 5.5.10
     */
    public void setCompress(int compress) {
        this.compress = compress;
    }
    /**
     * @return Returns the resend.
     * @since 5.5.10
     */
    public int getResend() {
        return resend;
    }
    /**
     * @param resend The resend to set.
     * @since 5.5.10
     */
    public void setResend(int resend) {
        this.resend = resend;
    }

}
