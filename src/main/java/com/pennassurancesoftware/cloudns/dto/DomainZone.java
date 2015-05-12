package com.pennassurancesoftware.cloudns.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.pennassurancesoftware.cloudns.type.ZoneType;
import com.pennassurancesoftware.cloudns.util.EnumerationUtils;

/** Represents a Domain Zone */
public class DomainZone implements Serializable {
   private static final long serialVersionUID = 6478375355864290807L;

   @SerializedName("name")
   private String domainName;
   private String type;

   public String getDomainName() {
      return domainName;
   }

   public ZoneType getType() {
      return EnumerationUtils.lookup( ZoneType.class, type );
   }

   public void setDomainName( String domainName ) {
      this.domainName = domainName;
   }

   public void setType( ZoneType type ) {
      this.type = type.value();
   }

   @Override
   public String toString() {
      final StringBuffer result = new StringBuffer();
      result.append( String.format( "%s(%s)", domainName, type ) );
      return result.toString();
   }

}
