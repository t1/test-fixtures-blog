#!/usr/bin/env bash
set -e

DIR=wildfly

if [ -d "${DIR}" ]; then
    rm -r "${DIR}"
fi

galleon.sh provision provision.xml --dir="${DIR}"

galleon.sh get-info --dir="${DIR}"

# /subsystem=datasources/data-source=ExampleDS:add(driver-name=h2,jndi-name=java:jboss/datasources/ExampleDS,connection-url="jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=${wildfly.h2.compatibility.mode:REGULAR}")
