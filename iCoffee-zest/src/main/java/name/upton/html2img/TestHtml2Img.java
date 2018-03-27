package name.upton.html2img;

import gui.ava.html.image.generator.HtmlImageGenerator;

public class TestHtml2Img {

    public static void main(String[] args) {
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        
        imageGenerator.loadUrl("http://zhibimo.com/books/xiaolai/ba-shi-jian-dang-zuo-peng-you");
        imageGenerator.saveAsImage("/Users/upton/pp.png");
        
    }

}
