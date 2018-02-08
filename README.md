# HyperLogLog

- Kind of ***Probabilistic Data Structure***
  - Group of data structures that are extremely **useful for big data and streaming applications**
  - These data structures use **hash functions** to ***randomize and compactly represent a set of items***
  - **Collisions** are *ignored* but **errors** can well-controlled under ***certain threshold***
  - *Comparing with* **error-free approaches**, **these uses much less memory and have constant query time**
  - They **supports union and intersection** operations and therefore can be ***easily parallelized***


- It's a **space-efficient algos. for accurate cardinality estimations**
- Redis’s implementation of HLL uses at most 12Kb of memory to estimate the cardinality of practically any set

-  The **Standard Error (SE) of the algorithm** is defined as:
<p align="center">
  <img align="" src="https://raw.githubusercontent.com/Pangaj/HyperLogLog/master/pictures/hllFormula.png" alt="center">
</p>

 - where ***p*** is considered as a **parameter**  of HLL specifying the **desired accuracy**.
 - When one says **“HyperLogLog 16-bit”**, they mean that **p = 16**, making ***m = 2<sup>16</sup> = 65536***
 - Thus, by **controlling the value of p**, the **accuracy** of HLL's **cardinality estimation** ***can be tuned***
    - **Redis uses 14-bit HyperLogLog**
    - **AdRoll uses 18-bit HyperLogLog**
  - It is necessary to note that the **higher the "p"**, the ***more accurate the cardinality estimation***

- By using a **minimal amount of memory** interms of KB, it can effectively represent **hundreds of millions of distinct elements**



 The **HyperLogLog algorithm**, thoroughly described in [HyperLogLog: the analysis of a near-optimal cardinality estimation algorithm](http://algo.inria.fr/flajolet/Publications/FlFuGaMe07.pdf)
- **Hashes each item** in the set to be analyzed, obtaining an associated ***32-bit binary number***
- This value is then **decomposed in two parts**
  - The **first  bits** of this value are used to ***determine which bucket*** of the *estimator this number falls in out of the  ones available*,
  - And in the *rest of the bits* --- **we count the amount of leading zeros** to ***estimate the probability of this number occurring***.
- *Every estimator bucket*, stores the **maximum amount of leading zeros** found for all the values associated with that bucket
- *After all the items processed this way*, the ***harmonic mean*** of the value for all the buckets is calculated
- This value is then **multiplied by a constant & amount of buckets**
