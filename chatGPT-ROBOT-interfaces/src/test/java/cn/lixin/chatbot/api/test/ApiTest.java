package cn.lixin.chatbot.api.test;

import cn.lixin.chatbot.api.ApiApplication;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

/*
*
* description :  单元测试
*
* */
public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();   // 使用一个Http的一个构建，用来封装我们的默认信息的

        HttpGet get = new HttpGet( "https://api.zsxq.com/v2/groups/15552554452212/topics?scope=all&count=20");  // 本身返回的就是get而不是post

//        get.addHeader("cookie","123123123");  // 本身查询就是需要cookie信息的
//        get.addHeader("Content-Type","1231231243");

        get.addHeader("cookie", "zsxq_access_token=7434B89C-B745-C6D9-C197-D727A5289B92_A64E43DA91C7A682; zsxqsessionid=010b297ad2585d9094d94febe1cdba9f; sensorsdata2015jssdkcross={\"distinct_id\":\"182522145842282\",\"first_id\":\"186ac4637ab682-0e2ff8224617bd-74525471-2073600-186ac4637acfae\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2YWM0NjM3YWI2ODItMGUyZmY4MjI0NjE3YmQtNzQ1MjU0NzEtMjA3MzYwMC0xODZhYzQ2MzdhY2ZhZSIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4MjUyMjE0NTg0MjI4MiJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"182522145842282\"},\"$device_id\":\"186ac4637ab682-0e2ff8224617bd-74525471-2073600-186ac4637acfae\"}; abtest_env=product");
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode()  == HttpStatus.SC_OK) {    // 判断返回的信息是否有问题，比如返回个404的话就报错

            String res = EntityUtils.toString(response.getEntity());  // 对象工具，将返回的结果转成String类型

            System.out.println(res);
        } else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void answer() throws IOException{
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();   // 使用一个Http的一个构建，用来封装我们的默认信息的

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/811852558214212/comments");   //根据分析URL 发现回答用的是post

        post.addHeader("cookie", "zsxq_access_token=7434B89C-B745-C6D9-C197-D727A5289B92_A64E43DA91C7A682; zsxqsessionid=010b297ad2585d9094d94febe1cdba9f; sensorsdata2015jssdkcross={\"distinct_id\":\"182522145842282\",\"first_id\":\"186ac4637ab682-0e2ff8224617bd-74525471-2073600-186ac4637acfae\",\"props\":{\"$latest_traffic_source_type\":\"直接流量\",\"$latest_search_keyword\":\"未取到值_直接打开\",\"$latest_referrer\":\"\"},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2YWM0NjM3YWI2ODItMGUyZmY4MjI0NjE3YmQtNzQ1MjU0NzEtMjA3MzYwMC0xODZhYzQ2MzdhY2ZhZSIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE4MjUyMjE0NTg0MjI4MiJ9\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"182522145842282\"},\"$device_id\":\"186ac4637ab682-0e2ff8224617bd-74525471-2073600-186ac4637acfae\"}; abtest_env=product");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"text\": \"你在说什么啊\\n\",\n" +
                "  \"image_ids\": [],\n" +
                "  \"mentioned_user_ids\": []\n" +
                "}";    // 根据格式就可以把text给替换掉

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));// 封装自己的入参信息

        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);//执行post信息

        if (response.getStatusLine().getStatusCode()  == HttpStatus.SC_OK) {    // 判断返回的信息是否有问题，比如返回个404的话就报错

            String res = EntityUtils.toString(response.getEntity());  // 对象工具，将返回的结果转成String类型

            System.out.println(res);
        } else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class,args);
    }

}
