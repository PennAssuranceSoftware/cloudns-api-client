/*
 * The MIT License
 * 
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.pennassurancesoftware.cloudns.exception;

/**
 * <code>TutumException</code> will be thrown, when request had interruption [
 * <code>HTTP status
 * code >= 400 && < 510</code>] Tutum API
 */
public class ClouDnsException extends RuntimeException {
   static final long serialVersionUID = -925220451573356906L;

   private String id;
   private int httpStatusCode;

   public ClouDnsException( String msg ) {
      super( msg );
   }

   public ClouDnsException( String msg, Throwable t ) {
      super( msg, t );
   }

   public ClouDnsException( String msg, String id, int statusCode ) {
      super( msg );
      this.id = id;
      this.httpStatusCode = statusCode;
   }

   /**
    * @return the id
    */
   public String getId() {
      return id;
   }

   /**
    * @return the httpStatusCode
    */
   public int getHttpStatusCode() {
      return httpStatusCode;
   }
}
