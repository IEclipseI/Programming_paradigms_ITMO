package search;

public class BinarySearchMissing {
    //PRE: foreach (s: args) s can be parsed to int && args.length >= 2 && x <= a[a.length - 1] && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j]
    //POST:RES = i: foreach j: 0 <= j < i : a[j] > x && a[i]<= x  && foreach k  = 0 .. a.length - 1 : a[k] == a'[k]
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        //int position = iterativeBinSearch(a, x);
        int position = recursiveBinSearch(a, x);
        if (position >= a.length || a[position] != x) {
            position = -position - 1;
        }
        System.out.println(position);
    }

    //PRE: a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
    //POST: RES = i: foreach j: 0 <j < i : a[j] > x && a[i] <= x && foreach k  = 0 .. a.length - 1 : a[k] == a'[k]
    private static int iterativeBinSearch(int[] a, int x) {
        int l = -1;
        int r = a.length;
        // PRE: a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
        //             && l==-1 && r == a.length
        // INV:a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
            //            && a[l] > x && a[r] <=[x] && l < r
        while (l < r - 1) {
            // a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
            //            && a[l] > x && a[r] <=[x] && l < r - 1
            int m = (l + r) / 2;
            // a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
            //            && a[l] > x && a[r] <=[x] && l < r -1 && m = (l + r)/2

            if (a[m] > x) {
                l = m;
            } else {
                r = m;
            }
            // a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
            //            && a[l] > x && a[r] <=[x] && l < r && (r - l) * 2 <= r' - l'
        }
        // a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
        //            && a[l] > x && a[r] <=[x] && l >= r -1
        return r;
    }

    //PRE: a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
    //POST: RES = i: foreach j < i in [0..a.length): a[j] > x && a[i] <= x && foreach k  = 0 .. a.length - 1 : a[k] == a'[k]
    private static int recursiveBinSearch(int[] a, int x) {
        return recursiveBinSearch(a, x, -1, a.length);
    }


    //PRE: a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1] && l, r in [-1, a.length]
    //     && l < r
    //POST: RES = i: foreach i,j: l' < j < i < r'  : a[j] > x && a[i] <= x && foreach k  = 0 .. a.length - 1 : a[k] == a'[k]
    private static int recursiveBinSearch(int[] a, int x, int l, int r) {
        //  a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1] && l, r in [-1, a.length]
        //     && l < r
        if (l >= r - 1) {
            // a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
            //            && a[l] > x && a[r] <=[x] && l >= r -1
            return r;
        } else {
            int m = (l + r) / 2;
            // a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
            //            && a[l] > x && a[r] <=[x] && l < r -1 && m = (l + r)/2
            if (a[m] > x) {
                // a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
                //            && a[l] > x && a[r] <=[x] && l < r && (r - l) * 2 <= r' - l'
                return recursiveBinSearch(a, x, m, r);
            } else {
                // a!=null && a.length > 0 && foreach i, j = 0..a.length - 1: i <= j ==> ar[i]>= ar[j] && x <= a[a.length - 1]
                //            && a[l] > x && a[r] <=[x] && l < r && (r - l) * 2 <= r' - l'
                return recursiveBinSearch(a, x, l, m);
            }
        }
    }
}