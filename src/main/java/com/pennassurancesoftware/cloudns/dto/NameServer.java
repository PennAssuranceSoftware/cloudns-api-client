package com.pennassurancesoftware.cloudns.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class NameServer implements Serializable {
   private static final long serialVersionUID = 1946347588886369481L;
   private String ip4;
   private String ip6;
   private String location;
   @SerializedName("location_cc")
   private String locationCountry;
   private String name;
   private String type;

   @Override
   public String toString() {
      final StringBuffer result = new StringBuffer();
      result.append( String.format( "%s(%s)", name, type ) );
      return result.toString();
   }

   public String getIp4() {
      return ip4;
   }

   public String getIp6() {
      return ip6;
   }

   public String getLocation() {
      return location;
   }

   public String getLocationCountry() {
      return locationCountry;
   }

   public String getName() {
      return name;
   }

   public String getType() {
      return type;
   }

   public void setIp4( String ip4 ) {
      this.ip4 = ip4;
   }

   public void setIp6( String ip6 ) {
      this.ip6 = ip6;
   }

   public void setLocation( String location ) {
      this.location = location;
   }

   public void setLocationCountry( String locationCountry ) {
      this.locationCountry = locationCountry;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public void setType( String type ) {
      this.type = type;
   }
}
