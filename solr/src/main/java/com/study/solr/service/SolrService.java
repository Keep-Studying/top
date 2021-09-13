/**
 * Aloudata.com Inc.
 * Copyright (c) 2021-2021 All Rights Reserved.
 */
package com.study.solr.service;

import com.alibaba.fastjson.JSON;
import lombok.NonNull;
import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.util.NamedList;

import java.io.IOException;
import java.util.Collection;

/**
 * SolrService
 *
 * @author boyan
 * @version : SolrService.java, v 0.1 2021-09-11 01:47 boyan
 */
public class SolrService {

    //solr服务器所在的地址，collection1为的文档库目录
    private final static String SOLR_URL = "http://121.199.78.155:8983/solr/";
    private final static String ZK_HOST = "http://121.199.78.155:2181";

    private SolrClient solrClient;

    public void init(Mode mode){
        solrClient = createSolrClient(mode);
    }

    enum Mode{
        CLOUD("cloud"),
        HTTP("http"),
        ;

        private String code;

        Mode(String code) {
            this.code = code;
        }
    }

    public SolrClient createSolrClient(@NonNull Mode mode){
        SolrClient solrClient = null;
        final ModifiableSolrParams clientParams = new ModifiableSolrParams();

        switch (mode) {
            case CLOUD:{
                final CloudSolrClient cloudServer = new CloudSolrClient.Builder().withZkHost(ZK_HOST).withZkChroot("chroot")
                    .withLBHttpSolrClientBuilder(
                        new LBHttpSolrClient.Builder()
                            .withHttpSolrClientBuilder(new HttpSolrClient.Builder().withInvariantParams(clientParams))
                            .withBaseSolrUrls(SOLR_URL)
                    )
                    .sendUpdatesOnlyToShardLeaders()
                    .build();
                cloudServer.connect();
                solrClient = cloudServer;
                break;
            }
            case HTTP:{
                final HttpClient client = HttpClientUtil.createClient(clientParams);
                solrClient = new LBHttpSolrClient.Builder()
                    .withHttpClient(client)
                    .withBaseSolrUrls(SOLR_URL)
                    .build();
                break;
            }
            default:
        }
        return solrClient;
    }

    /**
     * 往索引库添加文档
     *
     * @throws SolrServerException
     * @throws IOException
     */
    public void addDoc() throws Exception {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "doc01");
        document.addField("name", "商品1");
        document.addField("description", "商品描述1");
//        SolrClient solrClient = createSolrClient(Mode.HTTP);
        ////把文档写入索引库
        solrClient.add(document);
        //提交
        solrClient.commit();

    }

    /**
     * 根据ID从索引库删除文档
     *
     * @throws SolrServerException
     * @throws IOException
     */
    public void deleteDocumentById() throws SolrServerException, IOException {
//        SolrClient solrClient = createSolrClient(Mode.HTTP);

        solrClient.deleteById("doc01");
        solrClient.commit();

    }

    /**
     * 根据设定的查询条件进行文档字段的查询
     * @throws Exception
     */
    public void querySolr() throws Exception {

//        SolrClient solrClient = createSolrClient(Mode.HTTP);
        SolrQuery query = new SolrQuery();

        //下面设置solr查询参数

        //query.set("q", "*:*");// 参数q  查询所有
        query.set("q", "*:*");//相关查询，比如某条数据某个字段含有周、星、驰三个字  将会查询出来

        //参数fq, 给query增加过滤查询条件
        //query.addFacetQuery("id:[0 TO 9]");
        // query.addFilterQuery("description:商品");

        //参数df,给query设置默认搜索域，从哪个字段上查找
//        query.set("df", "name");
        query.set("id","14619");

        //参数sort,设置返回结果的排序规则
        //query.setSort("id",SolrQuery.ORDER.desc);

        //设置分页参数
        query.setStart(0);
        query.setRows(10);

        //设置高亮显示以及结果的样式
        query.setHighlight(true);
        query.addHighlightField("name");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font>");

        //执行查询
        QueryResponse response = solrClient.query(query);

        //获取返回结果
        SolrDocumentList resultList = response.getResults();

        for(SolrDocument document: resultList){
            System.out.println(JSON.toJSONString(document));
        }

    }

    public NamedList<Object>  request(String collectionName, Collection<SolrInputDocument> documents)
        throws SolrServerException, IOException {
        return solrClient.request(newUpdateRequest().add(documents), collectionName);
    }
    private UpdateRequest newUpdateRequest() {
        final UpdateRequest req = new UpdateRequest();
        return req;
    }

}