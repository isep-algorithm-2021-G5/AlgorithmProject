import csv
import os
from collections import defaultdict
from math import cos, sin, asin, sqrt, pi


def get_bus_routes():
    routes = set()
    with open(os.path.join("gtfs", "routes.txt"), encoding="utf-8") as f:
        f_csv = csv.DictReader(f)
        for row in f_csv:
            if row["route_type"] == "3":
                routes.add(row["route_id"])
    return routes


def get_trips_by_routes(routes):
    trips = defaultdict(list)
    with open(os.path.join("gtfs", "trips.txt"), encoding="utf-8") as f:
        f_csv = csv.DictReader(f)
        for row in f_csv:
            if row["route_id"] in routes:
                trips[row["route_id"]].append(row["trip_id"])
    return trips


def get_trips_with_stops(trips):
    trips_stops = defaultdict(list)
    trips_stops_unique = list()

    with open(os.path.join("gtfs", "stop_times.txt"), encoding="utf-8") as f:
        f_csv = csv.DictReader(f)
        for row in f_csv:
            trips_stops[row["trip_id"]].append(row["stop_id"])

    for routes in trips:
        flag = 0
        index = 1
        for tripId in trips[routes]:
            if flag == 0:
                flag = 1
                test = trips_stops[tripId]
                trips_stops_unique.append(test)
            else:
                v = trips_stops[tripId]
                if v not in trips_stops_unique:
                    index += 1
                    trips_stops_unique.append(trips_stops[tripId])

    return trips_stops_unique


def get_stops(trips):
    used_stops = set()
    stops = defaultdict(dict)
    for trip in trips:
        for stop in trip:
            used_stops.add(stop)

    with open(os.path.join("gtfs", "stops.txt"), encoding="utf-8") as f:
        f_csv = csv.DictReader(f)
        for row in f_csv:
            if row["stop_id"] in used_stops:
                stops[row["stop_id"]] = {
                    "lat": float(row["stop_lat"]),
                    "lon": float(row["stop_lon"])
                }
    return stops


def get_adj_list(trips_stops_unique):
    adj_set = defaultdict(set)
    for s in trips_stops_unique:
        for i in range(0, len(s) - 1):
            adj_set[s[i]].add(int(s[i + 1]))
    adj_list = defaultdict(list)
    for key in adj_set:
        adj_list[key] = list(adj_set[key])
    return adj_list


def get_graph(adj_list, stops):
    graph = list()
    for from_id in adj_list:
        for to_id in adj_list[from_id]:
            distance = get_distance(stops[from_id]["lat"],
                                    stops[from_id]["lon"],
                                    stops[str(to_id)]["lat"],
                                    stops[str(to_id)]["lon"])
            graph.append([int(from_id), int(to_id), distance])

    return graph


def write_csv(graph, stops):
    with open(os.path.join("gtfs", "graph.csv"), encoding="utf-8",
              mode="w+", newline='') as f:
        f_csv = csv.writer(f)
        f_csv.writerow(['from', 'to', 'weight'])
        f_csv.writerows(graph)
    stops_list = list()
    for stop_id in stops:
        stops_list.append([int(stop_id), stops[stop_id]["lat"],
                           stops[stop_id]["lon"]])
    with open(os.path.join("gtfs", "stops.csv"), encoding="utf-8",
              mode="w+", newline='') as f:
        f_csv = csv.writer(f)
        f_csv.writerow(['id', 'lat', 'lon'])
        f_csv.writerows(stops_list)


def rad(d):
    return d * pi / 180.0


def get_distance(lat1, lon1, lat2, lon2):
    rad_lat1 = rad(lat1)
    rad_lat2 = rad(lat2)
    a = rad_lat1 - rad_lat2
    b = rad(lon1) - rad(lon2)
    s = 2 * asin(sqrt(
            pow(sin(a / 2), 2) + cos(rad_lat1) * cos(rad_lat2) * pow(
                    sin(b / 2), 2)))
    s = s * 6378.137
    return s


if __name__ == "__main__":
    trips_with_stops = get_trips_with_stops(
            get_trips_by_routes(get_bus_routes()))
    adj_list_res = get_adj_list(trips_with_stops)
    stops_res = get_stops(trips_with_stops)
    graph_res = get_graph(adj_list_res, stops_res)
    write_csv(graph_res, stops_res)
