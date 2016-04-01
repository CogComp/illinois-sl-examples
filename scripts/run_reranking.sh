#!/usr/bin/env bash

MEMORY="-Xmx4g"

CP="./config/:./target/classes/:./target/dependency/*"

OPTIONS="$MEMORY -Xss40m -ea -cp $CP"
PACKAGE_PREFIX="edu.illinois.cs.cogcomp"
MAIN="$PACKAGE_PREFIX.sl.applications.reranking.MainClass"
time nice java $OPTIONS $MAIN $CONFIG_STR $*
