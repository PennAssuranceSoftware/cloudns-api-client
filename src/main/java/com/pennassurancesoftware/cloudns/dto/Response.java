package com.pennassurancesoftware.cloudns.dto;

import java.io.Serializable;

import com.pennassurancesoftware.cloudns.type.ResponseStatus;
import com.pennassurancesoftware.cloudns.util.EnumerationUtils;

public class Response implements Serializable {
   private static final long serialVersionUID = -7704014510595579496L;

   private String status;
   private String statusDescription;

   @Override
   public String toString() {
      return String.format( "%s(%s)", status, statusDescription );
   }

   public ResponseStatus getStatus() {
      return EnumerationUtils.lookup( ResponseStatus.class, status );
   }

   public void setStatus( ResponseStatus status ) {
      this.status = status.value();
   }

   public String getStatusDescription() {
      return statusDescription;
   }

   public void setStatusDescription( String statusDescription ) {
      this.statusDescription = statusDescription;
   }

   public boolean isSuccess() {
      return ResponseStatus.Success.equals( getStatus() );
   }
}
