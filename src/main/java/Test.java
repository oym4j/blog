import java.net.UnknownHostException;

/**
 * @author qiang.xie
 * @date 2016/12/28
 */
public class Test {

    public static void main(String[] arg) throws UnknownHostException {
//        List<BookIndex> nodes = new ArrayList<>();
//
//        BookIndex apple2 = new BookIndex();
//        apple2.setUrl("1.2");
//        apple2.setId("1.2");
//        apple2.setLabel("1章第2节");
//        apple2.setPath("0,1");
//        nodes.add(apple2);
//
//
//        BookIndex lizi = new BookIndex();
//        lizi.setId("2");
//        lizi.setLabel("第二章");
//        lizi.setPath("0");
//
//        lizi.setUrl("2");
//        nodes.add(lizi);
//
//        BookIndex apple3 = new BookIndex();
//        apple3.setUrl("2.1");
//        apple3.setId("2.1");
//        apple3.setLabel("2章第1节");
//        apple3.setPath("0,2");
//        nodes.add(apple3);
//
//        BookIndex fruit = new BookIndex();
//        fruit.setId("1");
//        fruit.setLabel("第一章");
//        fruit.setOrder(1);
//        fruit.setUrl("1");
//        fruit.setPath("0");
//        nodes.add(fruit);
//
//        BookIndex apple = new BookIndex();
//        apple.setUrl("1.1");
//        apple.setId("1.1");
//        apple.setPath("0,1");
//        apple.setLabel("1章第一节");
//        nodes.add(apple);
//
//
//        BookIndex apple4 = new BookIndex();
//        apple4.setUrl("2.2");
//        apple4.setId("2.2");
//        apple4.setLabel("2章第2节");
//        apple4.setPath("0,2");
//        nodes.add(apple4);
//
//        String url = "1.1";
//        GitBookCurrentIndex gitBookIndex = new GitBookCurrentIndex(nodes);
//        gitBookIndex.build(url);
//        System.out.println(gitBookIndex.getCurrent() + ";" + gitBookIndex.getPrev() + ";" + gitBookIndex.getNext());
//        System.out.println(gitBookIndex.getBookJson());

//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//
//        //  if (!client.admin().indices().prepareExists("blog").get().isExists()) {

        String mapping = "{\n" +
                "      \"properties\": { \n" +
                "        \"title\":    { \"type\": \"text\"  }, \n" +
                "        \"secondTitle\":    { \"type\": \"text\"  }, \n" +
                "        \"catalog\":    { \"type\": \"keyword\"  },\n" +
                "        \"auther\":    { \"type\": \"keyword\", \"index\":\"no\"  },  \n" +
                "        \"img\":    { \"type\": \"text\", \"index\":\"no\"  },  \n" +
                "        \"md\":     { \"type\": \"text\",\"index\":\"not_analyzed\"}, \n" +
                "        \"display\":  {\n" +
                "          \"type\":   \"text\",\n" +
                "          \"index\":\"no\"\n" +
                "          \n" +
                "        },\n" +
                "        \"publishTime\":  {\n" +
                "          \"type\":   \"date\", \n" +
                "          \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
                "        }\n" +
                "      }\n" +
                "}\n";
      //  client.admin().indices().preparePutMapping("blog").setType("blog").setSource(mapping);
//            client.admin().indices().prepareCreate("blog").setSettings(Settings.builder()
//                    .put("index.number_of_shards", 2)
//                    .put("index.number_of_replicas", 0)
//            ).addMapping("blog", "{\n" +
//                    "      \"properties\": { \n" +
//                    "        \"title\":    { \"type\": \"text\"  }, \n" +
//                    "        \"secondTitle\":    { \"type\": \"text\"  }, \n" +
//                    "        \"catalog\":    { \"type\": \"keyword\"  },\n" +
//                    "        \"auther\":    { \"type\": \"keyword\", \"index\":\"no\"  },  \n" +
//                    "        \"img\":    { \"type\": \"text\", \"index\":\"no\"  },  \n" +
//                    "        \"md\":     { \"type\": \"text\",\"index\":\"not_analyzed\"}, \n" +
//                    "        \"display\":  {\n" +
//                    "          \"type\":   \"text\",\n" +
//                    "          \"index\":\"no\"\n" +
//                    "          \n" +
//                    "        },\n" +
//                    "        \"publishTime\":  {\n" +
//                    "          \"type\":   \"date\", \n" +
//                    "          \"format\": \"strict_date_optional_time||epoch_millis\"\n" +
//                    "        }\n" +
//                    "      }\n" +
//                    "}\n").get();


        //  }


//        System.out.println(client.prepareDelete("blog", "blog", "1").get());
//
//        Blog blog = new Blog();
//        blog.setId("1");
//        blog.setTitle("java2");
//        blog.setDisplay("Java2，时间");
//        blog.setSecondTitle("wefafa");
//        blog.setPublishTime(new Date());
//        blog.setCatalog("java");
//        System.out.println(client.prepareIndex("blog", "blog").setId(blog.getId())
//                .setSource(JSON.toJSONString(blog))
//                .get());
//
//        blog.setId("2");
//        blog.setPublishTime(new Date());
//        blog.setCatalog("spring");
//        client.prepareIndex("blog", "blog").setId(blog.getId())
//                .setSource(JSON.toJSONString(blog))
//                .get();
//
//        System.out.println(client.prepareGet("blog", "blog", "1").get());
//
//        blog.setDisplay("hello世界");
//        blog.setMd("md");
//        System.out.println(client.prepareUpdate("blog", "blog", "2").setDoc(JSON.toJSONString(blog))
//                .get());
//
//
//        int page = 1;
//        int size = 2;
//        System.out.println(client.prepareSearch("blog").setTypes("blog").setFrom((page - 1) * size).setSize(size).setQuery(QueryBuilders.matchAllQuery()).addSort("publishTime", SortOrder.DESC).get());
//
//        System.out.println(client.prepareSearch("blog").setTypes("blog").setFrom((page - 1) * size).setSize(size).setQuery(QueryBuilders.matchQuery("catalog", "java")).addSort("publishTime", SortOrder.DESC).get());
//
//        client.close();
//    }

       System.out.println("<span class='kw'>迷失</span>".replaceAll("<span class='kw'>","").replaceAll("</span>",""));

//      new RestTemplate().postForEntity("http://localhost:8686/weixin","<xml><ToUserName><![CDATA[gh_94c58645c7e8]]></ToUserName>\n" +
//              "<FromUserName><![CDATA[opCwft3GcE-4V0DYchdA9Q7Fc0nQ]]></FromUserName>\n" +
//              "<CreateTime>1484466041</CreateTime>\n" +
//              "<MsgType><![CDATA[text]]></MsgType>\n" +
//              "<Content><![CDATA[spring ]]></Content>\n" +
//              "<MsgId>6375733098526275644</MsgId>\n" +
//              "</xml>\n",String.class);
    }

}
