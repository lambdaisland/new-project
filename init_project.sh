#!/bin/bash

DRY_RUN=""
# DRY_RUN="echo"

find -type f -print0 | xargs -0 -n 1 -I{} $DRY_RUN sed "s/{{project}}/$1/g" -i {}

DIR="${1//\.//}"
PARENT=$(dirname $DIR)

find -type d -name project -print0 | xargs -0 -n 1 -I{} $DRY_RUN sh -c 'mkdir -p `dirname {}`/'"$PARENT"' ; mv {} `dirname {}`/'"$DIR"
