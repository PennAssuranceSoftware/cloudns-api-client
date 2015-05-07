package com.pennassurancesoftware.cloudns;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pennassurancesoftware.cloudns.client.ClouDnsClient;
import com.pennassurancesoftware.cloudns.dto.NameServer;

public class TestClouDns {

   @Test(groups = { "integration" }, enabled = true)
   public void testCreateCnameRecord() throws Exception {
      // https://api.cloudns.net/dns/login.json?auth-id=445&auth-password=london10

      // Fixture
      final String authId = "445";
      final String authPassword = "temp1010";

      // Call
      final ClouDns client = new ClouDnsClient( authId, authPassword );
      final List<NameServer> result = client.getAvailableNameServers();

      // Assert
      Assert.assertNotNull( result, "No result returned" );
      Assert.assertFalse( result.isEmpty(), "No Available Name Servers found." );
      System.out.println( result );
   }
}
