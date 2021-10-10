package com.ybj.es.config;

import com.beust.jcommander.internal.Lists;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class EsMultiClientConfig implements PooledObjectFactory<RestHighLevelClient> {


    @Override
    public PooledObject<RestHighLevelClient> makeObject() throws Exception {
        RestHighLevelClient client = buildClient(Lists.newArrayList("http://es-cn-zvp29oayp001mbgk5.elasticsearch.aliyuncs.com:9200"),
                "elastic", "CwWkTuF1AY14HA3OTo66");
        return new DefaultPooledObject<>(client);
    }

    @Override
    public void destroyObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {
        RestHighLevelClient highLevelClient = pooledObject.getObject();
        highLevelClient.close();
    }

    @Override
    public boolean validateObject(PooledObject<RestHighLevelClient> pooledObject) {
        return false;
    }

    @Override
    public void activateObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<RestHighLevelClient> pooledObject) throws Exception {

    }

    public RestHighLevelClient buildClient(List<String> uris, String userName, String password){
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(userName,password));
        HttpHost[] httpHosts = uris.stream().map(HttpHost::new).toArray(HttpHost[]::new);
        RestClientBuilder restClientBuilder = RestClient.builder(httpHosts);
        ConnectionKeepAliveStrategy strategy = (response ,context) -> {
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()){
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if(value != null && param.equalsIgnoreCase("timeout")){
                    return 1000;
                }
            }
            return 1000;
        };
        restClientBuilder.setRequestConfigCallback( requestConfigBuilder -> requestConfigBuilder.setConnectTimeout(-1).setSocketTimeout(-1) );
        return new RestHighLevelClient(restClientBuilder);

    }

}
