package com.sjh.a18_openapiconnect;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* naver book api의 응답내용
<?xml version="1.0" encoding="UTF-8"?>
<rss version="2.0">
    <channel>
        <title>Naver Open API - book ::'주식'</title>
        <link>http://search.naver.com</link>
        <description>Naver Search Result</description>
        <lastBuildDate>Mon, 26 Sep 2016 10:40:35 +0900</lastBuildDate>
        <total>20177</total><start>1</start><display>10</display>
        <item>
            <title>불곰의 <b>주식</b>투자 불패공식 (60개 매도종목 평균 수익률 62%)</title>
            <link>http://openapi.naver.com/l?AAAC3LSwqDMBSF4dXcDCV6YxsHGfiog6JIV1A0SYloGpumQnffFIQz+DnwvT7afwVcaigRqvofvIKiIcbrhzAhbIAlZG3c5NySPMdd+0Q6exxqOuKudBjnNdlMFO00K8AmpRzZKackiJSdGc8ZxZzSglix3vqhG6KVKPcis0vXX+k+vqXM2BLpDzHjEYWYAAAA</link>
            <image>http://bookthumb.phinf.naver.net/cover/108/346/10834650.jpg?type=m1&udate=20160902</image>
            <author>불곰 박선목</author>
            <price>16000</price>
            <discount>14400</discount>
            <publisher>부키</publisher>
            <pubdate>20160729</pubdate>
            <isbn>8960515523 9788960515529</isbn>
            <description>잘못된 <b>주식</b>투자 습관을 버리고, 절대로 지지 않는 투자법을 체득하다!불곰<b>주식</b>연구소 대표 ‘불곰’이 알려 주는 세상에서 가장 쉬운 ‘<b>주식</b>투자 불패공식’ 『불곰의 <b>주식</b>투자 불패공식』. 불곰은 전업투자자가 아니다. 불곰<b>주식</b>연구소는 태평스럽게도 한 달에 한 종목 정도만 추천할 따름이다. 그럼에도... </description></item><item><title>엄마, <b>주식</b> 사주세요 (아이와 엄마의 미래를 위한 투자 원칙)</title><link>http://openapi.naver.com/l?AAACssTS2qtFV1dVZ1NFZ1cgYxLJxULV3UMopS02wzSkoKVI0dVY3cgCgpPz9bLy+xLLVILzk/FyqQkgRlxKekliRm5ugVZAB1uCVlpqgauxgamJsZmZlZqpXYGpqYm1iYmhgYmxoYWKrl2oaYpnokpmR6mhX6G1mkhHsDzXOqAGJDU8+M8rRIoGYA29JYJ5oAAAA=</link><image>http://bookthumb.phinf.naver.net/cover/107/626/10762669.jpg?type=m1&udate=20160802</image><author>존 리</author><price>14000</price><discount>12600</discount><publisher>한국경제신문사</publisher><pubdate>20160627</pubdate><isbn>8947541184 9788947541183</isbn><description>엄마의 <b>주식</b> 투자가 아이의 미래다!『엄마, <b>주식</b> 사주세요』는 전설의 펀드 투자자, 코리아펀드의 귀재로 불리며 새로운 마켓 리더로 부상한 존 리가... 저자는 이 책을 통해 자녀를 월급쟁이가 아닌 자본가로 키울 것과, <b>주식</b>투자에 대한 엄마들의 편견에 대해 중점적으로 이야기한다. 부를 축적하기 위한 자본가... </description>
        </item>
        ...
    </channel>
</rss>
 */
public class Parser {
    /* 웹에서 요소를 검색하여 vo에 저장하기 위한 클래스*/
    BookVO vo;
    String myQuery = "";//검색어

    //파싱 작업을 위한 메서드
    public ArrayList<BookVO> connectNaver( int start, ArrayList<BookVO> list ){

        try {
            myQuery = URLEncoder.encode( MainActivity.search.getText().toString(), "UTF8" );
            int count = PageCount.COUNT;//검색결과 n건씩 표시

            //요청 URL
            String urlStr = "https://openapi.naver.com/v1/search/book.xml?query=" + myQuery + "&start="+start+"&display="+count;

            URL url = new URL(urlStr);
            //준비된 url경로로 연결
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            //발급받은 ID와 Secret추가
            connection.setRequestProperty( "X-Naver-Client-Id", "ID 입력" );
            connection.setRequestProperty( "X-Naver-Client-Secret", "Secret 입력" );

            //위의 URL에 접근하는데 문제가 없었다면 받은 자원을 파싱할 준비
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput( connection.getInputStream(), null );

            int parserEvent = parser.getEventType();

            //파서를 통해 접근한 xml로 부터 각각의 요소들을 가져온다
            //xml로 접근하여 xml문서의 끝을 만날때까지 while문을 반복
            while( parserEvent != XmlPullParser.END_DOCUMENT ){//END_DOCUMENT : xml 문서의 끝
                if( parserEvent == XmlPullParser.START_TAG){
                    //시작태그의 이름을 알아낸다.
                    String tagName = parser.getName();  //시작태그
                    if( tagName.equalsIgnoreCase("title")){
                        vo = new BookVO();
                        String title = parser.nextText();   //해당 태그의 내용을 가져옴

                        //<b>등의 태그를 지움
                        Pattern pattern = Pattern.compile("<.*?>"); //패턴
                        Matcher matcher = pattern.matcher(title);
                        if(matcher.find()){ //태그가 존재할 시
                            String s_title = matcher.replaceAll("");
                            vo.setB_title(s_title);
                        }
                        else{
                            vo.setB_title(title);
                        }

                        //vo.setB_title(title);
                    }
                    else if(tagName.equalsIgnoreCase("image")){
                        String img = parser.nextText();
                        vo.setB_img(img);
                    }
                    else if(tagName.equalsIgnoreCase("author")){
                        String author = parser.nextText();
                        vo.setB_author(author);
                    }
                    else if(tagName.equalsIgnoreCase("price")){
                        String price = parser.nextText();
                        vo.setB_price(price);
                        list.add(vo);   //ArrayList에 vo를 저장
                    }
                }//시작태그의 이름 알아내기
                parserEvent = parser.next();//다음요소
            }//while

        }catch (Exception e){

        }
        return list;    //검색을 완료한 데이터의 개수만큼 size()를 가지고 있는 ArrayList 반환
    }//connectNaver()
}






































































