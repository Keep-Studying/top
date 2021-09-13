package com.study.solr.service;

import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * SolrServiceTest
 *
 * @author boyan
 * @version : SolrServiceTest.java, v 0.1 2021/9/11 15:00 boyan Exp $
 */
public class SolrServiceTest {

    private SolrService solrService;

    @Before
    public void init(){
        solrService = new SolrService();
        solrService.init(SolrService.Mode.HTTP);
    }

    @Test public void createSolrClient() {

    }

    @Test public void addDoc() {
        try {
            solrService.addDoc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test public void deleteDocumentById() {
        try {
            solrService.deleteDocumentById();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test public void querySolr() {
        try {
            solrService.querySolr();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
    @Test public void request() {
        try {
            String collectionName = "boyan_test";

            ArrayList<SolrInputDocument> documents = new ArrayList<>();
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", "doc113");
            document.addField("name", "商品1");
            document.addField("description", "商品描述1");
            documents.add(document);

            NamedList<Object> request = solrService.request(collectionName, documents);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}