# Challenge 2
To run: `./two <filename> <balance>`

The file specified by `filename` must have one item per line where 
each line is the item name and item price separated by a comma and space

For example:
```
$ cat prices.txt
Candy Bar, 500
Paperback Book, 700
Detergent, 1000
Headphones, 1400
Earmuffs, 2000
Bluetooth Stereo, 6000
$ ./two prices.txt 2500
Candy Bar 500, Earmuffs 2000
$ ./two prices.txt 2300
Paperback Book 700, Headphones 1400
$ ./two prices.txt 10000
Earmuffs 2000, Bluetooth Stereo 6000
$ ./two prices.txt 1100
Not possible
```

This algorithm is `O(nlog(n))` where `n` is the number of items because for each of `n` items, a binary search of `log(n)` is done on the rest of the items.

# Challenge 3
To run: `./three <string>`

The string specified by `string` must contain only `0`, `1`, or `X`
For example:
```
$ ./three X0
00
10
$ ./three 10X10X0
1001000
1001010
1011000
1011010
```

This algorithm is `2^n` where `n` is the number of `X`'s because for each `X` there are two possible replacements.
