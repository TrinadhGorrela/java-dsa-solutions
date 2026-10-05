-- 175. Combine Two Tables
-- Difficulty: Easy | Tags: Database
-- https://leetcode.com/problems/combine-two-tables/
-- 
-- Pattern: Left Outer Join
-- 
-- Key insight: A LEFT JOIN ensures all persons are returned even if they lack a
-- corresponding entry in the address table.
-- 
-- Time Complexity: O(N + M) - scanning both tables to evaluate the join
-- condition
-- 
-- Space Complexity: O(N) - storing the output result set for N persons
-- 
-- Edge Cases Handled: Per LeetCode constraints
# Write your MySQL query statement below
SELECT 
 firstName, lastName, city, state 
FROM Person p
LEFT JOIN Address a 
ON p.PersonId = a.personId;
