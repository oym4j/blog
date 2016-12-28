import org.bumishi.techblog.api.domain.model.BookIndex;
import org.bumishi.techblog.api.domain.model.GitBookCurrentIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiang.xie
 * @date 2016/12/28
 */
public class Test {

    public static void main(String[] arg) {
        List<BookIndex> nodes = new ArrayList<>();

        BookIndex apple2 = new BookIndex();
        apple2.setUrl("1.2");
        apple2.setId("1.2");
        apple2.setLabel("1章第2节");
        apple2.setPath("0,1");
        nodes.add(apple2);


        BookIndex lizi = new BookIndex();
        lizi.setId("2");
        lizi.setLabel("第二章");
        lizi.setPath("0");

        lizi.setUrl("2");
        nodes.add(lizi);

        BookIndex apple3 = new BookIndex();
        apple3.setUrl("2.1");
        apple3.setId("2.1");
        apple3.setLabel("2章第1节");
        apple3.setPath("0,2");
        nodes.add(apple3);

        BookIndex fruit = new BookIndex();
        fruit.setId("1");
        fruit.setLabel("第一章");
        fruit.setOrder(1);
        fruit.setUrl("1");
        fruit.setPath("0");
        nodes.add(fruit);

        BookIndex apple = new BookIndex();
        apple.setUrl("1.1");
        apple.setId("1.1");
        apple.setPath("0,1");
        apple.setLabel("1章第一节");
        nodes.add(apple);


        BookIndex apple4 = new BookIndex();
        apple4.setUrl("2.2");
        apple4.setId("2.2");
        apple4.setLabel("2章第2节");
        apple4.setPath("0,2");
        nodes.add(apple4);

        String url = "1.1";
        GitBookCurrentIndex gitBookIndex = new GitBookCurrentIndex(nodes);
        gitBookIndex.build(url);
        System.out.println(gitBookIndex.getCurrent() + ";" + gitBookIndex.getPrev() + ";" + gitBookIndex.getNext());
        System.out.println(gitBookIndex.getBookJson());
    }

}
