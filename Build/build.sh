#!/bin/bash
echo hello world
LOGFILE=BuildRecipe
LOGEXTENSION=log
DATE=`date +%m%d%Y_%M%S`

mvn -f ../Recipe install -l ./logs/${LOGFILE}_${DATE}.${LOGEXTENSION}
cp -v ../Recipe/target/*.jar ./modules
