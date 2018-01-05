/*
https://leetcode.com/problems/gas-station/description/

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to
its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note: the solution is guaranteed to be unique.
*/
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // TODO: fill in solution
        return 0;
    }

    public static void main(String[] args) {
        int N = 5;
        int[] gas = { 4, 3, 7, 1, 3 };
        int[] cost = { 1, 1, 1, 1, 1 };
        GasStation gs = new GasStation();
        System.out.println("starting gas statis is " + gs.canCompleteCircuit(gas, cost));
    }
}