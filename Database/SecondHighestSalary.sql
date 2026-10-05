-- 176. Second Highest Salary
-- Difficulty: Medium | Tags: Database
-- https://leetcode.com/problems/second-highest-salary/
-- 
-- Pattern: Subquery with Aggregate
-- 
-- Key insight: Find the maximum salary strictly less than the overall maximum,
-- which naturally evaluates to NULL if no second highest exists.
-- 
-- Time Complexity: O(N) - scans the table twice to compute maximum values
-- 
-- Space Complexity: O(1) - uses constant auxiliary space for scalar aggregation
-- 
-- Edge Cases Handled: Per LeetCode constraints
# Write your MySQL query statement below
select max(salary) as SecondHighestSalary
from Employee
where salary<(
    select max(salary) 
    from Employee
);
