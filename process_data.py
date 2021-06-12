from collections import defaultdict
import os
import csv
import json


def getBusRoutes():
    routes = set()
    with open(os.path.join("gtfs", "routes.txt"), encoding="utf-8") as f:
        f_csv = csv.DictReader(f)
        for row in f_csv:
            if row["route_type"] == "3":
                routes.add(row["route_id"])
    return routes


def getTripsByRoutes(routes):
    trips = defaultdict(list)
    with open(os.path.join("gtfs", "trips.txt"), encoding="utf-8") as f:
        f_csv = csv.DictReader(f)
        for row in f_csv:
            if row["route_id"] in routes:
                trips[row["route_id"]].append(row["trip_id"])
    return trips


def getTripsWithStops(trips):
    tripsWithStops = defaultdict(list)
    tripsWithStopsUnique = list()

    with open(os.path.join("gtfs", "stop_times.txt"), encoding="utf-8") as f:
        f_csv = csv.DictReader(f)
        for row in f_csv:
            tripsWithStops[row["trip_id"]].append(row["stop_id"])

    for routes in trips:
        flag = 0
        index = 1
        for tripId in trips[routes]:
            if flag == 0:
                flag = 1
                test = tripsWithStops[tripId]
                tripsWithStopsUnique.append(test)
            else:
                v = tripsWithStops[tripId]
                if v not in tripsWithStopsUnique:
                    index += 1
                    tripsWithStopsUnique.append(tripsWithStops[tripId])

    return tripsWithStopsUnique


def getStops(tripsWithStops, adjList):
    usedStops = set()
    stops = defaultdict(dict)
    for trip in tripsWithStops:
        for stop in trip:
            usedStops.add(stop)

    with open(os.path.join("gtfs", "stops.txt"), encoding="utf-8") as f:
        f_csv = csv.DictReader(f)
        for row in f_csv:
            if row["stop_id"] in usedStops:
                stops[row["stop_id"]] = {
                    "lat": float(row["stop_lat"]),
                    "lon": float(row["stop_lon"]),
                    "adjList": adjList[row["stop_id"]],
                }
    return stops


def getAdjList(stopsUnique):
    adjSet = defaultdict(set)
    for s in stopsUnique:
        for i in range(0, len(s) - 1):
            adjSet[s[i]].add(int(s[i + 1]))
    adjList = defaultdict(list)
    for key in adjSet:
        adjList[key] = list(adjSet[key])
    return adjList


def writeAdjList(jsonIn):
    with open(os.path.join("gtfs", "result.json"), encoding="utf-8", mode="w+") as f:
        f.write(jsonIn)


if __name__ == "__main__":
    tripsWithStops = getTripsWithStops(getTripsByRoutes(getBusRoutes()))
    stops = getStops(tripsWithStops,getAdjList(tripsWithStops))
    jsonStops = json.dumps(stops)
    writeAdjList(jsonStops)

