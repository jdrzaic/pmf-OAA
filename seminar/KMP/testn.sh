#!/bin/bash

n=$1
m=1
for i in {1..300}
do	
	java -cp bin testing.NaiveSearch "$n" "$m"
	let "n += $1"
	let "m += 1"
done
