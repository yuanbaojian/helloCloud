package com.ybj.es.service;

import com.ybj.es.config.ConnectionPool;
import com.ybj.es.model.Book;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONValue;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ConnectionPool connectionPool;

    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private MailProperties mailProperties;

    @Test
    public void test1(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername());
        message.setTo("1793870688@qq.com");
        message.setSubject("from qq");
        message.setText("hello");
        mailSender.send(message);
    }

    /**
     * 创建索引
     */
    @Test
    public void clientTest(){
        CreateIndexRequest request = new CreateIndexRequest("ybj_index_1");
        try {
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
            log.info("createIndexResponse 为{}",createIndexResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断索引是否存在
     */
    @Test
    public void getIndex(){
        GetIndexRequest request = new GetIndexRequest("ybj_index_book");
        try {
            boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
            log.info("exists 为{}",exists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获得索引别名
     */
    @Test
    public void getAliasIndex(){
        GetAliasesRequest request = new GetAliasesRequest("ybj_index_book");
        try {
            boolean exists = restHighLevelClient.indices().existsAlias(request, RequestOptions.DEFAULT);
            log.info(">>> exists 为{}",exists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex(){
        DeleteIndexRequest request = new DeleteIndexRequest("ybj_index_book");
        try {
            AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
            log.info(">>> delete 为{}",delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量插入
     */
    @Test
    public void bulkInsert() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        List<Book> list = new ArrayList<>();
        list.add(new Book(1,"东游记","阿Q"));
        list.add(new Book(2,"北游记","阿W"));
        list.add(new Book(3,"南游记","阿E"));
        list.add(new Book(4,"三国演义","阿R"));
        list.add(new Book(5,"五国演义","阿T"));
        list.add(new Book(6,"六国演义","阿Y"));
        for (int i = 0; i < list.size(); i++) {
            IndexRequest indexRequest = new IndexRequest("ybj_index_book");
            indexRequest.id(i+1 +"").source(JSONValue.toJSONString(list.get(i)), XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        log.info("bulk 为{}",bulk);
    }


    /**
     * 批量查询
     * @throws IOException
     */
    @Test
    public void bulkSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("ybj_index_book");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 模糊查询有点问题
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("author.keyword", "*R*").fuzziness(Fuzziness.AUTO);
        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("author.keyword", "*R*");
        // 准确匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("id", "2");
        TermQueryBuilder termQueryBuilder2 = QueryBuilders.termQuery("author.keyword", "阿Y");
        //全部匹配
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.query(wildcardQueryBuilder);
        searchRequest.source(sourceBuilder);

        //高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("author.keyword");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("/<span>");
        highlightBuilder.requireFieldMatch(false);
        sourceBuilder.highlighter(highlightBuilder);


        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        // 高亮处理
        for (SearchHit hit : hits) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField author = highlightFields.get("author.keyword");
            if(Objects.nonNull(author)){
                String result = "";
                for (Text item : author.getFragments()) {
                    result+=item;
                }
                hit.getSourceAsMap().put("author",result);
            }
            log.info(">>> hit 为{}",hit.getSourceAsMap());
        }

    }

    @Test
    public void searchByIn(){
        SearchRequest searchRequest = new SearchRequest("ybj_index_book");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("bookName.keyword","东游记"));
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            log.info("结果为{}",search);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建索引，保存信息
    @Test
    public void createAndSave() throws IOException {
        Book book = new Book(1,"西游记","吴承恩");
        IndexRequest indexRequest = new IndexRequest("ybj_index_book_green");
        indexRequest.id("2");
        indexRequest.timeout("1s");

        indexRequest.source(JSONValue.toJSONString(book), XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        log.info("index 为{}",index);

    }


    //保存信息
    @Test
    public void test(){
        Book book = new Book();
        book.setAuthor("曹雪芹");
        book.setBookName("红楼梦");
        book.setId(110);
        bookService.save(book);

        Random random = new Random();
        for(int i = 0; i < 100; i++) {
            book.setAuthor("唐伯虎");
            book.setBookName("小鸡啄米" + random.nextInt(100));
            book.setId(i);
            bookService.save(book);
        }

    }



    //查询数据1
    @Test
    public void test2(){
        List<Book> bookList =  bookService.findBooksByBookName("唐");
        for (Book book : bookList) {
            System.out.println("book = " + book);
        }
    }

    //查询数据
    @Test
    public void test3(){
        Optional<Book> book = bookService.findById(1);
        Book book1 = book.get();
        System.out.println("book1.toString() = " + book1.toString());
    }

    @Test
    void findBooksByBookName() {}

    @Test
    void findBooksByBookNameLike() {}

    @Test
    void findAllByAuthor() {}

    @Test
    void findBooksByBookNameAndAuthor() {}

    @Test
    void findAllByBookNameLikeAndAndAuthorIsLike() {
        List<Book> bookList = bookService.findAllByBookNameLikeAndAndAuthorIsLike("米", "虎");
        System.out.println("bookList = " + bookList);
    }

    //    分页
    @Test
    public void pageSearch(){
        // MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        // SearchQuery searchQuery = new NativeSearchQuery(matchAllQueryBuilder)
        //         .setPageable(PageRequest.of(1,10));
        // Page<Book> books = bookService.search(searchQuery);
        // for (Book book : books) {
        //     System.out.println("book.toString() = " + book.toString());
        // }
    }



}