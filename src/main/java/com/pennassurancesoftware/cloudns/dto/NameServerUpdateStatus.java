package com.pennassurancesoftware.cloudns.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/** Class that includes details of the update status of a Name Server for a Domain Zone */
public class NameServerUpdateStatus implements Serializable {
   private static final long serialVersionUID = -2397402215833468287L;
   
   private String ip4;
   private String ip6;
   @SerializedName("server")
   private String name;
   private Boolean updated;

   @Override
   public String toString() {
      return String.format( "%s: %s", name, updated ? "Updated" : "No Updated" );
   }

   public String getIp4() {
      return ip4;
   }

   public String getIp6() {
      return ip6;
   }

   public String getName() {
      return name;
   }

   public Boolean getUpdated() {
      return updated;
   }

   public void setIp4( String ip4 ) {
      this.ip4 = ip4;
   }

   public void setIp6( String ip6 ) {
      this.ip6 = ip6;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public void setUpdated( Boolean updated ) {
      this.updated = updated;
   }

}
