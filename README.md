<p align="center">
  <img src="https://img.shields.io/badge/Solutions-386%20%7C%20382%20Java%20%2B%204%20SQL-4B8BBE?style=for-the-badge&logo=leetcode&logoColor=FFA116"/>
  <img src="https://img.shields.io/badge/Language-Java%2017-E76F00?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Easy-167-44B77B?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Medium-197-FCB833?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/Hard-22-EB5C5C?style=for-the-badge"/>
</p>

<h1 align="center">LeetCode Solutions in Java</h1>

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

Most LeetCode repos are flat folders of files named after problem numbers. This one is different — it's organized by **algorithmic pattern**, because that's how problems are actually *solved* in an interview: you're never told "this is a DP problem." You have to recognize it.

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
| **Total solutions** | **386** (382 Java + 4 SQL) |
| Difficulty | 🟢 167 Easy · 🟡 197 Medium · 🔴 22 Hard |
| Patterns covered | **18** |
| Language | Java 17 — standard library only, zero external deps |
| Structure | Organized by **algorithmic pattern**, self-contained files |

## Progress by pattern

| Pattern | Solved | Easy | Medium | Hard |
| ------- | ------ | :---: | :----: | :---: |
| Math | 57 | 38 | 19 | 0 |
| Array | 46 | 32 | 14 | 0 |
| Dynamic Programming | 32 | 3 | 26 | 3 |
| Hash Table | 26 | 19 | 6 | 1 |
| Greedy | 25 | 6 | 16 | 3 |
| Linked List | 25 | 8 | 16 | 1 |
| Tree | 24 | 9 | 13 | 2 |
| Two Pointers | 21 | 11 | 8 | 2 |
| Backtracking | 19 | 1 | 14 | 4 |
| Graph | 19 | 2 | 15 | 2 |
| Binary Search | 17 | 6 | 10 | 1 |
| Sliding Window | 16 | 4 | 10 | 2 |
| String | 15 | 11 | 4 | 0 |
| Matrix | 13 | 4 | 9 | 0 |
| Prefix Sum | 12 | 7 | 5 | 0 |
| Stack | 10 | 2 | 7 | 1 |
| Heap | 4 | 1 | 3 | 0 |
| Divide and Conquer | 1 | 0 | 1 | 0 |
| **Total (Java)** | **382** | **164** | **196** | **22** |
| Database (SQL) | 4 | 3 | 1 | 0 |
| **Grand total** | **386** | **167** | **197** | **22** |

## Conventions used throughout

- **Iterative over recursive** wherever a clean iterative version exists (stacks/queues instead of recursion for traversal-heavy problems).
- **Bottom-up tabulation** for DP, not top-down memoization — unless the recursive structure is significantly clearer.
- Every file documents its **LeetCode number, difficulty, tags, time complexity, and space complexity** in a header comment.
- **Edge cases** (empty input, single element, boundary values) are handled explicitly, not assumed away.
- Solutions are **self-contained** and named after the problem — no cross-file imports to hunt down.

## Repository structure

```
leetcode-solutions/
├── Array/               ── 46
├── Backtracking/        ── 19
├── Binary Search/       ── 17
├── Database/            ── 4 (SQL)
├── Divide and Conquer/  ── 1
├── Dynamic Programming/ ── 32
├── Graph/               ── 19
├── Greedy/              ── 25
├── Hash Table/          ── 26
├── Heap/                ── 4
├── Linked List/         ── 25
├── Math/                ── 57
├── Matrix/              ── 13
├── Prefix Sum/          ── 12
├── Sliding Window/      ── 16
├── Stack/               ── 10
├── String/              ── 15
├── Tree/                ── 24
└── Two Pointers/        ── 21
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

