package com.example.poc;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.collection.ISet;

public class HazelcastPOC {

    public static void main(String[] args) {

        // Start Hazelcast Node
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        System.out.println("Hazelcast instance started...\n");

        // ---------------- IMap Example ----------------
        IMap<Integer, String> userMap = hz.getMap("users");

        System.out.println("=== IMap CRUD Operations ===");

        // CREATE
        userMap.put(1, "Alice");
        userMap.put(2, "Bob");

        // READ
        System.out.println("User with key 1: " + userMap.get(1));

        // UPDATE
        userMap.put(1, "Alice Updated");
        System.out.println("Updated User 1: " + userMap.get(1));

        // DELETE
        userMap.remove(2);
        System.out.println("Map after deletion: " + userMap + "\n");


        // ---------------- ISet Example ----------------
        ISet<String> activeUsers = hz.getSet("active-users");

        System.out.println("=== ISet CRUD-like Operations ===");

        // ADD
        activeUsers.add("Alice");
        activeUsers.add("Charlie");

        // READ
        System.out.println("Does Alice exist? " + activeUsers.contains("Alice"));

        // UPDATE (remove + add)
        activeUsers.remove("Charlie");
        activeUsers.add("Charlie Updated");

        // DELETE
        activeUsers.remove("Alice");

        System.out.println("Final Set: " + activeUsers);

        // Shutdown Hazelcast
        hz.shutdown();
        System.out.println("\nHazelcast shutdown complete.");
    }
}
