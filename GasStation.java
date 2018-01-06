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

While I was testing this, I noticed that a solution is only possible if you have
as much gas in the system and there are costs to go around the circle.  And even
then it typically works best if you start at the station with the most gas,
and pick up all the gas at every station on the way. You've got to think of the
cost of movement too, and take that into account.  So actually, the best station
to start at is the one where there is the biggest surplus of gas when you subtract
how much it will cost to get to the next station.

A more efficient algorithm that can be done in a single pass through the data
would be to find the maximumally best place to start and get the highest
surplus of gas left over, which will then help you make it through all subsequent
stations.

Addendum: this still doesn't work if the station you need to start at is the last
one on the list (see case12 in the tests.  So I need to go looking in the
discussion for a hint and I'll come back to this.

After looking at hint: I see it can be done in O(n) nearly the way I was doing it,
but I needed to add a double check but passing through everything twice, so that
the effectiveness of the path is verified.  Whenever the surplus is less than zero
at a station, you give up and try starting at the next station instead.
This works correctly for case12, whereas the other way did not.
Thanks goes to this discussion topic:
https://leetcode.com/problems/gas-station/discuss/42600
*/
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int currentSurplus = 0;
        int currentStartStation = 0;
        int numStations = gas.length;

        for (int i = 0; i < numStations * 2 - 1; i++) {
            int surplusHere = gas[i % numStations] - cost[i % numStations];
            currentSurplus += surplusHere;
            if (currentSurplus < 0) {
                currentStartStation = i + 1;
                if (currentStartStation >= numStations) {
                    return -1;
                }
                currentSurplus = 0;
            }
        }

        return currentStartStation;
    }

    public int canCompleteCircuitBruteForce(int[] gas, int[] cost) {
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
        int[] cost = { 3, 3, 3, 3, 3 };
        int[] gas1 = { 1, 2, 8, 2, 1 };
        int[] gas2 = { 1, 2, 9, 2, 1 };
        int[] gas3 = { 3, 3, 3, 3, 2 };
        int[] gas4 = { 2, 3, 3, 3, 4 };

        int[] gas11 = { 1, 2, 3, 4, 5 };
        int[] cost11 = { 3, 4, 5, 1, 2 };
        int[] gas12 = { 6, 1, 2, 3, 3 };
        int[] cost12 = { 2, 3, 4, 5, 1 };

        GasStation gs = new GasStation();
        System.out.println("starting gas station is " + gs.canCompleteCircuit(gas1, cost) + " (should be -1)" +
                " brute force result is " + gs.canCompleteCircuitBruteForce(gas1, cost));
        System.out.println("starting gas station is " + gs.canCompleteCircuit(gas2, cost) + " (should be 2)" +
                " brute force result is " + gs.canCompleteCircuitBruteForce(gas2, cost));
        System.out.println("starting gas station is " + gs.canCompleteCircuit(gas3, cost) + " (should be -1)" +
                " brute force result is " + gs.canCompleteCircuitBruteForce(gas3, cost));
        System.out.println("starting gas station is " + gs.canCompleteCircuit(gas4, cost) + " (should be 1)" +
                " brute force result is " + gs.canCompleteCircuitBruteForce(gas4, cost));

        System.out.println("starting gas station is " + gs.canCompleteCircuit(gas11, cost11) + " (should be 3)" +
                " brute force result is " + gs.canCompleteCircuitBruteForce(gas11, cost11));
        System.out.println("starting gas station is " + gs.canCompleteCircuit(gas12, cost12) + " (should be 4)" +
                " brute force result is " + gs.canCompleteCircuitBruteForce(gas12, cost12));
    }
}