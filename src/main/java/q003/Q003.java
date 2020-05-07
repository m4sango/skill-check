package q003;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Q003 集計と並べ替え
 * <p>
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 * <p>
 * 出力形式:単語=数
 * <p>
 * [出力イメージ]
 * （省略）
 * highest=1
 * I=3
 * if=2
 * ignorance=1
 * （省略）
 * <p>
 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(openDataFile()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        String data = sb.toString();
        List<String> words = Arrays.stream(data.split("(\\s|\\.[\\s]*)"))
                .map(word -> word.replace(",", ""))
                .map(word -> {
                    if (!word.equals("I")) {
                        return word.toLowerCase();
                    }

                    return word;
                })
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String s, String t1) {
                        return s.compareTo(t1);
                    }
                })
                .collect(Collectors.toList());

        Map<Object, Long> counts = words.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        List<Map.Entry<Object, Long>> sorted = counts.entrySet().stream().sorted(new Comparator<Map.Entry<Object, Long>>() {
            @Override
            public int compare(Map.Entry<Object, Long> objectLongEntry, Map.Entry<Object, Long> t1) {
                return String.valueOf(objectLongEntry.getKey()).compareTo(String.valueOf(t1));
            }
        }).collect(Collectors.toList());

        System.out.println("sorted = " + sorted);
    }
}
// 完成までの時間: xx時間 xx分