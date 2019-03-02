import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class hm {
    public static void main(String[] args) {
        int p = 0;
        int v = 0;
        long it = 0;
        for (int i = 0; i < 100000000; i++) {
            Random random = new Random();
            List<Integer> list = new ArrayList<>();
            list.add(random.nextInt(2));
            list.add(random.nextInt(2));
            while (true) {
                it++;
                list.add(random.nextInt(3)% 2);
                if (list.get(list.size() - 1) == 1 && list.get(list.size() - 2) == 0 && list.get(list.size() - 3) == 0) {
                    p++;
                    break;
                }
                if (list.get(list.size() - 1) == 0 && list.get(list.size() - 2) == 1 && list.get(list.size() - 3) == 0) {
                    v++;
                    break;
                }
            }
        }
        System.out.println((double) p / ((double) p + (double) v) + "    " + (double) it / (double) 100000000);
    }
}
