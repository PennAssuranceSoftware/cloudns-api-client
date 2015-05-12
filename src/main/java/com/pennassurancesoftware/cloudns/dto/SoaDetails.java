package com.pennassurancesoftware.cloudns.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class SoaDetails implements Serializable {
   private static final long serialVersionUID = -2986676766495270632L;

   private String adminMail;
   @SerializedName("defaultTTL")
   private Integer defaultTtl;
   private Integer expire;
   @SerializedName("primaryNS")
   private String primaryNs;
   private Integer refresh;
   private Integer retry;
   private Integer serialNumber;

   @Override
   public String toString() {
      final StringBuffer result = new StringBuffer();
      result.append( String.format( "Serial Number: %s, Default TTL: %s", serialNumber, defaultTtl ) );
      return result.toString();
   }

   public String getAdminMail() {
      return adminMail;
   }

   public Integer getDefaultTtl() {
      return defaultTtl;
   }

   public Integer getExpire() {
      return expire;
   }

   public String getPrimaryNs() {
      return primaryNs;
   }

   public Integer getRefresh() {
      return refresh;
   }

   public Integer getRetry() {
      return retry;
   }

   public Integer getSerialNumber() {
      return serialNumber;
   }

   public void setAdminMail( String adminMail ) {
      this.adminMail = adminMail;
   }

   public void setDefaultTtl( Integer defaultTtl ) {
      this.defaultTtl = defaultTtl;
   }

   public void setExpire( Integer expire ) {
      this.expire = expire;
   }

   public void setPrimaryNs( String primaryNs ) {
      this.primaryNs = primaryNs;
   }

   public void setRefresh( Integer refresh ) {
      this.refresh = refresh;
   }

   public void setRetry( Integer retry ) {
      this.retry = retry;
   }

   public void setSerialNumber( Integer serialNumber ) {
      this.serialNumber = serialNumber;
   }
}
