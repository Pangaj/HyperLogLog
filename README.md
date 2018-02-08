# HyperLogLog

- Kind of ***Probabilistic Data Structure***
  - Group of data structures that are extremely **useful for big data and streaming applications**
  - These data structures use **hash functions** to ***randomize and compactly represent a set of items***
  - **Collisions** are *ignored* but **errors** can well-controlled under ***certain threshold***
  - *Comparing with* **error-free approaches**, **these uses much less memory and have constant query time**
  - They **supports union and intersection** operations and therefore can be ***easily parallelized***


- It's a **space-efficient algos. for accurate cardinality estimations**
- Redis’s implementation of HLL uses at most 12Kb of memory to estimate the cardinality of practically any set
