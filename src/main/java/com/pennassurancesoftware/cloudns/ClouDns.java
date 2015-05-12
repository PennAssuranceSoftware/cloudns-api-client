package com.pennassurancesoftware.cloudns;

import java.util.List;

import com.pennassurancesoftware.cloudns.dto.DomainZone;
import com.pennassurancesoftware.cloudns.dto.DomainZoneStats;
import com.pennassurancesoftware.cloudns.dto.NameServer;
import com.pennassurancesoftware.cloudns.dto.NameServerUpdateStatus;
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
}
