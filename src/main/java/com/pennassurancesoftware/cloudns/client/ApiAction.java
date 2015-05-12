/*
 * The MIT License
 * 
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.pennassurancesoftware.cloudns.client;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.pennassurancesoftware.cloudns.dto.DomainZone;
import com.pennassurancesoftware.cloudns.dto.DomainZoneStats;
import com.pennassurancesoftware.cloudns.dto.NameServer;
import com.pennassurancesoftware.cloudns.dto.NameServerUpdateStatus;
import com.pennassurancesoftware.cloudns.dto.Record;
import com.pennassurancesoftware.cloudns.dto.Response;
import com.pennassurancesoftware.cloudns.dto.SoaDetails;

/**
 * Enumeration of Tutum RESTful resource information.
 */
public enum ApiAction {

   AVAILABLE_NAME_SERVERS("/available-name-servers.json", RequestMethod.GET, NameServer[].class),
   REGISTER_DOMAIN_ZONE("/register.json?domain-name=%s&zone-type=%s", RequestMethod.GET, Response.class),
   DELETE_DOMAIN_ZONE("/delete.json?domain-name=%s", RequestMethod.GET, Response.class),
   GET_DOMAIN_ZONES("/list-zones.json?page=%s&rows-per-page=%s", RequestMethod.GET, DomainZone[].class),
   GET_DOMAIN_ZONES_PAGE_COUNT("/get-pages-count.json?&rows-per-page=%s", RequestMethod.GET, Integer.class),
   GET_DOMAIN_ZONE_STATS("/get-zones-stats.json", RequestMethod.GET, DomainZoneStats.class),
   GET_DOMAIN_ZONE_UPDATE_STATUS("/update-status.json?domain-name=%s", RequestMethod.GET, NameServerUpdateStatus[].class),
   GET_DOMAIN_ZONE_IS_UPDATED("/is-updated.json?domain-name=%s", RequestMethod.GET, Boolean.class),
   GET_DOMAIN_ZONE_RECORDS("/records.json?domain-name=%s", RequestMethod.GET, new TypeToken<Map<String, Record>>() {}.getType()),
   ADD_DOMAIN_ZONE_RECORD(
         "/add-record.json?domain-name=%s&record-type=%s&host=%s&record=%s&ttl=%s&priority=%s&weight=%s&port=%s&frame=%s&frame-title=%s&frame-keywords=%s&frame-description=%s&save-path=%s&redirect-type=%s&mail=%s&txt=%s&algorithm=%s&fptype=%s",
         RequestMethod.GET, Response.class),
   DELETE_DOMAIN_ZONE_RECORD("/delete-record.json?domain-name=%s&record-id=%s", RequestMethod.GET, Response.class),
   MODIFY_DOMAIN_ZONE_RECORD(
         "/mod-record.json?domain-name=%s&record-id=%s&host=%s&record=%s&ttl=%s&priority=%s&weight=%s&port=%s&frame=%s&frame-title=%s&frame-keywords=%s&frame-description=%s&save-path=%s&redirect-type=%s&mail=%s&txt=%s&algorithm=%s&fptype=%s",
         RequestMethod.GET, Response.class),
   COPY_DOMAIN_ZONE_RECORDS("/copy-records.json?domain-name=%s&from-domain=%s&delete-current-records=%s", RequestMethod.GET, Response.class),
   GET_DOMAIN_ZONE_SOA_DETAILS("/soa-details.json?&domain-name=%s", RequestMethod.GET, SoaDetails.class),

   ;

   private String path;

   private RequestMethod method;

   private Type type;

   ApiAction( String path ) {
      this( path, RequestMethod.GET );
   }

   ApiAction( String path, RequestMethod method ) {
      this( path, method, null );
   }

   ApiAction( String path, RequestMethod method, Type type ) {
      this.path = path;
      this.method = method;
      this.type = type;
   }

   /**
    * @return the path
    */
   public String getPath() {
      return path;
   }

   /**
    * @return the method
    */
   public RequestMethod getMethod() {
      return method;
   }

   /**
    * @return the clazz
    */
   public Type getResponseType() {
      return type;
   }
}
