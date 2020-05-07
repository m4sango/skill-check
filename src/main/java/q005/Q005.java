package q005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Q005 データクラスと様々な集計
 * <p>
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 * <p>
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 * <p>
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 * <p>
 * [出力イメージ]
 * 部長: xx時間xx分
 * 課長: xx時間xx分
 * 一般: xx時間xx分
 * Z-7-31100: xx時間xx分
 * I-7-31100: xx時間xx分
 * T-7-30002: xx時間xx分
 * （省略）
 * 194033: xx時間xx分
 * 195052: xx時間xx分
 * 195066: xx時間xx分
 * （省略）
 */
public class Q005 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(Q005.class.getResourceAsStream("data.txt")));

        String line;
        boolean isFirst = true;
        List<WorkData> workDataList = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            if (isFirst) {
                isFirst = false;
                continue;
            }

            String[] strs = line.split(",");

            workDataList.add(new WorkData(strs[0], strs[1], strs[2], strs[3], Integer.parseInt(strs[4])));
        }

        Map<String, List<WorkData>> positionsMap = workDataList.stream().collect(Collectors.groupingBy(WorkData::getPosition));
        Map<String, List<WorkData>> codesMap = workDataList.stream().collect(Collectors.groupingBy(WorkData::getpCode));
        Map<String, List<WorkData>> numbersMap = workDataList.stream().collect(Collectors.groupingBy(WorkData::getNumber));

        // position
        outputSummaryTime(positionsMap);
        outputSummaryTime(codesMap);
        outputSummaryTime(numbersMap);
    }

    private static void outputSummaryTime(Map<String, List<WorkData>> argMap) {
        argMap.entrySet().forEach(map -> {
            String key = map.getKey();
            int sumMin = map.getValue().stream()
                    .map(WorkData::getWorkTime)
                    .mapToInt(Integer::intValue).sum();
            int hour = sumMin / 60;
            int min = sumMin % 60;

            System.out.println(key + ": " + hour + "時間" + min + "分");
        });
    }
}
// 完成までの時間: xx時間 xx分