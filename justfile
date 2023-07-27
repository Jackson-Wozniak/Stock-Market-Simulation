
# Main justfile to run all the development scripts
# To install 'just' see https://github.com/casey/just#installation

# Ensure all properties are exported as shell env-vars
set export

# Load any .env file eg to specify private access keys etc
set dotenv-load

# set the current directory, and the location of the test dats
CWDIR := justfile_directory()

_default:
    @just -f {{justfile()}} --list

build:
    #!/bin/bash 
    set -ex -o pipefail

    pushd Backend
    mvn clean package -DskipTests
    popd

    docker-compose down
    docker-compose build

start:
    docker-compose up