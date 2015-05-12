package com.pennassurancesoftware.cloudns.dto;

import com.google.gson.annotations.SerializedName;

public class DomainZoneStats {
   @SerializedName("count")
   private Integer numberOfZones;
   private Integer limit;

   @Override
   public String toString() {
      return String.format( "%s of %s Domain Zones Used", numberOfZones, limit );
   }

   public Integer getNumberOfZones() {
      return numberOfZones;
   }

   public void setNumberOfZones( Integer numberOfZones ) {
      this.numberOfZones = numberOfZones;
   }

   public Integer getLimit() {
      return limit;
   }

   public void setLimit( Integer limit ) {
      this.limit = limit;
   }
}
