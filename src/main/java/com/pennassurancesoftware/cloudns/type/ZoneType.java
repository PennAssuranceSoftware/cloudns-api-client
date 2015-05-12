package com.pennassurancesoftware.cloudns.type;

/** Defines the type so DNS Zones */
public enum ZoneType implements CodeEnum {
   /** Master domain zone */
   Master("master"),
   /** Slave domain zone */
   Slave("slave"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private ZoneType( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
