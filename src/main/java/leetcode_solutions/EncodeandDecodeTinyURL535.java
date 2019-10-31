package leetcode_solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class EncodeandDecodeTinyURL535 {
    public class Codec {

        int count = 0;
        Map<Integer, String> map = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            map.put(count, longUrl);
            return "http://" + String.valueOf(count++);
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            int key = Integer.valueOf(shortUrl.substring(7, shortUrl.length()));
            return map.get(key);
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

    public class Codec1 {

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            return longUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return shortUrl;
        }
    }

    public class Codec8 {

        static final String INDEX = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        static final String TINY_PREFIX = "http://tinyurl.com/";
        private Map<String, String> map = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            char[] chars = new char[6];
            while (true) {
                for (int i = 0; i < 6; i++) {
                    chars[i] = INDEX.charAt((int) (Math.random() * 62));
                }
                String shortUrl = TINY_PREFIX + new String(chars);
                if (!map.containsKey(shortUrl)) {
                    map.put(shortUrl, longUrl);
                    return shortUrl;
                }
            }

        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return map.get(shortUrl);
        }
    }

    @Test
    public void test() {
        Codec codec = new Codec();
        String url = "https://leetcode.com/problems/design-tinyurl";
        String s = codec.encode(url);
        s = codec.decode(s);
        System.out.println(s);


//        String s = "https://leetcode.com/problems/design-tinyurl";

//        String[] ss = s.split("//");
//        System.out.println(Arrays.toString(ss));
    }
}
