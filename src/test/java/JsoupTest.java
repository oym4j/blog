import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 * @author qiang.xie
 * @date 2017/4/1
 */
public class JsoupTest {
    public static void main(String[] arg) {
        System.out.println(getImg("<p><img src=\"http://static.bumishi.cn/2017-03-30-1f2f7374e1990b4bb551fd141bc4a53d.jpg\" alt=\"1f2f7374e1990b4bb551fd141bc4a53d\"></p>"));
    }

    private static String getImg(String html){
        Elements elements= Jsoup.parse(html).getElementsByTag("img");
        if(elements==null || elements.size()==0){
            return "http://static.bumishi.cn/bumishi_slog.png";
        }
        return elements.get(0).attr("src");
    }
}
