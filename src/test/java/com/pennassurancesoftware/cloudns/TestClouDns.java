package com.pennassurancesoftware.cloudns;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pennassurancesoftware.cloudns.client.ClouDnsClient;
import com.pennassurancesoftware.cloudns.dto.DomainZone;
import com.pennassurancesoftware.cloudns.dto.DomainZoneStats;
import com.pennassurancesoftware.cloudns.dto.DynamicUrl;
import com.pennassurancesoftware.cloudns.dto.NameServer;
import com.pennassurancesoftware.cloudns.dto.NameServerUpdateStatus;
import com.pennassurancesoftware.cloudns.dto.Record;
import com.pennassurancesoftware.cloudns.dto.SoaDetails;
import com.pennassurancesoftware.cloudns.type.RecordType;
import com.pennassurancesoftware.cloudns.type.ZoneType;

public class TestClouDns {
   private static final String AUTH_ID = "445";
   private static final String AUTH_PASSWORD = "temp1010";

   private ClouDns client = new ClouDnsClient( AUTH_ID, AUTH_PASSWORD );

   @Test(groups = { "integration" }, enabled = true, dependsOnGroups = "get-domain", priority = 100)
   public void testDeleteDomainZone() throws Exception {
      // Fixture
      final String domainName = "domain.com";
      final String domainName2 = "domain2.com";

      // Call
      client.deleteDomainZone( domainName );
      client.deleteDomainZone( domainName2 );
   }

   @Test(groups = { "integration" }, enabled = true, dependsOnGroups = "get-domain", priority = 50)
   public void testDeleteDomainZoneRecord() throws Exception {
      // Fixture
      final String domainName = "domain.com";

      // Call
      Record delete = null;
      for( Record record : client.getDomainZoneRecords( domainName ) ) {
         if( RecordType.CNAME.equals( record.getType() ) ) {
            delete = record;
            break;
         }
      }
      Assert.assertNotNull( delete, "Could not find CNAME record to delete." );
      client.deleteDomainZoneRecord( domainName, delete.getId() );
   }

   @Test(groups = { "integration", "modify-domain" }, enabled = true, dependsOnGroups = "get-domain", priority = 49)
   public void testModifyDomainZoneRecord() throws Exception {
      // Fixture
      final String domainName = "domain.com";

      // Call
      Record modify = null;
      for( Record record : client.getDomainZoneRecords( domainName ) ) {
         if( RecordType.CNAME.equals( record.getType() ) ) {
            modify = record;
            break;
         }
      }
      Assert.assertNotNull( modify, "Could not find CNAME record to delete." );
      modify.setHost( "blah-modified" );
      client.modifyDomainZoneRecord( domainName, modify );
      System.out.println( "Modified Domain Records: " + client.getDomainZoneRecords( domainName ) );
   }

   @Test(groups = { "integration", "modify-domain" }, enabled = true, dependsOnGroups = "get-domain", priority = 49)
   public void testModifyDomainZoneSoaDetails() throws Exception {
      // Fixture
      final String domainName = "domain.com";

      // Call
      final SoaDetails modify = client.getSoaDetails( domainName );
      modify.setDefaultTtl( 21600 );
      client.modifySoaDetails( domainName, modify );
      System.out.println( "Modified SOA: " + client.getSoaDetails( domainName ) );
   }

   @Test(groups = { "integration", "modify-domain" }, enabled = true, dependsOnGroups = "get-domain", priority = 48)
   public void testCopyDomainZoneRecords() throws Exception {
      // Fixture
      final String fromDomainName = "domain.com";
      final String domainName = "domain2.com";
      final boolean deleteCurrentRecords = false;

      // Call
      client.copyDomainZoneRecords( domainName, fromDomainName, deleteCurrentRecords );
      System.out.println( "Copied Domain Records: " + client.getDomainZoneRecords( domainName ) );
   }

   @Test(groups = { "integration" }, enabled = true)
   public void testGetAvailableNameServers() throws Exception {
      // https://api.cloudns.net/dns/login.json?auth-id=445&auth-password=temp1010

      // Call
      final List<NameServer> result = client.getAvailableNameServers();

      // Assert
      Assert.assertNotNull( result, "No result returned" );
      Assert.assertFalse( result.isEmpty(), "No Available Name Servers found." );
      System.out.println( result );
   }

   @Test(groups = { "integration", "get-domain" }, enabled = true, dependsOnGroups = "create-domain")
   public void testGetDomainZones() throws Exception {
      // Fixture

      // Call
      final List<DomainZone> result = client.getDomainZones();

      // Assert
      System.out.println( result );
   }

   @Test(groups = { "integration", "get-domain" }, enabled = true, dependsOnGroups = "create-domain")
   public void testGetDomainZoneSoaDetails() throws Exception {
      // Fixture
      final String domainName = "domain.com";

      // Call
      final SoaDetails soa = client.getSoaDetails( domainName );

      // Assert
      System.out.println( "SOA: " + soa );
   }

   @Test(groups = { "integration", "get-domain" }, enabled = true, dependsOnGroups = "create-domain")
   public void testGetDomainZoneRecordDynamicUrl() throws Exception {
      // Fixture
      final String domainName = "domain.com";

      // Call
      Record a = null;
      for( Record record : client.getDomainZoneRecords( domainName ) ) {
         if( RecordType.A.equals( record.getType() ) ) {
            a = record;
            break;
         }
      }
      Assert.assertNotNull( a, "No A record found." );
      final DynamicUrl result = client.getDomainZoneRecordDynamicUrl( domainName, a.getId() );

      // Assert
      Assert.assertNotNull( result, "No Dynamic URL found." );
      System.out.println( "Dynamic URL: " + result );
   }

   @Test(groups = { "integration", "get-domain" }, enabled = true, dependsOnGroups = "create-domain")
   public void testGetDomainZoneStats() throws Exception {
      // Call
      final DomainZoneStats result = client.getDomainZoneStats();

      // Assert
      Assert.assertNotNull( result, "No result returned" );
      System.out.println( result );
   }

   @Test(groups = { "integration", "get-domain" }, enabled = true, dependsOnGroups = "create-domain")
   public void testGetDomainZoneUpdateStatus() throws Exception {
      // Fixture
      final String domainName = "domain.com";

      // Call
      final List<NameServerUpdateStatus> result = client.getDomainZoneUpdateStatus( domainName );

      // Assert
      System.out.println( result );
   }

   @Test(groups = { "integration", "get-domain" }, enabled = true, dependsOnGroups = "create-domain")
   public void testGetDomainZoneIsUpdated() throws Exception {
      // Fixture
      final String domainName = "domain.com";

      // Call
      boolean result = client.isDomainZoneUpdated( domainName );
      //      while( !result ) {
      //         System.out.println( "Domain Updated: " + result );
      //         Thread.sleep( 3000 );
      //         result = client.isDomainZoneUpdated( domainName );
      //      }

      // Assert
      System.out.println( "Domain Updated: " + result );
   }

   @Test(groups = { "integration", "get-domain" }, enabled = true, dependsOnGroups = "create-domain")
   public void testGetDomainZoneRecords() throws Exception {
      // Fixture
      final String domainName = "domain.com";

      // Call
      final List<Record> result = client.getDomainZoneRecords( domainName );

      // Assert
      System.out.println( "Domain Records: " + result );
   }

   @Test(groups = { "integration", "create-domain" }, enabled = true, priority = 1)
   public void testRegisterDomainZone() throws Exception {
      // Fixture
      final String domainName = "domain.com";
      final ZoneType type = ZoneType.Master;

      // Call
      client.registerDomainZone( domainName, type );
   }

   @Test(groups = { "integration", "create-domain" }, enabled = true, priority = 1)
   public void testRegisterDomainZone2() throws Exception {
      // Fixture
      final String domainName = "domain2.com";
      final ZoneType type = ZoneType.Master;

      // Call
      client.registerDomainZone( domainName, type );
   }

   @Test(groups = { "integration", "create-domain" }, enabled = true, dependsOnMethods = "testRegisterDomainZone", priority = 2)
   public void testAddDomainZoneRecord() throws Exception {
      // Fixture
      final String domainName = "domain.com";
      final Record record = new Record();
      record.setType( RecordType.CNAME );
      record.setHost( "blah-test" );
      record.setRecord( "proxy-16f8311e.pennassurancesoftware.svc.tutum.io" );
      record.setTtl( 3600 );

      final Record record2 = new Record();
      record2.setType( RecordType.A );
      record2.setHost( "dynamic-test" );
      record2.setRecord( "69.242.33.252" );
      record2.setTtl( 3600 );

      // Call
      client.addDomainZoneRecord( domainName, record );
      client.addDomainZoneRecord( domainName, record2 );
   }
}
