package com.pennassurancesoftware.cloudns.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pennassurancesoftware.cloudns.ClouDns;
import com.pennassurancesoftware.cloudns.dto.DomainZone;
import com.pennassurancesoftware.cloudns.dto.DomainZoneStats;
import com.pennassurancesoftware.cloudns.dto.NameServer;
import com.pennassurancesoftware.cloudns.dto.NameServerUpdateStatus;
import com.pennassurancesoftware.cloudns.dto.Record;
import com.pennassurancesoftware.cloudns.dto.Response;
import com.pennassurancesoftware.cloudns.exception.ClouDnsException;
import com.pennassurancesoftware.cloudns.exception.RequestUnsuccessfulException;
import com.pennassurancesoftware.cloudns.type.ZoneType;

/** ClouDNS client wrapper methods Implementation */
public class ClouDnsClient implements ClouDns {
   private static final Integer DEFAULT_PAGE_SIZE = 100;

   private Gson deserialize;
   private JsonParser jsonParser;
   private Logger LOG = LoggerFactory.getLogger( ClouDnsClient.class );
   private Gson serialize;
   protected String apiHost = "api.cloudns.net";
   protected String authId;
   protected String authPassword;
   protected HttpClient httpClient;

   /**
    * Tutum Client Constructor
    *
    * @param authId a {@link String} Authorized ID that can be used to access the service
    * @param authPassword a {@link String} Authorized Password that can be used to access the service
    * @param httpClient a {@link HttpClient} object
    */
   public ClouDnsClient( String authId, String authPassword ) {
      this( authId, authPassword, null );
   }

   /**
    * Tutum Client Constructor
    *
    * @param authId a {@link String} Authorized ID that can be used to access the service
    * @param authPassword a {@link String} Authorized Password that can be used to access the service
    * @param httpClient a {@link HttpClient} object
    */
   public ClouDnsClient( String authId, String authPassword, HttpClient httpClient ) {
      this.authId = authId;
      this.authPassword = authPassword;
      this.httpClient = httpClient;
      initialize();
   }

   @Override
   public void addDomainZoneRecord( String domainName, Record record ) {
      final String recordType = record.getType().value();
      final String host = record.getHost();
      final String recordText = record.getRecord();
      final Integer ttl = record.getTtl();
      final Integer priority = record.getPriority();
      final Integer weight = record.getWeight();
      final Integer port = record.getPort();
      final Integer frame = record.getFrame();
      final String frameTitle = record.getFrameTitle();
      final String frameKeywords = record.getFrameKeywords();
      final String frameDescription = record.getFrameDescription();
      final Integer savePath = record.getSavePath();
      final Integer redirectType = record.getRedirectType();
      final Integer mail = record.getMail();
      final Integer txt = record.getTxt();
      final Integer algorithm = record.getAlgorithm();
      final Integer fptype = record.getSshFpAlgorithm();
      final Object[] params = { domainName, recordType, host, recordText, ttl, priority, weight, port, frame, frameTitle, frameKeywords, frameDescription, savePath, redirectType, mail, txt, algorithm, fptype };
      final Response result = ( Response )perform( new ApiRequest( ApiAction.ADD_DOMAIN_ZONE_RECORD, params ) ).getData();
      validateResponse( result );
   }

   @Override
   public void deleteDomainZone( String domainName ) {
      final Object[] params = { domainName };
      final Response result = ( Response )perform( new ApiRequest( ApiAction.DELETE_DOMAIN_ZONE, params ) ).getData();
      validateResponse( result );
   }

   @Override
   public void deleteDomainZoneRecord( String domainName, Integer recordId ) {
      final Object[] params = { domainName, recordId };
      final Response result = ( Response )perform( new ApiRequest( ApiAction.DELETE_DOMAIN_ZONE_RECORD, params ) ).getData();
      validateResponse( result );
   }

   @Override
   public List<NameServer> getAvailableNameServers() {
      final NameServer[] result = ( NameServer[] )perform( new ApiRequest( ApiAction.AVAILABLE_NAME_SERVERS ) ).getData();
      return Arrays.asList( result );
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Record> getDomainZoneRecords( String domainName ) {
      final Object[] params = { domainName };
      final Map<String, Record> result = ( Map<String, Record> )perform( new ApiRequest( ApiAction.GET_DOMAIN_ZONE_RECORDS, params ) ).getData();
      return new ArrayList<Record>( result.values() );
   }

   @Override
   public List<DomainZone> getDomainZones() {
      final Object[] params = { 1, DEFAULT_PAGE_SIZE };
      final DomainZone[] result = ( DomainZone[] )perform( new ApiRequest( ApiAction.GET_DOMAIN_ZONES, params ) ).getData();
      return Arrays.asList( result );
   }

   @Override
   public DomainZoneStats getDomainZoneStats() {
      final DomainZoneStats result = ( DomainZoneStats )perform( new ApiRequest( ApiAction.GET_DOMAIN_ZONE_STATS ) ).getData();
      return result;
   }

   @Override
   public List<NameServerUpdateStatus> getDomainZoneUpdateStatus( String domainName ) {
      final Object[] params = { domainName };
      final NameServerUpdateStatus[] result = ( NameServerUpdateStatus[] )perform( new ApiRequest( ApiAction.GET_DOMAIN_ZONE_UPDATE_STATUS, params ) ).getData();
      return Arrays.asList( result );
   }

   @Override
   public boolean isDomainZoneUpdated( String domainName ) {
      final Object[] params = { domainName };
      final Boolean result = ( Boolean )perform( new ApiRequest( ApiAction.GET_DOMAIN_ZONE_IS_UPDATED, params ) ).getData();
      return result;
   }

   @Override
   public void modifyDomainZoneRecord( String domainName, Record record ) {
      final Integer recordId = record.getId();
      final String host = record.getHost();
      final String recordText = record.getRecord();
      final Integer ttl = record.getTtl();
      final Integer priority = record.getPriority();
      final Integer weight = record.getWeight();
      final Integer port = record.getPort();
      final Integer frame = record.getFrame();
      final String frameTitle = record.getFrameTitle();
      final String frameKeywords = record.getFrameKeywords();
      final String frameDescription = record.getFrameDescription();
      final Integer savePath = record.getSavePath();
      final Integer redirectType = record.getRedirectType();
      final Integer mail = record.getMail();
      final Integer txt = record.getTxt();
      final Integer algorithm = record.getAlgorithm();
      final Integer fptype = record.getSshFpAlgorithm();
      final Object[] params = { domainName, recordId, host, recordText, ttl, priority, weight, port, frame, frameTitle, frameKeywords, frameDescription, savePath, redirectType, mail, txt, algorithm, fptype };
      final Response result = ( Response )perform( new ApiRequest( ApiAction.MODIFY_DOMAIN_ZONE_RECORD, params ) ).getData();
      validateResponse( result );

   }

   @Override
   public void registerDomainZone( String domainName, ZoneType type ) {
      final Object[] params = { domainName, type.value() };
      final Response result = ( Response )perform( new ApiRequest( ApiAction.REGISTER_DOMAIN_ZONE, params ) ).getData();
      validateResponse( result );
   }

   private String createPath( ApiRequest request ) {
      String path = request.getApiAction().getPath();
      path = request.getParams() != null ? String.format( path, request.getParams() ) : path;
      path = path.startsWith( "/" ) ? path.substring( 1 ) : path;
      final StringBuffer result = new StringBuffer();
      result.append( String.format( "/dns/%s", path ) );
      result.append( path.contains( "?" ) ? "&" : "?" );
      result.append( getAuthParamString() );
      return result.toString();
   }

   private StringEntity createRequestData( ApiRequest request ) {
      StringEntity data = null;
      if( null != request.getData() ) {
         final String inputData = serialize.toJson( request.getData() );
         data = new StringEntity( inputData, ContentType.create( Constants.JSON_CONTENT_TYPE ) );
      }
      return data;
   }

   private URI createUri( ApiRequest request ) {
      URIBuilder ub = new URIBuilder();
      ub.setScheme( Constants.HTTPS_SCHEME );
      ub.setHost( apiHost );
      ub.setPath( createPath( request ) );

      for( String paramName : request.getQueryParams().keySet() ) {
         final List<String> values = request.getQueryParams().get( paramName );
         for( String value : values ) {
            ub.setParameter( paramName, value );
         }
      }

      URI uri = null;
      try {
         uri = ub.build();
      }
      catch( URISyntaxException use ) {
         LOG.error( use.getMessage(), use );
      }

      return uri;
   }

   private String doDelete( URI uri ) {
      HttpDelete delete = new HttpDelete( uri );
      delete.setHeaders( getRequestHeaders() );
      delete.setHeader( HttpHeaders.CONTENT_TYPE, Constants.FORM_URLENCODED_CONTENT_TYPE );
      return executeHttpRequest( delete );
   }

   private String doGet( URI uri ) {
      HttpGet get = new HttpGet( uri );
      get.setHeaders( getRequestHeaders() );
      return executeHttpRequest( get );
   }

   private String doPatch( URI uri, StringEntity entity ) {
      HttpPatch patch = new HttpPatch( uri );
      patch.setHeaders( getRequestHeaders() );

      if( null != entity ) {
         patch.setEntity( entity );
      }

      return executeHttpRequest( patch );
   }

   private String doPost( URI uri, StringEntity entity ) {
      final HttpPost post = new HttpPost( uri );
      post.setHeaders( getRequestHeaders() );

      if( null != entity ) {
         post.setEntity( entity );
         LOG.debug( "POST Message: {}", readString( entity ) );
      }

      return executeHttpRequest( post );
   }

   private String doPut( URI uri, StringEntity entity ) {
      HttpPut put = new HttpPut( uri );
      put.setHeaders( getRequestHeaders() );

      if( null != entity ) {
         put.setEntity( entity );
      }

      return executeHttpRequest( put );
   }

   private String evaluateResponse( HttpRequestBase request, HttpResponse httpResponse ) {
      int statusCode = httpResponse.getStatusLine().getStatusCode();
      String response = "";

      if( HttpStatus.SC_OK == statusCode || HttpStatus.SC_CREATED == statusCode
            || HttpStatus.SC_ACCEPTED == statusCode ) {
         response = httpResponseToString( httpResponse );
      }
      else if( HttpStatus.SC_NO_CONTENT == statusCode ) {
         // in a way its always true from client perspective if there is no exception.
         response = String.format( Constants.NO_CONTENT_JSON_STRUCT, statusCode );
      }

      if( ( statusCode >= 400 && statusCode < 510 ) ) {
         String jsonStr = httpResponseToString( httpResponse );
         jsonStr = jsonStr == null || "".equals( jsonStr ) ? "{}" : jsonStr;
         LOG.error( "Target URL: {}", request.getURI() );
         LOG.error( "JSON Message: {}", getRequestMessage( request ) );
         LOG.error( "JSON Response: {}", jsonStr );

         final JsonObject jsonObj = jsonParser.parse( jsonStr ).getAsJsonObject();
         final String message = jsonObj.has( "error" ) ? jsonObj.get( "error" ).getAsString() : jsonStr;
         String errorMsg = String.format( "\nHTTP Status Code: %s\nError Message: %s", statusCode, message );
         LOG.debug( errorMsg );

         throw new ClouDnsException( errorMsg, "N/A", statusCode );
      }

      return response;
   }

   private String executeHttpRequest( HttpRequestBase request ) {
      String response = "";
      try {
         final HttpResponse httpResponse = httpClient.execute( request );
         LOG.debug( "HTTP Response Object:: " + httpResponse );
         response = evaluateResponse( request, httpResponse );
         LOG.debug( "Parsed Response:: " + response );
      }
      catch( ClientProtocolException cpe ) {
         throw new RequestUnsuccessfulException( cpe.getMessage(), cpe );
      }
      catch( IOException ioe ) {
         throw new RequestUnsuccessfulException( ioe.getMessage(), ioe );
      }
      finally {
         request.releaseConnection();
      }
      return response;
   }

   private String getAuthParamString() {
      return String.format( "auth-id=%s&auth-password=%s", authId, authPassword );
   }

   private Header[] getRequestHeaders() {
      Header[] headers =
      {
            new BasicHeader( "X-User-Agent", "ClouDNS API Client by PennAssuranceSoftware.com" ),
            new BasicHeader( "Content-Type", Constants.JSON_CONTENT_TYPE ),
      };
      return headers;
   }

   private String getRequestMessage( HttpRequestBase request ) {
      String result = null;
      if( request instanceof HttpEntityEnclosingRequestBase ) {
         final HttpEntity entity = ( ( HttpEntityEnclosingRequestBase )request ).getEntity();
         result = readString( entity );
      }
      return result;
   }

   private String httpResponseToString( HttpResponse httpResponse ) {
      String response = "";
      if( null != httpResponse.getEntity() ) {
         try {
            response = EntityUtils.toString( httpResponse.getEntity(), Constants.UTF_8 );
         }
         catch( ParseException pe ) {
            LOG.error( pe.getMessage(), pe );
         }
         catch( IOException ioe ) {
            LOG.error( ioe.getMessage(), ioe );
         }
      }
      return response;
   }

   private void initialize() {
      this.deserialize = new GsonBuilder().setDateFormat( Constants.DATE_FORMAT ).create();
      this.serialize = new GsonBuilder().setDateFormat( Constants.DATE_FORMAT ).excludeFieldsWithoutExposeAnnotation().create();
      this.jsonParser = new JsonParser();
      if( null == this.httpClient ) {
         this.httpClient = new DefaultHttpClient( new PoolingClientConnectionManager() );
      }
   }

   private ApiResponse perform( ApiRequest request ) {
      URI uri = createUri( request );
      String response = null;

      if( RequestMethod.GET == request.getMethod() ) {
         response = doGet( uri );
      }
      else if( RequestMethod.POST == request.getMethod() ) {
         response = doPost( uri, createRequestData( request ) );
      }
      else if( RequestMethod.PUT == request.getMethod() ) {
         response = doPut( uri, createRequestData( request ) );
      }
      else if( RequestMethod.PATCH == request.getMethod() ) {
         response = doPatch( uri, createRequestData( request ) );
      }
      else if( RequestMethod.DELETE == request.getMethod() ) {
         response = doDelete( uri );
      }

      final ApiResponse apiResponse = new ApiResponse( request.getApiAction(), true );

      try {
         apiResponse.setData( deserialize.fromJson( response, request.getResponseType() ) );
      }
      catch( JsonSyntaxException jse ) {
         LOG.error( "Error occurred while parsing response: {}", response, jse );
         apiResponse.setRequestSuccess( false );
      }

      LOG.debug( "API Response:: " + apiResponse.toString() );

      return apiResponse;
   }

   private String readString( HttpEntity entity ) {
      String result = null;
      if( entity != null && entity instanceof StringEntity ) {
         result = readString( ( StringEntity )entity );
      }
      return result;
   }

   private String readString( InputStream inputStream ) {
      try {
         final ByteArrayOutputStream into = new ByteArrayOutputStream();
         byte[] buf = new byte[4096];
         for( int n; 0 < ( n = inputStream.read( buf ) ); ) {
            into.write( buf, 0, n );
         }
         into.close();
         return new String( into.toByteArray(), "UTF-8" ); // Or whatever encoding
      }
      catch( Exception exception ) {
         throw new RuntimeException( "Error reading InputStream", exception );
      }
   }

   private String readString( StringEntity entity ) {
      try {
         return entity != null ? readString( entity.getContent() ) : null;
      }
      catch( Exception exception ) {
         throw new RuntimeException( "Error reading String Entity", exception );
      }
   }

   private void validateResponse( Response result ) {
      if( !result.isSuccess() ) {
         throw new RuntimeException( String.format( "Reponse failure: %s", result.getStatusDescription() ) );
      }
   }
}
