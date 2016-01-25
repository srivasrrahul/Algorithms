package Crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rahul on 11/12/15.
 */
public class Crawler {
    private String rootWebPage;
    private Pattern pattern = Pattern.compile("http://(\\w+\\.)*(\\w+)");

    public Crawler(String rootWebPage) {
        this.rootWebPage = rootWebPage;
    }

    String getPageContents(URL url) throws IOException {
        BufferedReader inputPageStream = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = inputPageStream.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    void crawl() throws IOException {
        LinkedList<String> linkedList = new LinkedList<>();
        HashSet<String> discoveredWebPages  = new HashSet<>();

        linkedList.add(rootWebPage);
        discoveredWebPages.add(rootWebPage);
        while (!linkedList.isEmpty()) {
            String url = linkedList.removeFirst();
            System.out.println(url);
            URL urlToRead = new URL(url);


            String pageContent = getPageContents(urlToRead);

            Matcher matcher = pattern.matcher(pageContent);

            while (matcher.find()) {
                String pageLink = matcher.group();
                if (discoveredWebPages.contains(pageLink) == false) {
                    discoveredWebPages.add(pageLink);
                    linkedList.add(pageLink);
                }


            }




        }
    }



    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler("http://www.jakarta.com");
        crawler.crawl();
    }
}
