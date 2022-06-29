#!/bin/bash

set -ex

rm -rfv ${HOME}/volumes_mapping
docker system prune --all --force
docker-compose build --no-cache
docker-compose up -d
