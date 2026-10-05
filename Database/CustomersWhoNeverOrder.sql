-- 183. Customers Who Never Order
-- Difficulty: Easy | Tags: Database
-- https://leetcode.com/problems/customers-who-never-order/
-- 
-- Pattern: Left Anti Join
-- 
-- Key insight: Use a LEFT JOIN to match customers with orders and filter for
-- NULL foreign keys to find customers who placed no orders.
-- 
-- Time Complexity: O(N + M) - scanning both tables to execute the join and
-- filter
-- 
-- Space Complexity: O(N + M) - storing intermediate join results and query
-- output
-- 
-- Edge Cases Handled: Per LeetCode constraints
# Write your MySQL query statement below
SELECT name AS Customers FROM Customers c LEFT JOIN Orders o ON c.id=o.customerId WHERE o.customerId IS NULL;
