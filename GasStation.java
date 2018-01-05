/*

https://leetcode.com/problems/gas-station/description/

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to
its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note: the solution is guaranteed to be unique.

Brute force algorithm:
- start at gas station 0,
- try to take all the gas at every station as we move,
- see if we can make it around the circle back to station 0,
- if that doesn't work, start over at station 1 and try again,
- as soon as we find a starting station that works, return it,
- if we get through trying them all, and there are none that work, return -1.

*/
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int numStations = gas.length;
        for (int startingStation = 0; startingStation < numStations; startingStation++) {
            int gasInTank = 0;
            for (int stationsTravelled = 0; stationsTravelled < numStations; stationsTravelled++) {
                int whichStation = (startingStation + stationsTravelled) % numStations;
                gasInTank += gas[whichStation];
                gasInTank -= cost[whichStation];
                if (gasInTank < 0) {
                    // ran out of gas, this startingStation won't work, give up
                    break;
                }
            }
            if (gasInTank >= 0) {
                // if we weren't out of gas, we made it through all stations, so found a round trip that works
                return startingStation;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int N = 5;
        int[] cost = { 3, 3, 3, 3, 3 };

        int[] gas1 = { 1, 2, 8, 2, 1 };
        int[] gas2 = { 1, 2, 9, 2, 1 };
        int[] gas3 = { 3, 3, 3, 3, 2 };
        int[] gas4 = { 2, 3, 3, 3, 4 };

        GasStation gs = new GasStation();
        System.out.println("starting gas station is " + gs.canCompleteCircuit(gas1, cost) + " (should be -1)");
        System.out.println("starting gas station is " + gs.canCompleteCircuit(gas2, cost) + " (should be 2)");
        System.out.println("starting gas station is " + gs.canCompleteCircuit(gas3, cost) + " (should be -1)");
        System.out.println("starting gas station is " + gs.canCompleteCircuit(gas4, cost) + " (should be 1)");
    }
}