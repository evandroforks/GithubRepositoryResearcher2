#!/bin/bash

set -ex

# https://github.com/facebook/create-react-app/issues/3070 
export CI=true
export BROWSER="none"

cd javabackend
mvn package exec:java &

cd ..

cd reactfrontend
npm start
