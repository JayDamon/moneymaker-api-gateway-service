#!/bin/bash

echo "version = $1"

# Get version number from version tag
JAR_VERSION=`echo $1 | cut -d'v' -f2`
echo "jar = $JAR_VERSION"
mvn versions:set -DnewVersion=$JAR_VERSION
