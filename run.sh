#!/bin/sh

find * -name '*.java' > sources.txt
javac @sources.txt
java --class-path src fr.aguiot.Main
