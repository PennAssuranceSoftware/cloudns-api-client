package com.pennassurancesoftware.cloudns.dto;

import java.io.Serializable;

/** Details for client to set dynamic IP of DNS record */
public class DynamicUrl implements Serializable {
   private static final long serialVersionUID = -6304467549768658836L;
   
   private String host;
   private String url;

   @Override
   public String toString() {
      return String.format( "%s: %s", host, url );
   }

   public String getHost() {
      return host;
   }

   public void setHost( String host ) {
      this.host = host;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl( String url ) {
      this.url = url;
   }

}
