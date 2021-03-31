FROM adoptopenjdk/openjdk11
ADD mycreds.json mycreds.json
ENV GOOGLE_APPLICATION_CREDENTIALS=./mycreds.json