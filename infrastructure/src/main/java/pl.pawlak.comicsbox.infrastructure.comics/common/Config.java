package pl.pawlak.comicsbox.infrastructure.comics.common;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;


@Configuration
@EnableElasticsearchRepositories(basePackages = "pl.pawlak.comicsbox")
@ComponentScan(basePackages = {"pl.pawlak.comicsbox"})
public class Config {
    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Bean
    public Client client() throws Exception {
        Settings settingsBuilder = Settings.builder()
                .put("cluster.name", EsClusterName).put("client.transport.sniff", true).put("client.transport.ignore_cluster_name", true).build();
        Client client = new PreBuiltTransportClient(settingsBuilder)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(EsHost), EsPort));

        return client;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
}
