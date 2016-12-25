/*
MySQL Data Transfer
Source Host: localhost
Source Database: bumishi_blog
Target Host: localhost
Target Database: bumishi_blog
Date: 2016/12/25 22:11:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for blog
-- ----------------------------
CREATE TABLE `blog` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `title` varchar(50) NOT NULL,
  `secondTitle` varchar(30) DEFAULT NULL,
  `catalog` varchar(20) NOT NULL,
  `md` text NOT NULL,
  `display` text,
  `auther` varchar(30) DEFAULT NULL,
  `publishTime` date DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for book
-- ----------------------------
CREATE TABLE `book` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(30) NOT NULL,
  `catalog` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `publishTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for book_index
-- ----------------------------
CREATE TABLE `book_index` (
  `id` varchar(50) NOT NULL,
  `label` varchar(20) NOT NULL,
  `path` varchar(200) DEFAULT '0',
  `order` smallint(6) DEFAULT '1',
  `level` smallint(6) DEFAULT '1' COMMENT '层级，方便根据层级查询',
  `url` varchar(200) DEFAULT NULL,
  `type` smallint(6) DEFAULT '1' COMMENT '扩展不同菜单时用',
  `style` varchar(50) DEFAULT NULL COMMENT 'ui 样式',
  `disabled` smallint(6) DEFAULT '0',
  `bookId` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for catalog
-- ----------------------------
CREATE TABLE `catalog` (
  `id` varchar(50) NOT NULL,
  `label` varchar(20) NOT NULL,
  `path` varchar(200) DEFAULT '0',
  `order` smallint(6) DEFAULT '1',
  `level` smallint(6) DEFAULT '1' COMMENT '层级，方便根据层级查询',
  `url` varchar(200) DEFAULT NULL,
  `type` smallint(6) DEFAULT '1' COMMENT '扩展不同业务时用',
  `style` varchar(50) DEFAULT NULL COMMENT 'ui 样式',
  `disabled` smallint(6) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for nav
-- ----------------------------
CREATE TABLE `nav` (
  `id` varchar(50) NOT NULL,
  `label` varchar(20) NOT NULL,
  `path` varchar(200) DEFAULT '0',
  `order` smallint(6) DEFAULT '1',
  `level` smallint(6) DEFAULT '1' COMMENT '层级，方便根据层级查询',
  `url` varchar(200) DEFAULT NULL,
  `type` smallint(6) DEFAULT '1' COMMENT '扩展不同菜单时用',
  `style` varchar(50) DEFAULT NULL COMMENT 'ui 样式',
  `disabled` smallint(6) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for site_config
-- ----------------------------
CREATE TABLE `site_config` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(30) DEFAULT NULL,
  `keywords` varchar(200) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `theme` varchar(30) DEFAULT NULL,
  `pageSize` int(11) DEFAULT NULL,
  `footer` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `blog` VALUES ('1df87905-88f0-46af-9d9f-e92ef14b9007', 'fagagagagag', 'afafa', 'java', 'ggaagag', '<p>ggaagag</p>\r\n', 'bumishi', '2016-12-25', null);
INSERT INTO `blog` VALUES ('e86e2321-20da-4a53-87a6-24c50fc76eea', '个性化宝贝分类', 'gagagagaga', 'java', 'gagagaa\r\n![](http://ohtnk29z2.bkt.clouddn.com/148145167885749301.jpg)\r\n\r\n```\r\npackage org.bumishi.admin.interfaces.web;\r\n\r\nimport com.qiniu.common.QiniuException;\r\nimport com.qiniu.http.Response;\r\nimport org.bumishi.admin.config.ApplicationConfig;\r\nimport org.bumishi.toolbox.qiniu.QiNiuApi;\r\nimport org.springframework.beans.factory.annotation.Autowired;\r\nimport org.springframework.web.bind.annotation.PostMapping;\r\nimport org.springframework.web.bind.annotation.RequestMapping;\r\nimport org.springframework.web.bind.annotation.RequestParam;\r\nimport org.springframework.web.bind.annotation.RestController;\r\nimport org.springframework.web.multipart.MultipartFile;\r\n\r\nimport java.io.IOException;\r\nimport java.util.Random;\r\n\r\n/**\r\n * @author qiang.xie\r\n * @date 2016/12/9\r\n */\r\n@RequestMapping(\"/upload\")\r\n@RestController\r\npublic class UploadController {\r\n\r\n    @Autowired\r\n    protected QiNiuApi qiNiuApi;\r\n\r\n    @Autowired\r\n    protected ApplicationConfig applicationConfig;\r\n\r\n    @PostMapping(\"/image\")\r\n    public UploadResponse uploadImage(@RequestParam(\"editormd-image-file\") MultipartFile file) throws IOException {\r\n        String key = System.currentTimeMillis() + \"\" + new Random().nextInt(10000) + file.getOriginalFilename();\r\n        Response response = qiNiuApi.upload(file.getBytes(), key, applicationConfig.getQiniu_bucket());\r\n        return create(response, key);\r\n    }\r\n\r\n    private UploadResponse create(Response response, String filename) {\r\n        UploadResponse uploadResponse = new UploadResponse();\r\n        if (response == null || !response.isOK()) {\r\n\r\n            uploadResponse.setSuccess(0);\r\n            try {\r\n                uploadResponse.setMessage(response.bodyString());\r\n            } catch (QiniuException e) {\r\n                e.printStackTrace();\r\n            }\r\n        } else {\r\n\r\n            uploadResponse.setSuccess(1);\r\n            uploadResponse.setUrl(applicationConfig.getQiniu_domain() + \"/\" + filename);\r\n        }\r\n        return uploadResponse;\r\n    }\r\n\r\n    public class UploadResponse {\r\n\r\n        //        {\r\n//            success : 0 | 1,           // 0 表示上传失败，1 表示上传成功\r\n//                    message : \"提示的信息，上传成功或上传失败及错误信息等。\",\r\n//                url     : \"图片地址\"        // 上传成功时才返回\r\n//        }\r\n        public int success;\r\n\r\n        public String url;\r\n\r\n        public String message;\r\n\r\n        public int getSuccess() {\r\n            return success;\r\n        }\r\n\r\n        public void setSuccess(int success) {\r\n            this.success = success;\r\n        }\r\n\r\n        public String getUrl() {\r\n            return url;\r\n        }\r\n\r\n        public void setUrl(String url) {\r\n            this.url = url;\r\n        }\r\n\r\n        public String getMessage() {\r\n            return message;\r\n        }\r\n\r\n        public void setMessage(String message) {\r\n            this.message = message;\r\n        }\r\n\r\n\r\n    }\r\n}\r\n\r\n```', '<p>gagagaa<br><img src=\"http://ohtnk29z2.bkt.clouddn.com/148145167885749301.jpg\" alt=\"\"></p>\r\n<pre class=\"prettyprint linenums prettyprinted\"><ol class=\"linenums\"><li class=\"L0\"><code><span class=\"kwd\">package</span><span class=\"pln\"> org</span><span class=\"pun\">.</span><span class=\"pln\">bumishi</span><span class=\"pun\">.</span><span class=\"pln\">admin</span><span class=\"pun\">.</span><span class=\"pln\">interfaces</span><span class=\"pun\">.</span><span class=\"pln\">web</span><span class=\"pun\">;</span></code></li><li class=\"L1\"><code></code></li><li class=\"L2\"><code><span class=\"kwd\">import</span><span class=\"pln\"> com</span><span class=\"pun\">.</span><span class=\"pln\">qiniu</span><span class=\"pun\">.</span><span class=\"pln\">common</span><span class=\"pun\">.</span><span class=\"typ\">QiniuException</span><span class=\"pun\">;</span></code></li><li class=\"L3\"><code><span class=\"kwd\">import</span><span class=\"pln\"> com</span><span class=\"pun\">.</span><span class=\"pln\">qiniu</span><span class=\"pun\">.</span><span class=\"pln\">http</span><span class=\"pun\">.</span><span class=\"typ\">Response</span><span class=\"pun\">;</span></code></li><li class=\"L4\"><code><span class=\"kwd\">import</span><span class=\"pln\"> org</span><span class=\"pun\">.</span><span class=\"pln\">bumishi</span><span class=\"pun\">.</span><span class=\"pln\">admin</span><span class=\"pun\">.</span><span class=\"pln\">config</span><span class=\"pun\">.</span><span class=\"typ\">ApplicationConfig</span><span class=\"pun\">;</span></code></li><li class=\"L5\"><code><span class=\"kwd\">import</span><span class=\"pln\"> org</span><span class=\"pun\">.</span><span class=\"pln\">bumishi</span><span class=\"pun\">.</span><span class=\"pln\">toolbox</span><span class=\"pun\">.</span><span class=\"pln\">qiniu</span><span class=\"pun\">.</span><span class=\"typ\">QiNiuApi</span><span class=\"pun\">;</span></code></li><li class=\"L6\"><code><span class=\"kwd\">import</span><span class=\"pln\"> org</span><span class=\"pun\">.</span><span class=\"pln\">springframework</span><span class=\"pun\">.</span><span class=\"pln\">beans</span><span class=\"pun\">.</span><span class=\"pln\">factory</span><span class=\"pun\">.</span><span class=\"pln\">annotation</span><span class=\"pun\">.</span><span class=\"typ\">Autowired</span><span class=\"pun\">;</span></code></li><li class=\"L7\"><code><span class=\"kwd\">import</span><span class=\"pln\"> org</span><span class=\"pun\">.</span><span class=\"pln\">springframework</span><span class=\"pun\">.</span><span class=\"pln\">web</span><span class=\"pun\">.</span><span class=\"pln\">bind</span><span class=\"pun\">.</span><span class=\"pln\">annotation</span><span class=\"pun\">.</span><span class=\"typ\">PostMapping</span><span class=\"pun\">;</span></code></li><li class=\"L8\"><code><span class=\"kwd\">import</span><span class=\"pln\"> org</span><span class=\"pun\">.</span><span class=\"pln\">springframework</span><span class=\"pun\">.</span><span class=\"pln\">web</span><span class=\"pun\">.</span><span class=\"pln\">bind</span><span class=\"pun\">.</span><span class=\"pln\">annotation</span><span class=\"pun\">.</span><span class=\"typ\">RequestMapping</span><span class=\"pun\">;</span></code></li><li class=\"L9\"><code><span class=\"kwd\">import</span><span class=\"pln\"> org</span><span class=\"pun\">.</span><span class=\"pln\">springframework</span><span class=\"pun\">.</span><span class=\"pln\">web</span><span class=\"pun\">.</span><span class=\"pln\">bind</span><span class=\"pun\">.</span><span class=\"pln\">annotation</span><span class=\"pun\">.</span><span class=\"typ\">RequestParam</span><span class=\"pun\">;</span></code></li><li class=\"L0\"><code><span class=\"kwd\">import</span><span class=\"pln\"> org</span><span class=\"pun\">.</span><span class=\"pln\">springframework</span><span class=\"pun\">.</span><span class=\"pln\">web</span><span class=\"pun\">.</span><span class=\"pln\">bind</span><span class=\"pun\">.</span><span class=\"pln\">annotation</span><span class=\"pun\">.</span><span class=\"typ\">RestController</span><span class=\"pun\">;</span></code></li><li class=\"L1\"><code><span class=\"kwd\">import</span><span class=\"pln\"> org</span><span class=\"pun\">.</span><span class=\"pln\">springframework</span><span class=\"pun\">.</span><span class=\"pln\">web</span><span class=\"pun\">.</span><span class=\"pln\">multipart</span><span class=\"pun\">.</span><span class=\"typ\">MultipartFile</span><span class=\"pun\">;</span></code></li><li class=\"L2\"><code></code></li><li class=\"L3\"><code><span class=\"kwd\">import</span><span class=\"pln\"> java</span><span class=\"pun\">.</span><span class=\"pln\">io</span><span class=\"pun\">.</span><span class=\"typ\">IOException</span><span class=\"pun\">;</span></code></li><li class=\"L4\"><code><span class=\"kwd\">import</span><span class=\"pln\"> java</span><span class=\"pun\">.</span><span class=\"pln\">util</span><span class=\"pun\">.</span><span class=\"typ\">Random</span><span class=\"pun\">;</span></code></li><li class=\"L5\"><code></code></li><li class=\"L6\"><code><span class=\"com\">/**</span></code></li><li class=\"L7\"><code><span class=\"com\"> * @author qiang.xie</span></code></li><li class=\"L8\"><code><span class=\"com\"> * @date 2016/12/9</span></code></li><li class=\"L9\"><code><span class=\"com\"> */</span></code></li><li class=\"L0\"><code><span class=\"lit\">@RequestMapping</span><span class=\"pun\">(</span><span class=\"str\">\"/upload\"</span><span class=\"pun\">)</span></code></li><li class=\"L1\"><code><span class=\"lit\">@RestController</span></code></li><li class=\"L2\"><code><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">class</span><span class=\"pln\"> </span><span class=\"typ\">UploadController</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L3\"><code></code></li><li class=\"L4\"><code><span class=\"pln\">    </span><span class=\"lit\">@Autowired</span></code></li><li class=\"L5\"><code><span class=\"pln\">    </span><span class=\"kwd\">protected</span><span class=\"pln\"> </span><span class=\"typ\">QiNiuApi</span><span class=\"pln\"> qiNiuApi</span><span class=\"pun\">;</span></code></li><li class=\"L6\"><code></code></li><li class=\"L7\"><code><span class=\"pln\">    </span><span class=\"lit\">@Autowired</span></code></li><li class=\"L8\"><code><span class=\"pln\">    </span><span class=\"kwd\">protected</span><span class=\"pln\"> </span><span class=\"typ\">ApplicationConfig</span><span class=\"pln\"> applicationConfig</span><span class=\"pun\">;</span></code></li><li class=\"L9\"><code></code></li><li class=\"L0\"><code><span class=\"pln\">    </span><span class=\"lit\">@PostMapping</span><span class=\"pun\">(</span><span class=\"str\">\"/image\"</span><span class=\"pun\">)</span></code></li><li class=\"L1\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"typ\">UploadResponse</span><span class=\"pln\"> uploadImage</span><span class=\"pun\">(</span><span class=\"lit\">@RequestParam</span><span class=\"pun\">(</span><span class=\"str\">\"editormd-image-file\"</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"typ\">MultipartFile</span><span class=\"pln\"> file</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"kwd\">throws</span><span class=\"pln\"> </span><span class=\"typ\">IOException</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"typ\">String</span><span class=\"pln\"> key </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"typ\">System</span><span class=\"pun\">.</span><span class=\"pln\">currentTimeMillis</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> </span><span class=\"str\">\"\"</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">Random</span><span class=\"pun\">().</span><span class=\"pln\">nextInt</span><span class=\"pun\">(</span><span class=\"lit\">10000</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> file</span><span class=\"pun\">.</span><span class=\"pln\">getOriginalFilename</span><span class=\"pun\">();</span></code></li><li class=\"L3\"><code><span class=\"pln\">        </span><span class=\"typ\">Response</span><span class=\"pln\"> response </span><span class=\"pun\">=</span><span class=\"pln\"> qiNiuApi</span><span class=\"pun\">.</span><span class=\"pln\">upload</span><span class=\"pun\">(</span><span class=\"pln\">file</span><span class=\"pun\">.</span><span class=\"pln\">getBytes</span><span class=\"pun\">(),</span><span class=\"pln\"> key</span><span class=\"pun\">,</span><span class=\"pln\"> applicationConfig</span><span class=\"pun\">.</span><span class=\"pln\">getQiniu_bucket</span><span class=\"pun\">());</span></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"kwd\">return</span><span class=\"pln\"> create</span><span class=\"pun\">(</span><span class=\"pln\">response</span><span class=\"pun\">,</span><span class=\"pln\"> key</span><span class=\"pun\">);</span></code></li><li class=\"L5\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L6\"><code></code></li><li class=\"L7\"><code><span class=\"pln\">    </span><span class=\"kwd\">private</span><span class=\"pln\"> </span><span class=\"typ\">UploadResponse</span><span class=\"pln\"> create</span><span class=\"pun\">(</span><span class=\"typ\">Response</span><span class=\"pln\"> response</span><span class=\"pun\">,</span><span class=\"pln\"> </span><span class=\"typ\">String</span><span class=\"pln\"> filename</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L8\"><code><span class=\"pln\">        </span><span class=\"typ\">UploadResponse</span><span class=\"pln\"> uploadResponse </span><span class=\"pun\">=</span><span class=\"pln\"> </span><span class=\"kwd\">new</span><span class=\"pln\"> </span><span class=\"typ\">UploadResponse</span><span class=\"pun\">();</span></code></li><li class=\"L9\"><code><span class=\"pln\">        </span><span class=\"kwd\">if</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"pln\">response </span><span class=\"pun\">==</span><span class=\"pln\"> </span><span class=\"kwd\">null</span><span class=\"pln\"> </span><span class=\"pun\">||</span><span class=\"pln\"> </span><span class=\"pun\">!</span><span class=\"pln\">response</span><span class=\"pun\">.</span><span class=\"pln\">isOK</span><span class=\"pun\">())</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L0\"><code></code></li><li class=\"L1\"><code><span class=\"pln\">            uploadResponse</span><span class=\"pun\">.</span><span class=\"pln\">setSuccess</span><span class=\"pun\">(</span><span class=\"lit\">0</span><span class=\"pun\">);</span></code></li><li class=\"L2\"><code><span class=\"pln\">            </span><span class=\"kwd\">try</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L3\"><code><span class=\"pln\">                uploadResponse</span><span class=\"pun\">.</span><span class=\"pln\">setMessage</span><span class=\"pun\">(</span><span class=\"pln\">response</span><span class=\"pun\">.</span><span class=\"pln\">bodyString</span><span class=\"pun\">());</span></code></li><li class=\"L4\"><code><span class=\"pln\">            </span><span class=\"pun\">}</span><span class=\"pln\"> </span><span class=\"kwd\">catch</span><span class=\"pln\"> </span><span class=\"pun\">(</span><span class=\"typ\">QiniuException</span><span class=\"pln\"> e</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L5\"><code><span class=\"pln\">                e</span><span class=\"pun\">.</span><span class=\"pln\">printStackTrace</span><span class=\"pun\">();</span></code></li><li class=\"L6\"><code><span class=\"pln\">            </span><span class=\"pun\">}</span></code></li><li class=\"L7\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span><span class=\"pln\"> </span><span class=\"kwd\">else</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L8\"><code></code></li><li class=\"L9\"><code><span class=\"pln\">            uploadResponse</span><span class=\"pun\">.</span><span class=\"pln\">setSuccess</span><span class=\"pun\">(</span><span class=\"lit\">1</span><span class=\"pun\">);</span></code></li><li class=\"L0\"><code><span class=\"pln\">            uploadResponse</span><span class=\"pun\">.</span><span class=\"pln\">setUrl</span><span class=\"pun\">(</span><span class=\"pln\">applicationConfig</span><span class=\"pun\">.</span><span class=\"pln\">getQiniu_domain</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> </span><span class=\"str\">\"/\"</span><span class=\"pln\"> </span><span class=\"pun\">+</span><span class=\"pln\"> filename</span><span class=\"pun\">);</span></code></li><li class=\"L1\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"kwd\">return</span><span class=\"pln\"> uploadResponse</span><span class=\"pun\">;</span></code></li><li class=\"L3\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L4\"><code></code></li><li class=\"L5\"><code><span class=\"pln\">    </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">class</span><span class=\"pln\"> </span><span class=\"typ\">UploadResponse</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L6\"><code></code></li><li class=\"L7\"><code><span class=\"pln\">        </span><span class=\"com\">//        {</span></code></li><li class=\"L8\"><code><span class=\"com\">//            success : 0 | 1,           // 0 表示上传失败，1 表示上传成功</span></code></li><li class=\"L9\"><code><span class=\"com\">//                    message : \"提示的信息，上传成功或上传失败及错误信息等。\",</span></code></li><li class=\"L0\"><code><span class=\"com\">//                url     : \"图片地址\"        // 上传成功时才返回</span></code></li><li class=\"L1\"><code><span class=\"com\">//        }</span></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">int</span><span class=\"pln\"> success</span><span class=\"pun\">;</span></code></li><li class=\"L3\"><code></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"typ\">String</span><span class=\"pln\"> url</span><span class=\"pun\">;</span></code></li><li class=\"L5\"><code></code></li><li class=\"L6\"><code><span class=\"pln\">        </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"typ\">String</span><span class=\"pln\"> message</span><span class=\"pun\">;</span></code></li><li class=\"L7\"><code></code></li><li class=\"L8\"><code><span class=\"pln\">        </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">int</span><span class=\"pln\"> getSuccess</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L9\"><code><span class=\"pln\">            </span><span class=\"kwd\">return</span><span class=\"pln\"> success</span><span class=\"pun\">;</span></code></li><li class=\"L0\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L1\"><code></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> setSuccess</span><span class=\"pun\">(</span><span class=\"kwd\">int</span><span class=\"pln\"> success</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L3\"><code><span class=\"pln\">            </span><span class=\"kwd\">this</span><span class=\"pun\">.</span><span class=\"pln\">success </span><span class=\"pun\">=</span><span class=\"pln\"> success</span><span class=\"pun\">;</span></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L5\"><code></code></li><li class=\"L6\"><code><span class=\"pln\">        </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"typ\">String</span><span class=\"pln\"> getUrl</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L7\"><code><span class=\"pln\">            </span><span class=\"kwd\">return</span><span class=\"pln\"> url</span><span class=\"pun\">;</span></code></li><li class=\"L8\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L9\"><code></code></li><li class=\"L0\"><code><span class=\"pln\">        </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> setUrl</span><span class=\"pun\">(</span><span class=\"typ\">String</span><span class=\"pln\"> url</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L1\"><code><span class=\"pln\">            </span><span class=\"kwd\">this</span><span class=\"pun\">.</span><span class=\"pln\">url </span><span class=\"pun\">=</span><span class=\"pln\"> url</span><span class=\"pun\">;</span></code></li><li class=\"L2\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L3\"><code></code></li><li class=\"L4\"><code><span class=\"pln\">        </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"typ\">String</span><span class=\"pln\"> getMessage</span><span class=\"pun\">()</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L5\"><code><span class=\"pln\">            </span><span class=\"kwd\">return</span><span class=\"pln\"> message</span><span class=\"pun\">;</span></code></li><li class=\"L6\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L7\"><code></code></li><li class=\"L8\"><code><span class=\"pln\">        </span><span class=\"kwd\">public</span><span class=\"pln\"> </span><span class=\"kwd\">void</span><span class=\"pln\"> setMessage</span><span class=\"pun\">(</span><span class=\"typ\">String</span><span class=\"pln\"> message</span><span class=\"pun\">)</span><span class=\"pln\"> </span><span class=\"pun\">{</span></code></li><li class=\"L9\"><code><span class=\"pln\">            </span><span class=\"kwd\">this</span><span class=\"pun\">.</span><span class=\"pln\">message </span><span class=\"pun\">=</span><span class=\"pln\"> message</span><span class=\"pun\">;</span></code></li><li class=\"L0\"><code><span class=\"pln\">        </span><span class=\"pun\">}</span></code></li><li class=\"L1\"><code></code></li><li class=\"L2\"><code></code></li><li class=\"L3\"><code><span class=\"pln\">    </span><span class=\"pun\">}</span></code></li><li class=\"L4\"><code><span class=\"pun\">}</span></code></li></ol></pre>', 'bumishi', '2016-12-25', null);
INSERT INTO `catalog` VALUES ('faf', 'fafaf', '0,java', '10', '2', 'fa', '1', null, '1');
INSERT INTO `catalog` VALUES ('java', 'Java', '0', '10', '1', '/catalog/java', '0', null, '0');
INSERT INTO `nav` VALUES ('fafa', 'fa', '0', '10', '1', 'fa', '0', null, '0');
