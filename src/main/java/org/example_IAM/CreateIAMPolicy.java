public class CreateIAMPolicy {
    static void doIt(int x, int y, int m) {
        if (x == 5) {
            m=y;
        } else {
            m=x;
        }
    }

    public static void main(String[] args) {
        int i=6, j=4, k=9;
        CreateIAMPolicy.doIt(i, j, k);
        System.out.print(k);
    }
}
