package com.pennassurancesoftware.cloudns;

import java.util.List;

import com.pennassurancesoftware.cloudns.dto.DomainZone;
import com.pennassurancesoftware.cloudns.dto.DomainZoneStats;
import com.pennassurancesoftware.cloudns.dto.DynamicUrl;
import com.pennassurancesoftware.cloudns.dto.NameServer;
import com.pennassurancesoftware.cloudns.dto.NameServerUpdateStatus;
import com.pennassurancesoftware.cloudns.dto.Record;
import com.pennassurancesoftware.cloudns.dto.SoaDetails;
import com.pennassurancesoftware.cloudns.type.ZoneType;

/**
 * <p>
 * <strong>ClouDNS API client in Java</strong>
 * </p>
 *
 * <p>
 * A simple and meaningful java methods for <a href="https://api.cloudns.net"
 * title="ClouDNS's API">ClouDNS's API</a>. All of the RESTful that you find in
 * ClouDNS API's Version 1 is available via simple java methods.
 * </p>
 *
 */
public interface ClouDns {
   /**
    * <a href="https://www.cloudns.net/api-help/dns/dns-available-name-servers">Available Name Servers Documentation</a>
    * 
    * @return List of available name servers provided by account with ClouDNS
    */
   List<NameServer> getAvailableNameServers();

   /**
    * <a href="https://www.cloudns.net/api-help/dns/register-domain-zone/">Register Domain Zone Documentation</a>
    * 
    * @param domainName Domain that will be added to the account
    * @param type Type of zone the domain should be created as
    * 
    */
   void registerDomainZone( String domainName, ZoneType type );

   /**
    * <a href="https://www.cloudns.net/api-help/dns/delete-domain-zone/">Delete Domain Zone Documentation</a>
    * 
    * 
    * @param domainName Domain that will be deleted
    */
   void deleteDomainZone( String domainName );

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/dns/list-zones/">List Domain Zone Documentation</a>
    * 
    * 
    * @return List of Domain Zones
    */
   List<DomainZone> getDomainZones();

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/dns/get-zones-stats/">Domain Zone Stats Documentation</a>
    * 
    * 
    * @return Statistics about account with ClouDNS
    */
   DomainZoneStats getDomainZoneStats();

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/dns/domain-update-status/">Domain Zone Update Status Documentation</a>
    * 
    * 
    * @param domainName Domain Zone to get the list of update statuses for
    * @return List of Name Servers and their corresponding update status
    */
   List<NameServerUpdateStatus> getDomainZoneUpdateStatus( String domainName );

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/dns/domain-is-updated/">Domain Zone Is Updated Documentation</a>
    * 
    * @param domainName Domain Zone to check if updates have been fully applied
    * @return Flag that indicates the zone is updated
    */
   boolean isDomainZoneUpdated( String domainName );

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/records/list-records-domain-zone/">Domain Zone Records Documentation</a>
    * 
    * @param domainName Domain Zone to get records for
    * @return List of records for the specified Domain Zone
    */
   List<Record> getDomainZoneRecords( String domainName );

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/records/add-record-domain-zone/">Add Domain Zone Record Documentation</a>
    * 
    * @param domainName Domain Zone to add the record to
    * @param record Record to add to the Domain Zone
    */
   void addDomainZoneRecord( String domainName, Record record );

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/records/delete-record-domain-zone/">Delete Domain Zone Record Documentation</a>
    * 
    * @param domainName Domain Zone to delete the record from
    * @param recordId ID of the record to delete
    */
   void deleteDomainZoneRecord( String domainName, Integer recordId );

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/records/mod-record-domain-zone/">Modify Domain Zone Record Documentation</a>
    * 
    * @param domainName Domain Zone to modify the record in
    * @param record Record with ID specified and values to update to
    */
   void modifyDomainZoneRecord( String domainName, Record record );

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/records/copy-records/">Copy Domain Zone Records Documentation</a>
    * 
    * @param domainName Domain Zone the records will be copied into
    * @param fromDomainName Domain Zone records will be copied from
    * @param deleteCurrentRecords Flag that indicates the records in the domain zone should be deleted when doing this action
    */
   void copyDomainZoneRecords( String domainName, String fromDomainName, boolean deleteCurrentRecords );

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/records/soa-details-domain-zone/">Domain Zone SOA Details Documentation</a>
    * 
    * @param domainName Domain Zone to get SOA details for
    * @return SOA Details
    */
   SoaDetails getSoaDetails( String domainName );

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/records/modify-soa-domain-zone/">Modify Domain Zone SOA Details Documentation</a>
    * 
    * @param domainName Domain Zone to modify SOA details of
    * @param details Details to update to
    */
   void modifySoaDetails( String domainName, SoaDetails details );

   /**
    * 
    * <a href="https://www.cloudns.net/api-help/records/get-dynamic-url/">Gety Domain Zone Record Dynamic URL Documentation</a>
    * 
    * @param domainName Domain Zone that contains the record to get the Dynamic URL update URL for
    * @param recordId Id of the record to get the Dynamic URL for
    * @return Dynamic URL that can be used to update to the calling machine's IP address
    */
   DynamicUrl getDomainZoneRecordDynamicUrl( String domainName, Integer recordId );
}
