package ehss.elasticsearch.index.util;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Service
public class Indexer {

    @Autowired
    RestHighLevelClient restHighLevelClient;



    public void index(String index, String type, String id, String jsonString) throws Exception{
        IndexRequest request = new IndexRequest(index,type,id);

        request.source(jsonString, XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(request);
    }

    public void index(String index, String type, String id, Map<String, Object> jsonMap ) throws IOException {
        IndexRequest request = new IndexRequest(index,type,id);

        request.source(jsonMap);
        IndexResponse indexResponse = restHighLevelClient.index(request);
    }

    public void index(String index, String type, String id, XContentBuilder jsonBuilder) throws IOException {
        IndexRequest request = new IndexRequest(index,type,id);

        request.source(jsonBuilder);
        IndexResponse indexResponse = restHighLevelClient.index(request);
    }


}
