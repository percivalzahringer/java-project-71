# Makefile

setup:
	$ gradle wrapper --gradle-version 8.5

clean:
	$ gradle clean

build:
	$ gradle clean build

install:
	$ gradle clean install

run-dist:
	$ gradle clean
	$ gradle installDist
	./build/install/app/bin/app

run:
	 $ gradle run

.PHONY: test

test:
	$ gradle test

.PHONY: report

report:
	$ gradle jacocoTestReport

lint:
	$ gradle checkstyleMain

check-dep:
	$ gradle dependencyUpdates -Drevision=release

build-run: build run

.PHONY: build