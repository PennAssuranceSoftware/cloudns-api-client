package com.pennassurancesoftware.cloudns.dto;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.pennassurancesoftware.cloudns.type.RecordType;
import com.pennassurancesoftware.cloudns.util.EnumerationUtils;

public class Record implements Serializable {
   private static final long serialVersionUID = -7320285491489555659L;

   private Integer algorithm;
   private Integer frame;
   @SerializedName("frame-description")
   private String frameDescription;
   @SerializedName("frame-keywords")
   private String frameKeywords;
   @SerializedName("frame-title")
   private String frameTitle;
   private String host;
   private Integer id;
   private Integer mail;
   private Integer port;
   private Integer priority;
   private String record;
   @SerializedName("redirect-type")
   private Integer redirectType;
   @SerializedName("save-path")
   private Integer savePath;
   @SerializedName("fptype")
   private Integer sshFpAlgorithm;
   private Integer ttl;
   private Integer txt;
   private String type;
   private Integer weight;

   /** @return Algorithm used to create the SSHFP fingerprint. Required for SSHFP records only. */
   public Integer getAlgorithm() {
      return algorithm;
   }

   /** @return 0 or 1 for Web redirects to disable or enable frame */
   public Integer getFrame() {
      return frame;
   }

   /** @return Description if frame is enabled in Web redirects */
   public String getFrameDescription() {
      return frameDescription;
   }

   /** @return Keywords if frame is enabled in Web redirects */
   public String getFrameKeywords() {
      return frameKeywords;
   }

   /** @return Tile if frame is enabled in Web redirects */
   public String getFrameTitle() {
      return frameTitle;
   }

   /** @return host / sub-domain */
   public String getHost() {
      return host;
   }

   /** @return Internal ID of the record */
   public Integer getId() {
      return id;
   }

   /** @return E-mail address for RP records */
   public Integer getMail() {
      return mail;
   }

   /** @return Port for SRV record */
   public Integer getPort() {
      return port;
   }

   /** @return Priority for MX or SRV record */
   public Integer getPriority() {
      return priority;
   }

   /** @return Record you want to add. Example 10.10.10.10 or cname.cloudns.net */
   public String getRecord() {
      return record;
   }

   /** @return 301 or 302 for Web redirects if frame is disabled */
   public Integer getRedirectType() {
      return redirectType;
   }

   /** @return 0 or 1 for Web redirects */
   public Integer getSavePath() {
      return savePath;
   }

   /** @return Type of the SSHFP algorithm. Required for SSHFP records only. */
   public Integer getSshFpAlgorithm() {
      return sshFpAlgorithm;
   }

   /**
    * Available TTL's:
    * 60 = 1 minute
    * 300 = 5 minutes
    * 900 = 15 minutes
    * 1800 = 30 minutes
    * 3600 = 1 hour
    * 21600 = 6 hours
    * 43200 = 12 hours
    * 86400 = 1 day
    * 172800 = 2 days
    * 604800 = 1 week
    * 1209600 = 2 weeks
    * 2592000 = 1 month
    * @return Time To Live for record
    */
   public Integer getTtl() {
      return ttl;
   }

   /** @return Domain name for TXT record used in RP records */
   public Integer getTxt() {
      return txt;
   }

   /** @return A, AAAA, MX, CNAME, TXT, NS, SRV, WR, RP, SSHFP for domain names and NS and PTR for reversed zones */
   public RecordType getType() {
      return EnumerationUtils.lookup( RecordType.class, type );
   }

   /** @return Weight for SRV record */
   public Integer getWeight() {
      return weight;
   }

   public void setAlgorithm( Integer algorithm ) {
      this.algorithm = algorithm;
   }

   public void setFrame( Integer frame ) {
      this.frame = frame;
   }

   public void setFrameDescription( String frameDescription ) {
      this.frameDescription = frameDescription;
   }

   public void setFrameKeywords( String frameKeywords ) {
      this.frameKeywords = frameKeywords;
   }

   public void setFrameTitle( String frameTitle ) {
      this.frameTitle = frameTitle;
   }

   public void setHost( String host ) {
      this.host = host;
   }

   public void setId( Integer id ) {
      this.id = id;
   }

   public void setMail( Integer mail ) {
      this.mail = mail;
   }

   public void setPort( Integer port ) {
      this.port = port;
   }

   public void setPriority( Integer priority ) {
      this.priority = priority;
   }

   public void setRecord( String record ) {
      this.record = record;
   }

   public void setRedirectType( Integer redirectType ) {
      this.redirectType = redirectType;
   }

   public void setSavePath( Integer savePath ) {
      this.savePath = savePath;
   }

   public void setSshFpAlgorithm( Integer sshFpAlgorithm ) {
      this.sshFpAlgorithm = sshFpAlgorithm;
   }

   public void setTtl( Integer ttl ) {
      this.ttl = ttl;
   }

   public void setTxt( Integer txt ) {
      this.txt = txt;
   }

   public void setType( RecordType type ) {
      this.type = type.value();
   }

   public void setType( String type ) {
      this.type = type;
   }

   public void setWeight( Integer weight ) {
      this.weight = weight;
   }

   @Override
   public String toString() {
      return String.format( "%s: %s", type, record );
   }

}
