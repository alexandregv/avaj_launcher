#!/bin/sh

find * -name '*.java' > sources.txt
javac -d out @sources.txt
java --class-path out fr.ftparis.avaj.launcher.Simulator $@
