/* ========================================================================= *
 *                                                                           *
 *                 The Apache Software License,  Version 1.1                 *
 *                                                                           *
 *          Copyright (c) 1999-2001 The Apache Software Foundation.          *
 *                           All rights reserved.                            *
 *                                                                           *
 * ========================================================================= *
 *                                                                           *
 * Redistribution and use in source and binary forms,  with or without modi- *
 * fication, are permitted provided that the following conditions are met:   *
 *                                                                           *
 * 1. Redistributions of source code  must retain the above copyright notice *
 *    notice, this list of conditions and the following disclaimer.          *
 *                                                                           *
 * 2. Redistributions  in binary  form  must  reproduce the  above copyright *
 *    notice,  this list of conditions  and the following  disclaimer in the *
 *    documentation and/or other materials provided with the distribution.   *
 *                                                                           *
 * 3. The end-user documentation  included with the redistribution,  if any, *
 *    must include the following acknowlegement:                             *
 *                                                                           *
 *       "This product includes  software developed  by the Apache  Software *
 *        Foundation <http://www.apache.org/>."                              *
 *                                                                           *
 *    Alternately, this acknowlegement may appear in the software itself, if *
 *    and wherever such third-party acknowlegements normally appear.         *
 *                                                                           *
 * 4. The names  "The  Jakarta  Project",  "Jk",  and  "Apache  Software     *
 *    Foundation"  must not be used  to endorse or promote  products derived *
 *    from this  software without  prior  written  permission.  For  written *
 *    permission, please contact <apache@apache.org>.                        *
 *                                                                           *
 * 5. Products derived from this software may not be called "Apache" nor may *
 *    "Apache" appear in their names without prior written permission of the *
 *    Apache Software Foundation.                                            *
 *                                                                           *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES *
 * INCLUDING, BUT NOT LIMITED TO,  THE IMPLIED WARRANTIES OF MERCHANTABILITY *
 * AND FITNESS FOR  A PARTICULAR PURPOSE  ARE DISCLAIMED.  IN NO EVENT SHALL *
 * THE APACHE  SOFTWARE  FOUNDATION OR  ITS CONTRIBUTORS  BE LIABLE  FOR ANY *
 * DIRECT,  INDIRECT,   INCIDENTAL,  SPECIAL,  EXEMPLARY,  OR  CONSEQUENTIAL *
 * DAMAGES (INCLUDING,  BUT NOT LIMITED TO,  PROCUREMENT OF SUBSTITUTE GOODS *
 * OR SERVICES;  LOSS OF USE,  DATA,  OR PROFITS;  OR BUSINESS INTERRUPTION) *
 * HOWEVER CAUSED AND  ON ANY  THEORY  OF  LIABILITY,  WHETHER IN  CONTRACT, *
 * STRICT LIABILITY, OR TORT  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN *
 * ANY  WAY  OUT OF  THE  USE OF  THIS  SOFTWARE,  EVEN  IF  ADVISED  OF THE *
 * POSSIBILITY OF SUCH DAMAGE.                                               *
 *                                                                           *
 * ========================================================================= *
 *                                                                           *
 * This software  consists of voluntary  contributions made  by many indivi- *
 * duals on behalf of the  Apache Software Foundation.  For more information *
 * on the Apache Software Foundation, please see <http://www.apache.org/>.   *
 *                                                                           *
 * ========================================================================= */

/***************************************************************************
 * Description: Multi thread portability code for JK                       *
 * Author:      Gal Shachor <shachor@il.ibm.com>                           *
 * Version:     $Revision: 1.3 $                                           *
 ***************************************************************************/

#ifndef _JK_MT_H
#define _JK_MT_H

#include "jk_global.h"

/*
 * All WIN32 code is MT, UNIX code that uses pthreads is marked by the POSIX 
 * _REENTRANT define.
 */
#if defined (WIN32) || defined(_REENTRANT)

    /*
     * Marks execution under MT compilation
     */
    #define _MT_CODE

    #ifdef WIN32

        #include <windows.h>

        typedef CRITICAL_SECTION JK_CRIT_SEC;

        #define JK_INIT_CS(x, rc) InitializeCriticalSection(x); rc = JK_TRUE;
        #define JK_DELETE_CS(x, rc) DeleteCriticalSection(x); rc = JK_TRUE;
        #define JK_ENTER_CS(x, rc) EnterCriticalSection(x); rc = JK_TRUE;
        #define JK_LEAVE_CS(x, rc) LeaveCriticalSection(x); rc = JK_TRUE;

    #else /* Unix pthreads */

        #include <pthread.h>

        typedef pthread_mutex_t	JK_CRIT_SEC;

        #define JK_INIT_CS(x, rc)\
            if(pthread_mutex_init(x, NULL)) rc = JK_FALSE; else rc = JK_TRUE; 

        #define JK_DELETE_CS(x, rc)\
            if(pthread_mutex_destroy(x)) rc = JK_FALSE; else rc = JK_TRUE; 

        #define JK_ENTER_CS(x, rc)\
            if(pthread_mutex_lock(x)) rc = JK_FALSE; else rc = JK_TRUE; 

        #define JK_LEAVE_CS(x, rc)\
            if(pthread_mutex_unlock(x)) rc = JK_FALSE; else rc = JK_TRUE; 
    #endif /* Unix pthreads */

#else /* Not an MT code */

    typedef void *JK_CRIT_SEC;

    #define JK_INIT_CS(x, rc) rc = JK_TRUE;
    #define JK_DELETE_CS(x, rc) rc = JK_TRUE;
    #define JK_ENTER_CS(x, rc) rc = JK_TRUE;
    #define JK_LEAVE_CS(x, rc) rc = JK_TRUE;

#endif /* Not an MT code */

#endif /* _JK_MT_H */
