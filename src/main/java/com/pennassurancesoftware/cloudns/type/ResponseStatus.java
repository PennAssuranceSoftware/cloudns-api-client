package com.pennassurancesoftware.cloudns.type;

public enum ResponseStatus implements CodeEnum {
   /** The action was executed successfully */
   Success("Success"),
   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private ResponseStatus( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
