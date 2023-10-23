public class Main {
  public static int Josephus(int N, int k) {
    int i = 1, ans = 0;
    while (i <= N) {
      ans = (ans + k) % i;
      i++;
    }
    return ans + 1;
  }

  static int josephus(int N, int K) {
    if (N == 1)
      return 1;
    else
      return (josephus(N - 1, K) + K - 1);
  }

  public static void main(String[] args) {
    int N = 11, k = 9;
    String[] names = { "Hasan", "Nima", "Reza", "Amir", "Zahra", "Deniz", "Ali", "Sana", "Mahyar", "Mammad", "Hooman" };
    int ans1 = Josephus(N, k);
    System.out.println("Lucky " + names[ans1] + " :)");
    int ans2 = Josephus(N, k);
    System.out.println("Lucky " + names[ans2] + " :)");
  }
}
