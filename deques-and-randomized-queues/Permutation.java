public class Permutation {
  public static void main(String[] args) {
    int k = Integer.parseInt(args[1]);
    RandomizedQueue<String> rq = new RandomizedQueue<>();
    for (String str : args[2].split(" ")) {
      rq.enqueue(str);
    }

    for (int i = 0; i < k; i++) {
      System.out.println(rq.dequeue());
    }
  }
}