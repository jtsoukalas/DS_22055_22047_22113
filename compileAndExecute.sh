#!/bin/bash

# Moving to project's directory
cd $(cd `dirname $0` && pwd)

# Compiling the project
mvn clean
mvn package

# Executing the project
java -cp target/DS_First_Exc-1.0-SNAPSHOT.jar gr.hua.dit.ds.circularQueue.App
