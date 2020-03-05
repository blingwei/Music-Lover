package com.blingwei.musicService.dao.esServise;

import com.blingwei.musicService.pojo.Essay;
import com.blingwei.musicService.pojo.EssayForElastic;
import net.sf.json.JSONObject;
import org.apache.shiro.util.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.ScrollableHitSource;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EssayWithSongEsService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private static RestHighLevelClient staticRestHighLevelClient;

    @PostConstruct
    public void init(){
        staticRestHighLevelClient = restHighLevelClient;
    } //给static的字段注入


    private static final String index = "essay";
    private static final String type= "essay";

    public static void add(Object data)  {
        try {
            IndexRequest indexRequest = new IndexRequest();
            Long id = System.currentTimeMillis();
            indexRequest.id(id.toString());
            String source = JSONObject.fromObject(data).toString();
            indexRequest.source(source, XContentType.JSON);
            indexRequest.index(index);
            indexRequest.type(type);
            staticRestHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static List<EssayForElastic> find(String message){
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);

//        HighlightBuilder sourceBuilder = new HighlightBuilder();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        if("".equals(message)){
            sourceBuilder.query(QueryBuilders.matchAllQuery());
        }else{
            //字段优先级权重
            Map<String, Float> weightMap = new HashMap<>();
            weightMap.put("songName", 3.0F);
            weightMap.put("title", 2.0F);
            weightMap.put("intor", 1.0F);
            //设置关键值高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("title");
            HighlightBuilder.Field highlightSongName = new HighlightBuilder.Field("songName");
            HighlightBuilder.Field highlightIntor= new HighlightBuilder.Field("intor");
            highlightBuilder.field(highlightSongName);
            highlightBuilder.field(highlightTitle);
            highlightBuilder.field(highlightIntor);
            highlightBuilder.preTags("<span style='color: red'>");//高亮的字段会在前面加上该标签
            highlightBuilder.postTags("</span>");
            sourceBuilder.highlighter(highlightBuilder);

            sourceBuilder.query(QueryBuilders.multiMatchQuery(message, "songName","title", "intor").fields(weightMap));//先匹配歌曲，再匹配标题，最后匹配简介
        }
        searchRequest.source(sourceBuilder);
        try {
            List<EssayForElastic> list = new ArrayList<>();
            SearchResponse response = staticRestHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            for(SearchHit hit: hits){
                EssayForElastic result = (EssayForElastic) JSONObject.toBean(JSONObject.fromObject(hit.getSourceAsString()), EssayForElastic.class);
                //设置高亮字段
                if (hit.getHighlightFields() != null)  {
                    for( HighlightField field: hit.getHighlightFields().values()){
                       switch (field.name()){//加入自定义的样式
                           case "title": result.setTitle(field.fragments()[0].string()); break;
                           case "songName": result.setSongName(field.fragments()[0].string()); break;
                           case "intor": result.setIntor(field.fragments()[0].string()); break;
                       }
                    }
                }
                list.add(result);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void delete(){

    }




}
