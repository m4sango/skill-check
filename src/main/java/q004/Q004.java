package q004;

/**
 * Q004 ソートアルゴリズム
 * <p>
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 * <p>
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 * <p>
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {

    public static void main(String[] args) {
        ListManager data = new ListManager();

        int end = data.size() - 1;
        while (end > 0) {

            int target = 0;
            for (int next = 1; next <= end; next++) {

                if (data.compare(target, next) == -1) {
                    target = next;
                }
                if (next == end) {
                    data.exchange(target, end);
                    break;
                }
            }

            end--;
        }

        data.checkResult();
    }
}
// 完成までの時間: xx時間 xx分