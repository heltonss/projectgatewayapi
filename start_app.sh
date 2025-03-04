#!/bin/bash

./gradlew bootRun &
echo $! > bootrun.pid
wait $! 
