-- 181. Employees Earning More Than Their Managers
-- Difficulty: Easy | Tags: Database
-- https://leetcode.com/problems/employees-earning-more-than-their-managers/
-- 
-- Pattern: Self Join
-- 
-- Key insight: Join the table to itself matching manager IDs with employee IDs
-- and filter where employee salary exceeds manager salary.
-- 
-- Time Complexity: O(N) - joining on the primary key manager ID takes linear
-- time
-- 
-- Space Complexity: O(N) - intermediate join and output result storage
-- 
-- Edge Cases Handled: Per LeetCode constraints
# Write your MySQL query statement below
SELECT e.name AS Employee FROM Employee e JOIN Employee m ON e.managerId=m.id WHERE e.salary>m.salary;
