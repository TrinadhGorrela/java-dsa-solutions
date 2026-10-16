<p align="center">
  <img src="https://img.shields.io/badge/Solutions-435%20%7C%20431%20Java%20%2B%204%20SQL-4B8BBE?style=for-the-badge&logo=leetcode&logoColor=FFA116"/>
  <img src="https://img.shields.io/badge/Language-Java%2017-E76F00?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Easy-178-44B77B?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Medium-228-FCB833?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Hard-29-EB5C5C?style=for-the-badge"/>
</p>

<h1 align="center">Java DSA Solutions</h1>

<p align="center">
  <b>Pattern-first, interview-ready DSA solutions.</b><br/>
  Not a scatter of answers — a <i>playbook</i>: every file maps to a pattern you can recognize and reproduce on a whiteboard.
</p>

<p align="center">
  <a href="https://linkedin.com/in/trinadhgorrela"><img src="https://img.shields.io/badge/-LinkedIn-0A66C2?style=flat-square&logo=linkedin&logoColor=white"/></a>
  <a href="https://github.com/TrinadhGorrela"><img src="https://img.shields.io/badge/-GitHub-181717?style=flat-square&logo=github&logoColor=white"/></a>
  <a href="https://leetcode.com/u/Trinadh462/"><img src="https://img.shields.io/badge/-LeetCode-FFA116?style=flat-square&logo=leetcode&logoColor=black"/></a>
</p>

---

Most DSA and competitive programming repos are flat folders of files named after problem numbers. This one is different — it's organized by **algorithmic pattern**, because that's how problems are actually *solved* in an interview: you're never told "this is a DP problem." You have to recognize it.

If you're grinding for **SDE interviews at product-based companies**, this is a study guide, not just an answer key.

## Table of Contents

- [Why pattern-based?](#why-pattern-based)
- [Quick stats](#quick-stats)
- [Progress by pattern](#progress-by-pattern)
- [Conventions used throughout](#conventions-used-throughout)
- [Repository structure](#repository-structure)
- [Tech stack](#tech-stack)
- [Updates](#updates)
- [Author](#author)

## Why pattern-based?

Interviews don't hand you a topic tag. A *"sliding window"* problem, a *"two pointers"* problem, and a *"binary search"* problem can all look superficially alike. The ability that gets you hired is the ability to **map a fresh, weird problem onto a known pattern**.

Each subdirectory here is a studied pattern:

- **All** solutions in a folder share the same recognition heuristic — read any two and the pattern clicks.
- **Difficulty is a by-product**, not the organizing idea. You build up from recognizing a pattern to mastering it.
- **Edge cases are called out explicitly** (empty input, single element, boundaries) — the exact things that decide pass/fail on the judge and in review.

I built this the way you actually study: practice first, notice the recurring shapes, then file each solution under the shape it belongs to.

## Quick stats

| Metric | Value |
| ------ | ----- |
| **Total solutions** | **435** (431 Java + 4 SQL) |
| Difficulty | 🟢 178 Easy · 🟡 228 Medium · 🔴 29 Hard |
| Patterns covered | **25** |
| Language | Java 17 — standard library only, zero external deps |
| Structure | Organized by **algorithmic pattern**, self-contained files |

## Progress by pattern

| Pattern | Solved | Easy | Medium | Hard |
| ------- | ------ | :---: | :----: | :---: |
| Math | 48 | 34 | 14 | 0 |
| Array | 46 | 33 | 13 | 0 |
| Dynamic Programming | 40 | 4 | 33 | 3 |
| Greedy | 29 | 6 | 19 | 4 |
| Linked List | 27 | 8 | 18 | 1 |
| Tree | 27 | 11 | 15 | 1 |
| Hash Table | 26 | 19 | 6 | 1 |
| Two Pointers | 22 | 11 | 9 | 2 |
| Binary Search | 19 | 6 | 12 | 1 |
| Graph | 19 | 3 | 14 | 2 |
| Backtracking | 18 | 0 | 14 | 4 |
| Sliding Window | 17 | 3 | 12 | 2 |
| String | 17 | 14 | 3 | 0 |
| Matrix | 15 | 4 | 10 | 1 |
| Stack | 15 | 4 | 8 | 3 |
| Prefix Sum | 14 | 8 | 6 | 0 |
| Bit Manipulation | 8 | 5 | 3 | 0 |
| Monotonic Stack | 5 | 0 | 5 | 0 |
| Heap | 4 | 1 | 3 | 0 |
| Intervals | 3 | 0 | 3 | 0 |
| Segment Tree | 3 | 1 | 0 | 2 |
| Union Find | 3 | 0 | 3 | 0 |
| Divide and Conquer | 2 | 0 | 1 | 1 |
| Game Theory | 2 | 0 | 2 | 0 |
| Topological Sort | 2 | 0 | 1 | 1 |
| **Total (Java)** | **431** | **175** | **227** | **29** |
| Database (SQL) | 4 | 3 | 1 | 0 |
| **Grand total** | **435** | **178** | **228** | **29** |

## Conventions used throughout

- **Iterative over recursive** wherever a clean iterative version exists (stacks/queues instead of recursion for traversal-heavy problems).
- **Bottom-up tabulation** for DP, not top-down memoization — unless the recursive structure is significantly clearer.
- Every file documents its **source platform, difficulty, tags, time complexity, and space complexity** in a header comment.
- **Edge cases** (empty input, single element, boundary values) are handled explicitly, not assumed away.
- Solutions are **self-contained** and named after the problem — no cross-file imports to hunt down.
- We include **Standard Algorithms** like sorting.

## Repository structure

```
java-dsa-solutions/
├── Array/               ── 46
├── Backtracking/        ── 18
├── Binary Search/       ── 19
├── Bit Manipulation/    ── 8
├── Database/            ── 4 (SQL)
├── Divide and Conquer/  ── 2
├── Dynamic Programming/ ── 40
├── Game Theory/         ── 2
├── Graph/               ── 19
├── Greedy/              ── 29
├── Hash Table/          ── 26
├── Heap/                ── 4
├── Intervals/           ── 3
├── Linked List/         ── 27
├── Math/                ── 48
├── Matrix/              ── 15
├── Monotonic Stack/     ── 5
├── Prefix Sum/          ── 14
├── Segment Tree/        ── 3
├── Sliding Window/      ── 17
├── Stack/               ── 15
├── String/              ── 17
├── Topological Sort/    ── 2
├── Tree/                ── 27
├── Two Pointers/        ── 22
└── Union Find/          ── 3
```

## Tech stack

- **Java 17**
- **No external libraries** — pure `java.util` (`ArrayList`, `HashMap`, `ArrayDeque`, `PriorityQueue`, etc.)
- SQL solutions use plain, portable query syntax

## Updates

> **This repository is updated daily** — new solutions are added as problems are solved, keeping the collection consistently fresh and growing.

## Author

<p align="left">
  <b>Siva Satya Trinadh Gorrela</b><br/>
  Self-taught developer crossing from an ECE background into full-stack & backend engineering.<br/>
  <a href="https://linkedin.com/in/trinadhgorrela">LinkedIn</a> · <a href="https://github.com/TrinadhGorrela">GitHub</a>
</p>

