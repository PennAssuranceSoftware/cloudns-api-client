package com.pennassurancesoftware.cloudns.type;

/**
 * Domain Record Type
 * A, AAAA, MX, CNAME, TXT, NS, SRV, WR, RP, SSHFP for domain names and NS and PTR for reversed zones
 */
public enum RecordType implements CodeEnum {

   A("A"),
   AAAA("AAAA"),
   MX("MX"),
   CNAME("CNAME"),
   TXT("TXT"),
   NS("NS"),
   SRV("SRV"),
   WR("WR"),
   RP("RP"),
   SSHFP("SSHFP"),
   PTR("PTR"),

   /** Unknown or Not Applicable */
   Null(""), ;

   private String value;

   private RecordType( String value ) {
      this.value = value;
   }

   @Override
   public String value() {
      return value;
   }
}
