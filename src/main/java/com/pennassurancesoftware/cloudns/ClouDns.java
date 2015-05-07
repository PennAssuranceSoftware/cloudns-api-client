package com.pennassurancesoftware.cloudns;

import java.util.List;

import com.pennassurancesoftware.cloudns.dto.NameServer;

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
   List<NameServer> getAvailableNameServers();
}
