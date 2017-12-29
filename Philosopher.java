import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * This code addresses the classic dining philosophers problem, a bunch of philosophers are sitting around a
 * circular table, there is a chopstick between each of them. A philosopher needs both chopsticks to eat.
 *
 * In this initial, simple solution, the philosophers always pick up the left chopstick before the right one,
 * and this result in a deadlock (as you will see if you run this program). I will iterate on this problem
 * using git commits, until there are no deadlocks.
 */

class Chopstick {
    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        lock = new ReentrantLock();
        System.out.println("created chopstick " + id);
    }

    public int getId() {
        return id;
    }

    public void pickUp() {
        lock.lock();
    }

    public void putDown() {
        lock.unlock();
    }
}

public class Philosopher extends Thread {
    private int bites = 10;
    private Chopstick left, right;
    private int id;

    public Philosopher(int id, Chopstick left, Chopstick right) {
        this.id = id;
        this.left = left;
        this.right = right;
        System.out.println("philosopher " + id + " is next to chopstick " + left.getId() + " and chopstick " + right.getId());
    }

    private void pickUpSticks() {
        left.pickUp();
        right.pickUp();
    }

    private void chew() {
        System.out.println("philosopher " + id + " is chewing");
    }

    private void putDownSticks() {
        right.putDown();
        left.putDown();
    }

    private void eat() {
        pickUpSticks();
        chew();
        putDownSticks();
    }

    public void run() {
        for (int i = 0; i < bites; i++) {
            eat();
        }
    }

    public static void main(String[] args) {
        int numSticks = 10;
        int numPhilosophers = 10;
        List<Chopstick> chopsticks = new ArrayList<Chopstick>(numSticks);
        List<Philosopher> philosophers = new ArrayList<Philosopher>(numPhilosophers);

        for (int i = 0; i < numSticks; i++) {
            chopsticks.add(new Chopstick(i));
        }

        for (int i = 0; i < numPhilosophers; i++) {
            int left = i % numSticks;
            int right = (i + 1) % numSticks;
            philosophers.add(new Philosopher(i, chopsticks.get(left), chopsticks.get(right)));
        }

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers.get(i).start();
        }
    }
}
