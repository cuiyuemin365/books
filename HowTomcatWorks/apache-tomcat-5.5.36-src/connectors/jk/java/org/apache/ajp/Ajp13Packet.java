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

package org.apache.ajp;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.buf.MessageBytes;
import org.apache.tomcat.util.http.MimeHeaders;


/**
 * A single packet for communication between the web server and the
 * container.  Designed to be reused many times with no creation of
 * garbage.  Understands the format of data types for these packets.
 * Can be used (somewhat confusingly) for both incoming and outgoing
 * packets.  
 *
 * See Ajp14/Ajp13Packet.
 *
 * @author Henri Gomez [hgomez@apache.org]
 * @author Dan Milstein [danmil@shore.net]
 * @author Keith Wannamaker [Keith@Wannamaker.org]
 * @author Kevin Seguin
 * @author Costin Manolache
 */
public class Ajp13Packet {
    
    private static org.apache.commons.logging.Log log=
        org.apache.commons.logging.LogFactory.getLog( Ajp13Packet.class );
    
    public static final String DEFAULT_CHAR_ENCODING = "8859_1";
    public static final int    AJP13_WS_HEADER       = 0x1234;
    public static final int    AJP13_SW_HEADER       = 0x4142;  // 'AB'

    /**
     * encoding to use when converting byte[] <-> string
     */
    String encoding = DEFAULT_CHAR_ENCODING;

    /**
     * Holds the bytes of the packet
     */
    byte buff[];

    /**
     * The current read or write position in the buffer
     */
    int pos;    

    /**
     * This actually means different things depending on whether the
     * packet is read or write.  For read, it's the length of the
     * payload (excluding the header).  For write, it's the length of
     * the packet as a whole (counting the header).  Oh, well.
     */
    int len; 

    /**
     * Create a new packet with an internal buffer of given size.
     * @param size packet size
     */
    public Ajp13Packet( int size ) {
        buff = new byte[size];
    }

    /**
     * Create a new packet with given bytes
     * @param b this packet's bytes.
     */
    public Ajp13Packet( byte b[] ) {
        buff = b;
    }

    /**
     * Set the encoding to use for byte[] <-> string
     * conversions.
     * @param encoding the encoding to use.
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * Get the encoding used for byte[] <-> string
     * conversions.
     * @return the encoding used.
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Get the internal buffer
     * @return internal buffer
     */
    public byte[] getBuff() {
        return buff;
    }

    /**
     * Get length.
     * @return length -- This actually means different things depending on whether the
     *                   packet is read or write.  For read, it's the length of the
     *                   payload (excluding the header).  For write, it's the length of
     *                   the packet as a whole (counting the header).  Oh, well.
     */
    public int getLen() {
        return len;
    }

    /**
     * Get offset into internal buffer.
     * @return offset
     */
    public int getByteOff() {
        return pos;
    }

    /**
     * Set offset into internal buffer.
     * @param c new offset
     */
    public void setByteOff(int c) {
        pos=c;
    }

    /** 
     * Parse the packet header for a packet sent from the web server to
     * the container.  Set the read position to immediately after
     * the header.
     *
     * @return The length of the packet payload, as encoded in the
     * header, or -1 if the packet doesn't have a valid header.  
     */
    public int checkIn() {
        pos = 0;
        int mark = getInt();
        len      = getInt();
	    
        if( mark != AJP13_WS_HEADER ) {
            if (log.isDebugEnabled())
                log.debug("BAD packet " + mark);
            dump( "In: " );
            return -1;
        }
        return len;
    }
    
    /**
     * Prepare this packet for accumulating a message from the container to
     * the web server.  Set the write position to just after the header
     * (but leave the length unwritten, because it is as yet unknown).
     */
    public void reset() {
        len = 4;
        pos = 4;
        buff[0] = (byte)(AJP13_SW_HEADER >> 8);
        buff[1] = (byte)(AJP13_SW_HEADER & 0xFF);
    }
	
    /**
     * For a packet to be sent to the web server, finish the process of
     * accumulating data and write the length of the data payload into
     * the header.  
     */
    public void end() {
        len = pos;
        setInt( 2, len-4 );
    }
	
    // ============ Data Writing Methods ===================

    /**
     * Write an 32 bit integer at an arbitrary position in the packet,but don't
     * change the write position.
     *
     * @param bpos The 0-indexed position within the buffer at which to
     * write the integer (where 0 is the beginning of the header).
     * @param val The integer to write.
     */
    private void setInt( int bPos, int val ) {
        buff[bPos]   = (byte) ((val >>>  8) & 0xFF);
        buff[bPos+1] = (byte) (val & 0xFF);
    }

    public void appendInt( int val ) {
        setInt( pos, val );
        pos += 2;
    }
	
    public void appendByte( byte val ) {
        buff[pos++] = val;
    }
	
    public void appendBool( boolean val) {
        buff[pos++] = (byte) (val ? 1 : 0);
    }

    /**
     * Write a String out at the current write position.  Strings are
     * encoded with the length in two bytes first, then the string, and
     * then a terminating \0 (which is <B>not</B> included in the
     * encoded length).  The terminator is for the convenience of the C
     * code, where it saves a round of copying.  A null string is
     * encoded as a string with length 0.  
     */
    public void appendString(String str) throws UnsupportedEncodingException {
        // Dual use of the buffer - as Ajp13Packet and as OutputBuffer
        // The idea is simple - fewer buffers, smaller footprint and less
        // memcpy. The code is a bit tricky, but only local to this
        // function.
        if(str == null) {
            setInt( pos, 0);
            buff[pos + 2] = 0;
            pos += 3;
            return;
        }

        //
        // XXX i don't have OutputBuffer in tc4... ks.
        // fix this later...
        //
        byte[] bytes = str.getBytes(encoding);
        appendBytes(bytes, 0, bytes.length);
        
        /* XXX XXX XXX XXX Try to add it back.
        int strStart=pos;

        // This replaces the old ( buggy and slow ) str.length()
        // and str.getBytes(). str.length() is chars, may be != bytes
        // and getBytes is _very_ slow.
        // XXX setEncoding !!!

        ob.setByteOff( pos+2 ); 
        try {
            ob.write( str );
            ob.flushChars();
        } catch( IOException ex ) {
            ex.printStackTrace();
        }
        int strEnd=ob.getByteOff();
        
        buff[strEnd]=0; // The \0 terminator
        int strLen=strEnd-strStart;
        setInt( pos, strEnd - strStart );
        pos += strLen + 3; 
        */
    }

    /** 
     * Copy a chunk of bytes into the packet, starting at the current
     * write position.  The chunk of bytes is encoded with the length
     * in two bytes first, then the data itself, and finally a
     * terminating \0 (which is <B>not</B> included in the encoded
     * length).
     *
     * @param b The array from which to copy bytes.
     * @param off The offset into the array at which to start copying
     * @param numBytes The number of bytes to copy.  
     */
    public void appendBytes( byte b[], int off, int numBytes ) {
        appendInt( numBytes );
        if( pos + numBytes >= buff.length ) {
            if (log.isDebugEnabled())
                log.debug("Buffer overflow " + buff.length + " " + pos + " " + numBytes );
        }
        System.arraycopy( b, off, buff, pos, numBytes);
        buff[pos + numBytes] = 0; // Terminating \0
        pos += numBytes + 1;
    }

        /**
     * Write a 32 bits integer at an arbitrary position in the packet, but don't
     * change the write position.
     *
     * @param bpos The 0-indexed position within the buffer at which to
     * write the integer (where 0 is the beginning of the header).
     * @param val The integer to write.
     */
    private void setLongInt( int bPos, int val ) {
        buff[bPos]   = (byte) ((val >>>  24) & 0xFF);
        buff[bPos+1] = (byte) ((val >>>  16) & 0xFF);
        buff[bPos+2] = (byte) ((val >>>   8) & 0xFF);
        buff[bPos+3] = (byte) (val & 0xFF);
    }

    public void appendLongInt( int val ) {
        setLongInt( pos, val );
        pos += 4;
    }

    /**
     * Copy a chunk of bytes into the packet, starting at the current
     * write position.  The chunk of bytes IS NOT ENCODED with ANY length
     * header.
     *
     * @param b The array from which to copy bytes.
     * @param off The offset into the array at which to start copying
     * @param numBytes The number of bytes to copy.
     */
    public void appendXBytes(byte[] b, int off, int numBytes) {
        if( pos + numBytes > buff.length ) {
            if (log.isDebugEnabled())
                log.debug("appendXBytes - Buffer overflow " + buff.length + " " + pos + " " + numBytes );
        }
        System.arraycopy( b, off, buff, pos, numBytes);
        pos += numBytes;
    }
	
    
    // ============ Data Reading Methods ===================

    /**
     * Read an integer from packet, and advance the read position past
     * it.  Integers are encoded as two unsigned bytes with the
     * high-order byte first, and, as far as I can tell, in
     * little-endian order within each byte.  
     */
    public int getInt() {
        int result = peekInt();
        pos += 2;
        return result;
    }

    /**
     * Read an integer from the packet, but don't advance the read
     * position past it.  
     */
    public int peekInt() {
        int b1 = buff[pos] & 0xFF;  // No swap, Java order
        int b2 = buff[pos + 1] & 0xFF;

        return  (b1<<8) + b2;
    }

    public byte getByte() {
        byte res = buff[pos];
        pos++;
        return res;
    }

    public byte peekByte() {
        return buff[pos];
    }

    public boolean getBool() {
        return (getByte() == (byte) 1);
    }

    public void getMessageBytes(MessageBytes mb) {
        int length = getInt();
        if( (length == 0xFFFF) || (length == -1) ) {
            mb.setString( null );
            return;
        }
        mb.setBytes( buff, pos, length );
        pos += length;
        pos++; // Skip the terminating \0
    }
    
    public MessageBytes addHeader(MimeHeaders headers) {
        int length = getInt();
        if( (length == 0xFFFF) || (length == -1) ) {
            return null;
        }
        MessageBytes vMB=headers.addValue( buff, pos, length );
        pos += length;
        pos++; // Skip the terminating \0
	    
        return vMB;
    }
	
    /**
     * Read a String from the packet, and advance the read position
     * past it.  See appendString for details on string encoding.
     **/
    public String getString() throws java.io.UnsupportedEncodingException {
        int length = getInt();
        if( (length == 0xFFFF) || (length == -1) ) {
            if (log.isDebugEnabled())
                log.debug("null string " + length);
            return null;
        }
        String s = new String(buff, pos, length, encoding);

        pos += length;
        pos++; // Skip the terminating \0
        return s;
    }

    /**
     * Copy a chunk of bytes from the packet into an array and advance
     * the read position past the chunk.  See appendBytes() for details
     * on the encoding.
     *
     * @return The number of bytes copied.
     */
    public int getBytes(byte dest[]) {
        int length = getInt();
        if( length > buff.length ) {
            // XXX Should be if(pos + length > buff.legth)?
            if (log.isDebugEnabled())
                log.debug("XXX Assert failed, buff too small ");
        }
	
        if( (length == 0xFFFF) || (length == -1) ) {
            if (log.isDebugEnabled())
                log.debug("null string " + length);
            return 0;
        }

        System.arraycopy( buff, pos,  dest, 0, length );
        pos += length;
        pos++; // Skip terminating \0  XXX I believe this is wrong but harmless
        return length;
    }

        /**
     * Read a 32 bits integer from packet, and advance the read position past
     * it.  Integers are encoded as four unsigned bytes with the
     * high-order byte first, and, as far as I can tell, in
     * little-endian order within each byte.
     */
    public int getLongInt() {
        int result = peekLongInt();
        pos += 4;
        return result;
    }

    /**
     * Copy a chunk of bytes from the packet into an array and advance
     * the read position past the chunk.  See appendXBytes() for details
     * on the encoding.
     *
     * @return The number of bytes copied.
     */
    public int getXBytes(byte[] dest, int length) {
        if( length > buff.length ) {
        // XXX Should be if(pos + length > buff.legth)?
            if (log.isDebugEnabled())
                log.debug("XXX Assert failed, buff too small ");
        }

        System.arraycopy( buff, pos,  dest, 0, length );
        pos += length;
        return length;
    }

    /**
     * Read a 32 bits integer from the packet, but don't advance the read
     * position past it.
     */
    public int peekLongInt() {
        int b1 = buff[pos] & 0xFF;  // No swap, Java order
        b1 <<= 8;
        b1 |= (buff[pos + 1] & 0xFF);
        b1 <<= 8;
        b1 |= (buff[pos + 2] & 0xFF);
        b1 <<=8;
        b1 |= (buff[pos + 3] & 0xFF);
        return  b1;
    }

    // ============== Debugging code =========================
    private String hex( int x ) {
        //	    if( x < 0) x=256 + x;
        String h=Integer.toHexString( x );
        if( h.length() == 1 ) h = "0" + h;
        return h.substring( h.length() - 2 );
    }

    private void hexLine( int start ) {
	int pkgEnd = len + 4;
	if( pkgEnd > buff.length )
	    pkgEnd = buff.length;
        for( int i=start; i< start+16 ; i++ ) {
            if( i < pkgEnd) {
                if (log.isDebugEnabled())
                    log.debug( hex( buff[i] ) + " ");
            } else {
                if (log.isDebugEnabled()) 
                    log.debug( "   " );
            }
        }
        if (log.isDebugEnabled()) 
            log.debug(" | ");
        for( int i=start; i < start+16 && i < pkgEnd; i++ ) {
            if( Character.isLetterOrDigit( (char)buff[i] )) {
                if (log.isDebugEnabled()) 
                    log.debug( new Character((char)buff[i]) );
            } else {
                if (log.isDebugEnabled()) 
                    log.debug( "." );
            }
        }
    }
    
    public void dump(String msg) {
        if (log.isDebugEnabled())
            log.debug( msg + ": " + buff + " " + pos +"/" + (len + 4));

        for( int j=0; j < len + 4; j+=16 )
            hexLine( j );

    }
}
