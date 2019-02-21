#!/bin/bash
echo hello world
LOGFILE=BuildRecipe
LOGEXTENSION=log
DATE=`date +%m%d%Y_%M%S`
echo $DATE
touch ${LOGFILE}_${DATE}.${LOGEXTENSION}

mvn -f ../Recipe install -l ./logs/${LOGFILE}_${DATE}.${LOGEXTENSION}
