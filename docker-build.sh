#!/bin/bash
NAME=sk042
IMAGE_NAME="my-app"
VERSION="1.0.0"

# Docker 이미지 빌드
docker buildx build \
  --tag ${NAME}-${IMAGE_NAME}:${VERSION} \
  --file Dockerfile \
  --platform linux/amd64 \
  ${IS_CACHE} .
